package com.hworld.serivice.sys;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.hworld.constants.RedisConstants;
import com.hworld.service.api.sys.SysRedisService;
import com.hworld.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 系统redis业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-08-11 15:00:52
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRedisServiceImpl implements SysRedisService {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 删除用户权限缓存
     *
     * @param userId
     */
    @Override
    @CacheInvalidate(name = RedisConstants.CRM_USER_PERMISSION, key = "#userId")
    public void delUserPermission(Long userId) {
        log.info("userId={},delele User Permission redis data", userId);
    }

    @Override
    @CacheInvalidate(name = RedisConstants.CRM_PERMISSION_ALL)
    public void delPermission() {
        log.info("userId={},delele Permission redis data");
    }

    /**
     * 删除用户树结构权限缓存
     *
     * @param userId
     */
    @Override
    @CacheInvalidate(name = RedisConstants.CRM_USER_PERMISSION_TREE, key = "#userId")
    public void delUserPermissionTree(Long userId) {
        log.info("userId={},delele User Permission tree redis data", userId);
    }

    /**
     * 删除用户路由缓存
     *
     * @param userId
     */
    @Override
    @CacheInvalidate(name = RedisConstants.CRM_USER_ROUTE, key = "#userId")
    public void delUserRoute(Long userId) {
        log.info("userId={},delele User route tree redis data", userId);
    }

    /**
     * 删除用户权限缓存
     *
     * @param userId
     */
    @Override
    @CacheInvalidate(name = RedisConstants.CRM_USER_ROLE, key = "#userId")
    public void delUserRole(Long userId) {
        log.info("userId={},delele User Role redis data", userId);
    }

    /**
     * 删除用户权限缓存
     *
     * @param userId
     */
    @Override
    @CacheInvalidate(name = RedisConstants.CRM_USER_ROLE_INFO, key = "#userId")
    public void delUserRoleInfo(Long userId) {
        log.info("userId={},delele User Role redis data", userId);
    }

    /**
     * 删除用户酒店缓存
     *
     * @param userId
     */
    @Override
    @CacheInvalidate(name = RedisConstants.CRM_USER_HOTEL, key = "#userId")
    public void delUserHotel(Long userId) {
        log.info("userId={},delele User Hotel redis data", userId);
    }

    @Override
    @CacheInvalidate(name = RedisConstants.CRM_USER, key = "#userId")
    public void delUser(Long userId) {
        log.info("userId={},delele User Role redis data", userId);
    }

    /**
     * 删除权限缓存
     *
     * @param userId
     */
    @Override
    @CacheInvalidate(name = RedisConstants.CRM_PERMISSION_ALL)
    @CacheInvalidate(name = RedisConstants.CRM_USER_PERMISSION, key = "#userId")
    @CacheInvalidate(name = RedisConstants.CRM_USER_PERMISSION_TREE, key = "#userId")
    @CacheInvalidate(name = RedisConstants.CRM_USER_ROUTE, key = "#userId")
    public void delPermission(Long userId) {
    }
}
