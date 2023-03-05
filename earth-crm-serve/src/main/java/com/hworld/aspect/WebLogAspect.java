package com.hworld.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.hworld.bo.log.SysOperationLogBO;
import com.hworld.entity.sys.SysUserDO;
import com.hworld.enums.KafKaTopicEnum;
import com.hworld.enums.LogTypeEnum;
import com.hworld.http.WebContext;
import com.hworld.service.api.kafka.KafKaProducerService;
import com.hworld.service.api.sys.SysUserService;
import com.hworld.utils.MyStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 日志拦截
 *
 * @author caoyang
 * @date 2022-01-18 12:31:07
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private KafKaProducerService kafKaProducerService;

    private long startTime;

    private SysOperationLogBO sysOperationLogBO;

    // 以 controller 包下定义的所有请求为切入点
    @Pointcut("execution(public * com.hworld.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            log.info("=========================================={} Start ==========================================",
                    joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

            startTime = System.currentTimeMillis();
            //设置日志参数
            sysOperationLogBO = new SysOperationLogBO();
            sysOperationLogBO.setReqTime(LocalDateTime.now());

            // 开始打印请求日志
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            //获取token
            String satoken = request.getHeader("satoken");
            setWebContext(satoken);


            sysOperationLogBO.setReqHeader(StringEscapeUtils.unescapeJava(JSON.toJSONString(getHeader(request))));
            sysOperationLogBO.setReqUrl(String.valueOf(request.getRequestURL()));
            sysOperationLogBO.setReqMethod(request.getMethod());
            sysOperationLogBO.setReqMethodPath(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            sysOperationLogBO.setReqParams(StringEscapeUtils.unescapeJava(JSON.toJSONString(getReqParams(joinPoint))));
            sysOperationLogBO.setReqIpAddres(getIpAddr(request));
            sysOperationLogBO.setLogType(LogTypeEnum.RUN_LOG.getLogType());
        } catch (Exception e) {
            log.error("出现异常：{}", e);
        }
    }

//    @Around("webLog()")
//    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//
//        Object result = proceedingJoinPoint.proceed();
//        // 打印出参
//        log.info("响应参数:{}", JSON.toJSONString(result));
//        // 执行耗时
//        log.info("响应时间:{} ms", System.currentTimeMillis() - startTime);
//        return result;
//    }

    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) {
        try {
            //清除ThreadLocal
            WebContext.remove();
            log.info("==========================================={} End ===========================================",
                    joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            // 每个请求之间空一行
            log.info("");
        } catch (Exception e) {
            log.error("出现异常：{}", e);
        }
    }

    // 在方法执行完结后打印返回内容
    @AfterReturning(returning = "resp", pointcut = "webLog()")
    public void methodAfterReturing(Object resp) {
        sysOperationLogBO.setRespTime(LocalDateTime.now());
        sysOperationLogBO.setRespParams(StringEscapeUtils.unescapeJava(JSON.toJSONString(resp)));
        sysOperationLogBO.setOperationTime(System.currentTimeMillis() - startTime);
        kafKaProducerService.sendKafkaLogMessage(KafKaTopicEnum.CRM_WORLD_LOG.getTopic(), JSON.toJSONString(sysOperationLogBO));

        log.info("请求日志:{}", sysOperationLogBO);
    }

    /**
     * 异常通知
     *
     * @param point
     */
    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void serviceAspect(JoinPoint point, Exception e) {
        sysOperationLogBO.setErrorMsg(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
        sysOperationLogBO.setLogType(LogTypeEnum.ERROR_LOG.getLogType());
//        crmLogService.sendCrmLogToKafka(JSON.toJSONString(sysOperationLogBO));
        kafKaProducerService.sendKafkaLogMessage(KafKaTopicEnum.CRM_WORLD_LOG.getTopic(), JSON.toJSONString(sysOperationLogBO));
        log.error("异常日志:{}", sysOperationLogBO);
    }

    /**
     * 获取请求头数据
     *
     * @param request
     * @return
     */
    public Map<String, String> getHeader(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<>();
        //获取请求头
        Enumeration headerNames = request.getHeaderNames();
        //使用循环遍历请求头，并通过getHeader()方法获取一个指定名称的头字段
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            headerMap.put(headerName, request.getHeader(headerName));
        }
        return headerMap;
    }

    /**
     * 获取请求参数
     *
     * @param joinPoint
     * @return
     */
    public Map<String, Object> getReqParams(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        String[] keys = pnd.getParameterNames(method);
        Object[] values = joinPoint.getArgs();
        Map<String, Object> paramsMap = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            if (values[i] instanceof HttpServletRequest || values[i] instanceof HttpServletResponse) {
                continue;
            }
            paramsMap.put(keys[i], values[i]);
        }
        return paramsMap;
    }

    /**
     * 获取IP信息
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        //X-Forwarded-For（XFF）是用来识别通过HTTP代理或负载均衡方式连接到Web服务器的客户端最原始的IP地址的HTTP请求头字段。
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("出现异常：{}", e);
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public void setWebContext(String satoken) {
        //获取redis用户信息
        if (MyStringUtils.isNullParam(satoken)) {
            return;
        }
        Long userId = MyStringUtils.stringToLong(String.valueOf(StpUtil.getLoginIdByToken(satoken)));
        if (userId == null) {
            return;
        }
        SysUserDO sysUserDO = sysUserService.getOneById(userId);
        if (sysUserDO != null) {
            WebContext.set(sysUserDO.getId(), sysUserDO.getName());
        }
    }
}
