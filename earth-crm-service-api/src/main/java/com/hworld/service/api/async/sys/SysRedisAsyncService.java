package com.hworld.service.api.async.sys;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.hworld.constants.RedisConstants;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * 系统redis业务异步接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-08-11 15:00:52
 **/
public interface SysRedisAsyncService {

    /**
     * 批量异步删除用户权限缓存
     * @param userIdList
     */
    @Async
    void delUserPermissionBashAsync(List<Long> userIdList);

    /**
     * 批量异步删除用户权限缓存
     * @param userIdList
     */
    @Async
    void delUserRoleBashAsync(List<Long> userIdList);

    /**
     * 批量异步删除用户酒店缓存
     * @param userIdList
     */
    @Async
    void delUserHotelBashAsync(List<Long> userIdList);

    /**
     * 异步删除用户权限缓存
     * @param userId
     */
    @Async
    void delUserPermissionAsync(Long userId);

    /**
     * 异步删除用户权限缓存
     * @param userId
     */
    @Async
    void delUserRoleAsync(Long userId);

    /**
     * 异步删除用户缓存
     * @param userId
     */
    @Async
    void delUserAsync(Long userId);
}
