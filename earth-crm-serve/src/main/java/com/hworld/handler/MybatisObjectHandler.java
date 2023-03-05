package com.hworld.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hworld.http.WebContext;
import com.hworld.utils.MyStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

/**
 * @author caoyang
 * @date 2021/07/16 13:38
 * @directions 自动注入createdOn, modifiedOn
 */
@Slf4j
@Component
public class MybatisObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill...");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (WebContext.getUserId() != null) {
            this.setFieldValByName("createdBy", WebContext.getUserId(), metaObject);
            this.setFieldValByName("modifiedBy", WebContext.getUserId(), metaObject);
        }
        if (MyStringUtils.isNotNullParam(WebContext.getUserName())) {
            this.setFieldValByName("createdName", WebContext.getUserName(), metaObject);
            this.setFieldValByName("modifiedName", WebContext.getUserName(), metaObject);
        }
        if (WebContext.getUserId() == null) {
            this.setFieldValByName("createdBy", 1, metaObject);
            this.setFieldValByName("modifiedBy", 1, metaObject);
            this.setFieldValByName("createdName", "System", metaObject);
            this.setFieldValByName("modifiedName", "System", metaObject);
        }
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName("deletedFlag", false, metaObject);
        this.setFieldValByName("createdOn", now, metaObject);
        this.setFieldValByName("modifiedOn", now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");
        if (WebContext.getUserId() != null) {
            this.setFieldValByName("modifiedBy", WebContext.getUserId(), metaObject);
        }
        if (MyStringUtils.isNotNullParam(WebContext.getUserName())) {
            this.setFieldValByName("modifiedName", WebContext.getUserName(), metaObject);
        }
        if (WebContext.getUserId() == null) {
            this.setFieldValByName("modifiedBy", 1, metaObject);
            this.setFieldValByName("modifiedName", "System", metaObject);
        }
        this.setFieldValByName("modifiedOn", LocalDateTime.now(), metaObject);
    }
}