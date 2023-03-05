package com.hworld.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.hworld.base.BaseResponse;
import com.hworld.constants.RedisConstants;
import com.hworld.entity.sys.SysUserDO;
import com.hworld.http.WebContext;
import com.hworld.utils.DateUtil;
import com.hworld.utils.MyStringUtils;
import com.hworld.utils.ReflectionUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 多语言翻译
 *
 * @Author caoyang
 * @Create 2022-01-21 12:03
 **/
@Component
@Aspect
@Log4j2
public class TimeDateAspect {
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    HttpServletResponse httpServletResponse;

    /**
     * 对所有接口做切面
     */
    @Pointcut("execution(public * com.hworld.controller..*.*(..))")
    public void timeDateAspectCut() {
    }

    /**
     * 前置通知将 当前时区转为系统时区
     *
     * @param joinPoint
     * @param reqVO
     */
    @Before("timeDateAspectCut() && args(reqVO)")
    public void doBefore(JoinPoint joinPoint, Object reqVO) {
        try {
            if (Objects.nonNull(reqVO)) {
                String timeZone = getTimeZone();
                String dstTimeZoneId = TimeZone.getDefault().getDisplayName();
                log.info("doBefore:请求时区,{}", timeZone);
                log.info("doBefore:系统时区,{}", dstTimeZoneId);
                log.info("doBefore:目标时区,{}", dstTimeZoneId);
                if (reqVO.getClass().isArray()) {
                    int arrLength = Array.getLength(reqVO);
                    for (int i = 0; i < arrLength; i++) {
                        transfer(Array.get(reqVO, i), timeZone, dstTimeZoneId);
                    }
                } else if (reqVO instanceof Collection) {
                    Collection collection = (Collection) reqVO;
                    for (Object object : collection) {
                        transfer(object, timeZone, dstTimeZoneId);
                    }
                } else {
                    transfer(reqVO, timeZone, dstTimeZoneId);
                }
            }

        } catch (Exception e) {
            log.error("出现异常：{}", e);
        }
    }

    /**
     * 后置通知实现
     *
     * @param joinPoint
     * @return
     */
    @AfterReturning(returning = "result", pointcut = "timeDateAspectCut()")
    public void doAfter(JoinPoint joinPoint, Object result) throws IllegalAccessException {
        if (Objects.nonNull(result) && result instanceof BaseResponse) {
            BaseResponse response = (BaseResponse) result;
            if (Objects.nonNull(response.getData())) {
                //系统时区
                String timeZone = TimeZone.getDefault().getDisplayName();
                //目标时区
                String dstTimeZoneId = getTimeZone();
                log.info("doBefore:请求时区,{}", dstTimeZoneId);
                log.info("doAfter:系统时区,{}", timeZone);
                log.info("doBefore:目标时区,{}", dstTimeZoneId);
                if (response.getData().getClass().isArray()) {
                    int arrLength = Array.getLength(response.getData());
                    for (int i = 0; i < arrLength; i++) {
                        transfer(Array.get(response.getData(), i), timeZone, dstTimeZoneId);
                    }
                } else if (response.getData() instanceof Collection) {
                    Collection collection = (Collection) response.getData();
                    for (Object object : collection) {
                        transfer(object, timeZone, dstTimeZoneId);
                    }
                } else {
                    transfer(response.getData(), timeZone, dstTimeZoneId);
                }
            }
        }

    }

    /**
     * @param object
     * @param timeZone      系统时区
     * @param dstTimeZoneId 目标时区
     * @throws IllegalAccessException
     */
    private void transfer(Object object, String timeZone, String dstTimeZoneId) throws IllegalAccessException {
        if (MyStringUtils.isNullParam(timeZone) || MyStringUtils.isNullParam(dstTimeZoneId)) {
            return;
        }
        List<Field> fields = getAllField(object);
        for (Field field : fields) {
            if (Objects.equals(field.getType(), LocalDateTime.class)) {
                LocalDateTime value = getTimeZone(timeZone, dstTimeZoneId, field, object);
                ReflectionUtils.setFieldValue(object, field.getName(), value);
            }
        }
    }

    private String getTimeZone() {
        String timeZone = httpServletRequest.getHeader("timezone");
        if (MyStringUtils.isNotNullParam(timeZone)) {
            return timeZone;
        }
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("timezone".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private static List<Field> getAllField(Object bean) {
        Class clazz = bean.getClass();
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
            fields.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }


    private LocalDateTime getTimeZone(String timeZone, String dstTimeZoneId, Field field, Object object) throws IllegalAccessException {
        try {
            field.setAccessible(true);
            LocalDateTime date = (LocalDateTime) field.get(object);
            if (date == null) {
                return null;
            }
            String time = DateUtil.string2TimezoneDefault(DateUtil.format(DateUtil.asDate(date)), timeZone, dstTimeZoneId);
            return DateUtil.asLocalDateTime(DateUtil.parse(time));
        } catch (IllegalAccessException e) {
            throw e;
        }
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
}
