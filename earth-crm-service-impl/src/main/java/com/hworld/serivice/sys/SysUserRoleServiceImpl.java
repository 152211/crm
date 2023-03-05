package com.hworld.serivice.sys;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.constants.RedisConstants;
import com.hworld.entity.sys.*;
import com.hworld.mapper.sys.SysUserRoleMapper;
import com.hworld.service.api.sys.SysRoleService;
import com.hworld.service.api.sys.SysUserRoleService;
import com.hworld.service.api.sys.SysUserService;
import com.hworld.utils.MyStringUtils;
import com.hworld.utils.RedisUtil;
import com.hworld.utils.WrapperUtiles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户角色对应关系业务实现
 *
 * @author caoyang
 * @date 2021-11-30 11:22:10
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleDO> implements SysUserRoleService {

    @Autowired
    public SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    public SysUserService sysUserService;

    @Autowired
    public SysRoleService sysRoleService;

    @Autowired
    public RedisUtil redisUtil;

    /**
     * 根据userId 查询用户角色信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysUserRoleDO> selectListByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        QueryWrapper<SysUserRoleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("deleted_flag", 0);
        return sysUserRoleMapper.selectList(queryWrapper);
    }

    /**
     * 根据userId 查询用户角色code
     *
     * @param userId
     * @return
     */
    @Override
    @Cached(area = "default", name = RedisConstants.CRM_USER_ROLE, key = "#userId", expire = RedisConstants.REDIS_EXPIRE, cacheType = CacheType.REMOTE)
    public List<String> selectRoleCodeByUserId(Long userId) {
        if (userId == null) {
            return null;
        }

//        //1.0.redis用户权限code
//        String permission = redisUtil.getString(RedisConstants.CRM_USER_ROLE + userId);
//        if (MyStringUtils.isNotNullParam(permission)) {
//            String[] permissionArray = permission.split(",");
//            if (permissionArray != null && permissionArray.length > 0) {
//                return Arrays.asList(permissionArray);
//            }
//        }

        //1.1.查询mysql用户权限code
        QueryWrapper<SysUserRoleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("deleted_flag", 0);
        queryWrapper.select("role_code");
        List<SysUserRoleDO> list = sysUserRoleMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        //1.2.去重
        Set<String> roleCodeSet = list.stream().map(SysUserRoleDO::getRoleCode).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(roleCodeSet)) {
            return null;
        }

//        //1.3.添加到redis中
//        StringBuilder roleCodeSb = new StringBuilder();
//        for (String roleCode : roleCodeSet) {
//            roleCodeSb.append(roleCode).append(",");
//        }
//        String roleCodeStr = String.valueOf(roleCodeSb).substring(0, roleCodeSb.length() - 1);
//        redisUtil.setString(RedisConstants.CRM_USER_ROLE + userId, roleCodeStr, 600000L);

        return new ArrayList<>(roleCodeSet);
    }

    /**
     * 根据roleId 查询用户角色信息
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysUserRoleDO> selectListByRoleId(Long roleId) {
        if (roleId == null) {
            return null;
        }
        QueryWrapper<SysUserRoleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        queryWrapper.eq("deleted_flag", 0);
        return sysUserRoleMapper.selectList(queryWrapper);
    }

    /**
     * 根据roleId集合 查询userId集合（去重）
     *
     * @param roleIdList
     * @return
     */
    @Override
    public List<Long> selectUserIdListByRoleId(List<Long> roleIdList) {

        if (CollectionUtils.isEmpty(roleIdList)) {
            return null;
        }

        Set<Long> userIdAllSet = new HashSet<>();
        for (Long roleId : roleIdList) {
            List<SysUserRoleDO> userRoleList = selectListByRoleId(roleId);
            Set<Long> userIdSet = userRoleList.stream().map(SysUserRoleDO::getUserId).collect(Collectors.toSet());
            if (!CollectionUtils.isEmpty(userIdSet)) {
                userIdAllSet.addAll(userIdSet);
            }
        }
        return new ArrayList<>(userIdAllSet);
    }

    /**
     * 获取是超级管理员角色的用户ID列表
     *
     * @return
     */
    @Override
    public List<Long> getAdminRoleUser() {
        List<SysRoleDO> adminRoleList = sysRoleService.getAdminRoleList();
        if (adminRoleList == null) {
            return null;
        }
        Set<Long> adminRoleIdSet = adminRoleList.stream().map(SysRoleDO::getId).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(adminRoleIdSet)) {
            return null;
        }
        return selectUserIdListByRoleId(new ArrayList<>(adminRoleIdSet));
    }

    /**
     * 根据roleId集合 查询user集合（去重）
     *
     * @param roleIdList
     * @return
     */
    @Override
    public List<SysUserDO> selectUserListByRoleId(List<Long> roleIdList) {
        List<Long> userIdList = selectUserIdListByRoleId(roleIdList);
        if (CollectionUtils.isEmpty(userIdList)) {
            return null;
        }
        return sysUserService.listByIds(userIdList);
    }

    /**
     * 根据roleId删除用户角色关联信息
     *
     * @param roleId
     */
    @Override
    public Boolean removeByRoleId(Long roleId) {
        if (roleId == null) {
            return false;
        }
        QueryWrapper<SysUserRoleDO> queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", roleId);

        return remove(queryWrapper);
    }

    /**
     * 根据UserId删除用户角色关联信息
     *
     * @param userId
     */
    @Override
    public Boolean removeByUserId(Long userId) {
        if (userId == null) {
            return false;
        }
        QueryWrapper<SysUserRoleDO> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);

        return remove(queryWrapper);
    }

    /**
     * 根据roleId删除用户角色关联信息
     *
     * @param roleIdSet
     */
    @Override
    public Boolean removeByUserIdAndRoleIdSet(Long userId, Set<Long> roleIdSet) {
        if (userId == null || CollectionUtils.isEmpty(roleIdSet)) {
            return false;
        }
        SysUserRoleDO userRole = new SysUserRoleDO();
        userRole.setUserId(userId);
        QueryWrapper<SysUserRoleDO> queryWrapper = WrapperUtiles.entityToWrapper(userRole);
        queryWrapper.in("role_id", roleIdSet);

        return remove(queryWrapper);
    }

    /**
     * 批量添加用户角色关联信息
     *
     * @param userId
     * @param roleList
     */
    @Override
    public Boolean saveBatch(Long userId, List<SysRoleDO> roleList) {
        if (userId == null || CollectionUtils.isEmpty(roleList)) {
            return false;
        }
        List<SysUserRoleDO> userRoleList = new ArrayList<>();
        for (SysRoleDO role : roleList) {
            SysUserRoleDO userRole = new SysUserRoleDO();
            userRole.setRoleId(role.getId());
            userRole.setRoleCode(role.getRoleCode());
            userRole.setUserId(userId);
            userRoleList.add(userRole);
        }

        return saveBatch(userRoleList);
    }

    /**
     * 批量添加用户角色关联信息
     *
     * @param userId
     * @param roleIdSet
     */
    @Override
    public Boolean saveBatch(Long userId, Set<Long> roleIdSet) {
        if (userId == null || CollectionUtils.isEmpty(roleIdSet)) {
            return false;
        }
        List<SysRoleDO> roleList = sysRoleService.listByIds(roleIdSet);

        if (CollectionUtils.isEmpty(roleList)) {
            return false;
        }
        return saveBatch(userId, roleList);
    }

    /**
     * 更新用户角色关联信息
     *
     * @param userId
     * @param roleIdList
     * @param addRoleIdSet
     * @param removeRoleIdSet
     * @return
     */
    @Override
    public void renewUserRoleBatch(Long userId, List<Long> roleIdList, Set<Long> addRoleIdSet, Set<Long> removeRoleIdSet) {
        if (userId == null) {
            return;
        }

        //1.0.查询此用户的角色
        List<SysUserRoleDO> userRoleList = selectListByUserId(userId);
        if (CollectionUtils.isEmpty(userRoleList) && CollectionUtils.isEmpty(roleIdList)) {
            return;
        }

        //1.1.对oldPermissionIdSet,newPermissionIdSet 求差集
        Set<Long> oldRoleIdSet = userRoleList.stream().map(SysUserRoleDO::getRoleId).collect(Collectors.toSet());
        //对roleIdList去重
        Set<Long> newRoleIdSet = new HashSet<>(roleIdList);
        if (CollectionUtils.isEmpty(oldRoleIdSet) && !CollectionUtils.isEmpty(newRoleIdSet)) {
            addRoleIdSet.addAll(newRoleIdSet);
        } else if (CollectionUtils.isEmpty(newRoleIdSet) && !CollectionUtils.isEmpty(oldRoleIdSet)) {
            removeRoleIdSet.addAll(oldRoleIdSet);
        } else {
            // 差集 (oldPermissionIdSet - newPermissionIdSet)
            removeRoleIdSet.addAll(oldRoleIdSet.stream().filter(item -> !newRoleIdSet.contains(item)).collect(Collectors.toSet()));

            // 差集 (newPermissionIdSet - oldPermissionIdSet)
            addRoleIdSet.addAll(newRoleIdSet.stream().filter(item -> !oldRoleIdSet.contains(item)).collect(Collectors.toSet()));
        }

        //1.2.新增角色权限
        if (!CollectionUtils.isEmpty(addRoleIdSet)) {
            saveBatch(userId, addRoleIdSet);
        }

        //1.3.删除角色权限
        if (!CollectionUtils.isEmpty(removeRoleIdSet)) {
            removeByUserIdAndRoleIdSet(userId, removeRoleIdSet);
        }
    }
}
