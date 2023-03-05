package com.hworld;

import cn.dev33.satoken.SaManager;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.ZoneId;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
@MapperScan("com.hworld.mapper")
@EnableAsync
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "com.hworld")
public class CrmServerApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC")));
        try {
            ConfigurableApplicationContext context = SpringApplication.run(CrmServerApplication.class, args);
            Environment environment = context.getBean(Environment.class);
            log.debug("访问链接：http://{}:{}", environment.getProperty("spring.cloud.client.ip-address"), environment.getProperty("local.server.port"));
//            System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
        } catch (Exception e) {
            log.error("[AppDingTalk] -> main:启动异常!error:{}", e);
        }
    }
}
