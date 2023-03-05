package com.hworld.service.api.sys;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.hworld.constants.RedisConstants;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * 系统redis业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-08-11 15:00:52
 **/
public interface SysRedisService {
    /**
     * 删除用户权限缓存
     *
     * @param userId
     */
    void delUserPermission(Long userId);

    /**
     * 删除权限缓存
     *
     *
     */
    void delPermission();

    /**
     * 删除用户树结构权限缓存
     *
     * @param userId
     */
    void delUserPermissionTree(Long userId);

    /**
     * 删除用户路由缓存
     *
     * @param userId
     */
    void delUserRoute(Long userId);

    /**
     * 删除用户权限缓存
     *
     * @param userId
     */
    void delUserRole(Long userId);

    /**
     * 删除用户权限缓存
     *
     * @param userId
     */
    void delUserRoleInfo(Long userId);

    /**
     * 删除用户酒店缓存
     *
     * @param userId
     */
    void delUserHotel(Long userId);

    /**
     * 删除用户缓存
     *
     * @param userId
     */
    void delUser(Long userId);

    /**
     * 删除权限缓存
     *
     * @param userId
     */
    void delPermission(Long userId);
}
