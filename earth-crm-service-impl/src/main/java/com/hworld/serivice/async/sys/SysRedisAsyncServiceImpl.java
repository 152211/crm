package com.hworld.serivice.async.sys;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.hworld.constants.RedisConstants;
import com.hworld.service.api.async.sys.SysRedisAsyncService;
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
public class SysRedisAsyncServiceImpl implements SysRedisAsyncService {

    @Autowired
    private SysRedisService sysRedisService;

    /**
     * 批量异步删除用户权限缓存
     *
     * @param userIdList
     */
    @Override
    public void delUserPermissionBashAsync(List<Long> userIdList) {
        if (CollectionUtils.isEmpty(userIdList)) {
            return;
        }
        for (Long userId : userIdList) {
//            redisUtil.delKey(RedisConstants.CRM_USER_PERMISSION + userId);
            sysRedisService.delUserPermission(userId);
        }
    }

    /**
     * 批量异步删除用户权限缓存
     *
     * @param userIdList
     */
    @Override
    public void delUserRoleBashAsync(List<Long> userIdList) {
        if (CollectionUtils.isEmpty(userIdList)) {
            return;
        }
        for (Long userId : userIdList) {
//            redisUtil.delKey(RedisConstants.CRM_USER_ROLE + userId);
            sysRedisService.delUserRole(userId);
        }
    }

    /**
     * 批量异步删除用户酒店缓存
     * @param userIdList
     */
    @Override
    public void delUserHotelBashAsync(List<Long> userIdList) {
        if (CollectionUtils.isEmpty(userIdList)) {
            return;
        }
        for (Long userId : userIdList) {
//            redisUtil.delKey(RedisConstants.CRM_USER_ROLE + userId);
            sysRedisService.delUserHotel(userId);
        }
    }

    /**
     * 异步删除用户权限缓存
     *
     * @param userId
     */
    @Override
    public void delUserPermissionAsync(Long userId) {
        sysRedisService.delPermission();
        if (userId == null) {
            return;
        }
        sysRedisService.delUserPermission(userId);
        sysRedisService.delUserPermissionTree(userId);
        sysRedisService.delUserRoute(userId);
    }

    /**
     * 异步删除用户权限缓存
     *
     * @param userId
     */
    @Override
    public void delUserRoleAsync(Long userId) {
        if (userId == null) {
            return;
        }
        sysRedisService.delUserRole(userId);
    }

    /**
     * 异步删除用户缓存
     * @param userId
     */
    @Override
    public void delUserAsync(Long userId) {
        if (userId == null) {
            return;
        }
        sysRedisService.delUserRole(userId);
        sysRedisService.delUserRoleInfo(userId);
        sysRedisService.delUserPermission(userId);
        sysRedisService.delUserPermissionTree(userId);
        sysRedisService.delUserPermissionTree(userId);
        sysRedisService.delUserHotel(userId);
        sysRedisService.delUser(userId);
    }
}
