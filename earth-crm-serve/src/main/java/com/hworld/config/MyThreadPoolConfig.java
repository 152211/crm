package com.hworld.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * springboot 整合线程池
 */
@EnableAsync
@Configuration
public class MyThreadPoolConfig {

    /**
     * 线程核心数
     */
    @Value("${earthCrm.thread.corePoolSize}")
    private int corePoolSize;
    /**
     * 线程最大数
     */
    @Value("${earthCrm.thread.maxPoolSize}")
    private int maxPoolSize ;
    /**
     *任务容量
     */
    @Value("${earthCrm.thread.queueCapacity}")
    private int queueCapacity;
    /**
     * 允许空闲时间，默认60
     */
    @Value("${earthCrm.thread.keepAlive}")
    private int keepAlive;

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAlive);
        threadPoolTaskExecutor.setThreadNamePrefix("taskThread-");
        //设置拒绝策略 当线程数达到最大时，如何处理新任务
        //CallerRunsPolicy 不由线程池中线程执行，由调用者所在线程执行
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return threadPoolTaskExecutor;
    }
}