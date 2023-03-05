package com.hworld.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存放string类型
     *
     * @param key     key
     * @param data    数据
     * @param timeout 超时间
     */
    public void setString(String key, String data, Long timeout) {
        try {
            stringRedisTemplate.opsForValue().set(key, data);
            boolean bool;
            if (timeout != null) {
                bool = stringRedisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            log.error("出现异常：{}", e);
        }
    }

    public void doOpsForValue(String key, String val, Long timeOut) {
        try {
            if (timeOut == null) {
                stringRedisTemplate.opsForValue().set(key, val);
            } else {
                stringRedisTemplate.opsForValue().set(key, val, timeOut, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            log.error("出现异常：{}", e);
        }
    }

    public void doOpsForValue(String key, long val, Long timeOut) {
        try {
            if (timeOut == null) {
                redisTemplate.opsForValue().set(key, val);
            } else {
                redisTemplate.opsForValue().set(key, val, timeOut, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            log.error("出现异常：{}",e);
        }
    }

    /**
     * 存放string类型
     *
     * @param key  key
     * @param data 数据
     */
    public void setString(String key, String data) {
        setString(key, data, null);
    }

    /**
     * 根据key查询string类型
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 自增
     *
     * @param key
     * @return
     */
    public Long getAndIncrement(String key) {
        return redisTemplate.boundValueOps(key).increment(1);
    }

    /**
     * @param key      键
     * @param liveTime 过期时间
     * @return
     */
    public Long incr(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();

        //初始设置过期时间
        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {
            entityIdCounter.expire(liveTime, TimeUnit.MILLISECONDS);
        }

        return increment;
    }


    /**
     * 根据对应的key删除key
     *
     * @param key
     */
    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
}