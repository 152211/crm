package com.hworld.aspect;

import com.alibaba.fastjson.JSON;
import com.hworld.bo.log.SysRequestLogBO;
import com.hworld.enums.KafKaTopicEnum;
import com.hworld.service.api.kafka.KafKaProducerService;
import com.hworld.utils.SnowflakeIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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
public class RestTemplateLogAspect {
    @Autowired
    private KafKaProducerService kafKaProducerService;

    private SysRequestLogBO sysRequestLogBO = new SysRequestLogBO();

    private long startTime;

    @Pointcut("execution(public * com.hworld.utils.RestTemplateUtil.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            startTime = System.currentTimeMillis();
            //类名
            String clazzName = joinPoint.getTarget().getClass().getName();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            //方法名
            String methodName = methodSignature.getName();
            //参数名数组
            String[] parameterArray = methodSignature.getParameterNames();
            //参数值
            Object[] parameterValueArray = joinPoint.getArgs();

            if (parameterArray == null || parameterArray.length <= 0) {
                return;
            }
            sysRequestLogBO.setHttpMethod(methodName);
            sysRequestLogBO.setRequestId(String.valueOf(SnowflakeIdUtil.getSnowflakeId()));
            for (String parameter : parameterArray) {
                //获取参数名对应数组下标
                int paramIndex = ArrayUtils.indexOf(parameterArray, parameter);
                if (paramIndex < 0) {
                    continue;
                }
                Object parameterValue = parameterValueArray[paramIndex];
                if ("url".equalsIgnoreCase(parameter)) {
                    sysRequestLogBO.setRequestUrl(String.valueOf(parameterValue));
                }
                if ("params".equalsIgnoreCase(parameter)) {
                    sysRequestLogBO.setRequestParams(StringEscapeUtils.unescapeJava(JSON.toJSONString(parameterValue)));
                }
                if ("headers".equalsIgnoreCase(parameter)) {
                    sysRequestLogBO.setRequestHeaders(StringEscapeUtils.unescapeJava(JSON.toJSONString(parameterValue)));
                }
                if ("requestBody".equalsIgnoreCase(parameter)) {
                    sysRequestLogBO.setRequestBody(StringEscapeUtils.unescapeJava(JSON.toJSONString(parameterValue)));
                }
            }
        } catch (Exception e) {
            log.error("出现异常：{}", e);
        }
    }


    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) {
        try {

        } catch (Exception e) {
            log.error("出现异常：{}", e);
        }
    }

    // 在方法执行完结后打印返回内容
    @AfterReturning(returning = "resp", pointcut = "webLog()")
    public void methodAfterReturing(Object resp) {
        ResponseEntity response = (ResponseEntity) resp;
        sysRequestLogBO.setResponseStatus(response.getStatusCodeValue());
        sysRequestLogBO.setResponseData(StringEscapeUtils.unescapeJava(JSON.toJSONString(response.getBody())));
        sysRequestLogBO.setResponseTime(String.valueOf(System.currentTimeMillis() - startTime));
        kafKaProducerService.sendKafkaLogMessage(KafKaTopicEnum.CRM_REQUEST_LOG.getTopic(), JSON.toJSONString(sysRequestLogBO));
    }

    /**
     * 异常通知
     *
     * @param point
     */
    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void serviceAspect(JoinPoint point, Exception e) {
        sysRequestLogBO.setErrorMsg(e.getMessage());
        kafKaProducerService.sendKafkaLogMessage(KafKaTopicEnum.CRM_REQUEST_LOG.getTopic(), JSON.toJSONString(sysRequestLogBO));
    }
}
