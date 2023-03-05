package com.hworld.serivice.sys;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.constants.RedisConstants;
import com.hworld.entity.sys.*;
import com.hworld.mapper.sys.SysUserPermissionMapper;
import com.hworld.service.api.async.sys.SysRedisAsyncService;
import com.hworld.service.api.sys.*;
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
 * 用户权限对应关系业务实现
 *
 * @author caoyang
 * @date 2021-11-30 11:22:10
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserPermissionServiceImpl extends ServiceImpl<SysUserPermissionMapper, SysUserPermissionDO> implements SysUserPermissionService {

    @Autowired
    public SysUserPermissionMapper sysUserPermissionMapper;

    @Autowired
    public SysUserRoleService sysUserRoleService;

    @Autowired
    public SysUserService sysUserService;

    @Autowired
    public SysPermissionService sysPermissionService;

    @Autowired
    public SysUserPermissionService sysUserPermissionService;

    @Autowired
    public SysRolePermissionService sysRolePermissionService;

    @Autowired
    public SysRedisAsyncService sysRedisAsyncService;

    @Autowired
    public RedisUtil redisUtil;

    /**
     * 根据userId 工号查询用户权限关联信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysUserPermissionDO> selectUserPermissionByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        QueryWrapper<SysUserPermissionDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("deleted_flag", 0);
        return sysUserPermissionMapper.selectList(queryWrapper);
    }

    /**
     * 查询用户权限信息
     *
     * @param queryDO
     * @return
     */
    @Override
    public List<SysUserPermissionDO> selectUserPermission(SysUserPermissionDO queryDO) {
        QueryWrapper<SysUserPermissionDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        return sysUserPermissionMapper.selectList(queryWrapper);
    }

    @Override
    @Cached(area = "default", name = RedisConstants.CRM_USER_PERMISSION, key = "#userId", expire = RedisConstants.REDIS_EXPIRE, cacheType = CacheType.REMOTE)
    public List<String> selectUserPermissionCodeByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
//        //1.0.redis用户权限code
//        String permission = redisUtil.getString(RedisConstants.CRM_USER_PERMISSION + userId);
//        if (MyStringUtils.isNotNullParam(permission)) {
//            String[] permissionArray = permission.split(",");
//            if (permissionArray != null && permissionArray.length > 0) {
//                return Arrays.asList(permissionArray);
//            }
//        }

        //1.1.查询mysql用户权限code
        QueryWrapper<SysUserPermissionDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("deleted_flag", 0);
        queryWrapper.select("permission_code");
        List<SysUserPermissionDO> list = sysUserPermissionMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        //1.2.去重
        Set<String> permissionCodeSet = list.stream().map(SysUserPermissionDO::getPermissionCode).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(permissionCodeSet)) {
            return null;
        }

//        //1.3.添加到redis中
//        StringBuilder permissionCodeSb = new StringBuilder();
//        for (String permissionCode : permissionCodeSet) {
//            permissionCodeSb.append(permissionCode).append(",");
//        }
//        String permissionCodeStr = String.valueOf(permissionCodeSb).substring(0, permissionCodeSb.length() - 1);
//        redisUtil.setString(RedisConstants.CRM_USER_PERMISSION + userId, permissionCodeStr, 600000L);

        return new ArrayList<>(permissionCodeSet);
    }

    /**
     * 批量添加用户权限
     *
     * @param permissionDO
     * @param roleList
     */
    @Override
    public void saveUserPermissionBatch(SysPermissionDO permissionDO, List<SysRoleDO> roleList) {
        if (CollectionUtils.isEmpty(roleList)) {
            return;
        }
        //去重
        Set<Long> roleIdSet = roleList.stream().map(SysRoleDO::getId).collect(Collectors.toSet());

        //1.0.根据roleId集合 查询userId集合（去重）
        List<Long> userIdList = sysUserRoleService.selectUserIdListByRoleId(new ArrayList<>(roleIdSet));

        if (CollectionUtils.isEmpty(userIdList)) {
            return;
        }
        //1.1.封装SysUserPermissionDO参数
        List<SysUserPermissionDO> userPermissionList = new ArrayList<>();
        for (Long userId : userIdList) {
            SysUserPermissionDO userPermission = new SysUserPermissionDO();
            userPermission.setUserId(userId);
            userPermission.setPermissionId(permissionDO.getId());
            userPermission.setPermissionCode(permissionDO.getPermissionCode());

            userPermissionList.add(userPermission);
        }
        saveBatch(userPermissionList);

        //1.2.删除用户权限缓存数据
        sysRedisAsyncService.delUserPermissionBashAsync(userIdList);
    }

    /**
     * 批量添加用户权限
     *
     * @param userList
     * @param permissionIdSet
     */
    @Override
    public void saveUserPermissionBatch(List<SysUserDO> userList, Set<Long> permissionIdSet) {
        if (CollectionUtils.isEmpty(userList) || CollectionUtils.isEmpty(permissionIdSet)) {
            return;
        }

        //1.0.根据permissionIdSet查询permissionList
        List<SysPermissionDO> permissionList = sysPermissionService.listByIds(permissionIdSet);
        if (CollectionUtils.isEmpty(permissionList)) {
            return;
        }

        //1.1.封装SysUserPermissionDO参数
        List<SysUserPermissionDO> userPermissionList = new ArrayList<>();
        for (SysUserDO user : userList) {
            for (SysPermissionDO permission : permissionList) {
                SysUserPermissionDO userPermission = new SysUserPermissionDO();
                userPermission.setUserId(user.getId());
                userPermission.setPermissionId(permission.getId());
                userPermission.setPermissionCode(permission.getPermissionCode());
                userPermissionList.add(userPermission);
            }
        }
        saveBatch(userPermissionList, 100);
    }

    /**
     * 批量添加用户权限
     *
     * @param userId
     * @param roleIdList
     */
    @Override
    public Boolean saveUserPermissionBatch(Long userId, List<Long> roleIdList) {
        if (userId == null || CollectionUtils.isEmpty(roleIdList)) {
            return false;
        }

        //1.0.根据roleIdList查询权限列表信息
        List<SysRolePermissionDO> rolePermissionList = sysRolePermissionService.getSysRolePermissionList(new HashSet<>(roleIdList));
        if (CollectionUtils.isEmpty(rolePermissionList)) {
            return false;
        }
        Set<Long> permissionIdSet = rolePermissionList.stream().map(SysRolePermissionDO::getPermissionId).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(permissionIdSet)) {
            return false;
        }

        //1.1.根据permissionIdSet查询权限列表信息
        List<SysPermissionDO> permissionList = sysPermissionService.listByIds(permissionIdSet);
        if (CollectionUtils.isEmpty(permissionList)) {
            return false;
        }
        List<SysUserPermissionDO> userPermissionList = new ArrayList<>();
        for (SysPermissionDO permission : permissionList) {
            SysUserPermissionDO userPermission = new SysUserPermissionDO();
            userPermission.setUserId(userId);
            userPermission.setPermissionId(permission.getId());
            userPermission.setPermissionCode(permission.getPermissionCode());

            userPermissionList.add(userPermission);
        }

        return saveBatch(userPermissionList, 100);
    }

    /**
     * 根据权限ID删除用户权限关联信息
     *
     * @param permissionId
     */
    @Override
    public Boolean removeByPermissionId(Long permissionId) {
        if (permissionId == null) {
            return false;
        }
        QueryWrapper<SysUserPermissionDO> queryWrapper = new QueryWrapper();
        queryWrapper.eq("permission_id", permissionId);
        List<SysUserPermissionDO> userPermissionList = sysUserPermissionMapper.selectList(queryWrapper);

        if (CollectionUtils.isEmpty(userPermissionList)) {
            return true;
        }
        //批量异步删除用户权限缓存
        Set<Long> userIdSet = userPermissionList.stream().map(SysUserPermissionDO::getUserId).collect(Collectors.toSet());
        sysRedisAsyncService.delUserPermissionBashAsync(new ArrayList<>(userIdSet));

        return remove(queryWrapper);
    }

    /**
     * 根据UserId删除用户权限关联信息
     *
     * @param userId
     */
    @Override
    public Boolean removeByUserId(Long userId) {
        if (userId == null) {
            return false;
        }
        QueryWrapper<SysUserPermissionDO> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);

        return remove(queryWrapper);
    }

    /**
     * 根据userId删除用户权限关联信息
     *
     * @param roleId
     */
    @Override
    public Boolean removeByRoleId(Long roleId) {
        if (roleId == null) {
            return false;
        }

        //1.0.查询roleId关联的权限列表
        List<SysRolePermissionDO> rolePermissionDOList = sysRolePermissionService.getSysRolePermissionListByRoleId(roleId);
        if (CollectionUtils.isEmpty(rolePermissionDOList)) {
            return true;
        }
        Set<Long> permissionIdSet = rolePermissionDOList.stream().map(SysRolePermissionDO::getPermissionId).collect(Collectors.toSet());

        //1.1.查询roleId关联的用户列表
        List<SysUserRoleDO> userRoleDOList = sysUserRoleService.selectListByRoleId(roleId);
        if (CollectionUtils.isEmpty(userRoleDOList)) {
            return true;
        }
        Set<Long> userIdSet = userRoleDOList.stream().map(SysUserRoleDO::getUserId).collect(Collectors.toSet());

        //1.2.批量删除用户权限信息
        Boolean result = removeBatch(permissionIdSet, userIdSet);

        //1.3.清除用户权限，用户角色缓存数据
        sysRedisAsyncService.delUserPermissionBashAsync(new ArrayList<>(userIdSet));
        sysRedisAsyncService.delUserRoleBashAsync(new ArrayList<>(userIdSet));

        return result;
    }

    /**
     * 根据userIdSet permissionIdSet删除用户权限关联信息
     *
     * @param userIdSet
     * @param permissionIdSet
     */
    @Override
    public Boolean removeBatch(Set<Long> userIdSet, Set<Long> permissionIdSet) {
        if (CollectionUtils.isEmpty(userIdSet) || CollectionUtils.isEmpty(permissionIdSet)) {
            return false;
        }

        QueryWrapper<SysUserPermissionDO> queryWrapper = new QueryWrapper();
        queryWrapper.in("user_id", userIdSet);
        queryWrapper.in("permission_id", permissionIdSet);

        return remove(queryWrapper);
    }

    /**
     * 根据userId roleIdSet删除用户权限关联信息
     *
     * @param userId
     * @param roleIdSet
     */
    @Override
    public Boolean removeBatch(Long userId, Set<Long> roleIdSet) {
        if (userId == null || CollectionUtils.isEmpty(roleIdSet)) {
            return false;
        }

        //1.0.查询角色权限列表
        List<SysRolePermissionDO> rolePermissionList = sysRolePermissionService.getSysRolePermissionList(roleIdSet);
        if (CollectionUtils.isEmpty(rolePermissionList)) {
            return false;
        }
        Set<Long> permissionIdSet = rolePermissionList.stream().map(SysRolePermissionDO::getPermissionId).collect(Collectors.toSet());

        if (CollectionUtils.isEmpty(permissionIdSet)) {
            return false;
        }

        QueryWrapper<SysUserPermissionDO> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.in("permission_id", permissionIdSet);

        return remove(queryWrapper);
    }

    /**
     * 更新用户权限
     *
     * @param role
     * @param addPermissionIdSet
     * @param removePermissionIdSet
     */
    @Override
    public void renewUserPermissionBatch(SysRoleDO role, Set<Long> addPermissionIdSet, Set<Long> removePermissionIdSet) {
        if (role == null || role.getId() == null || MyStringUtils.isNullParam(role.getRoleCode())) {
            return;
        }

        if (CollectionUtils.isEmpty(addPermissionIdSet) && CollectionUtils.isEmpty(removePermissionIdSet)) {
            return;
        }

        //1.0.根据roleId查询用户角色信息
        List<SysUserRoleDO> userRoleList = sysUserRoleService.selectListByRoleId(role.getId());
        if (CollectionUtils.isEmpty(userRoleList)) {
            return;
        }

        Set<Long> userIdSet = userRoleList.stream().map(SysUserRoleDO::getUserId).collect(Collectors.toSet());
        //1.1.根据userIdSet查询userList
        List<SysUserDO> userList = sysUserService.listByIds(userIdSet);
        if (CollectionUtils.isEmpty(userList)) {
            return;
        }

        //1.2.批量删除用户权限
        removeBatch(userIdSet, removePermissionIdSet);

        //1.3.批量添加用户权限
        saveUserPermissionBatch(userList, addPermissionIdSet);

        //1.4.删除redis中的用户角色、权限数据
        sysRedisAsyncService.delUserPermissionBashAsync(new ArrayList<>(userIdSet));
        sysRedisAsyncService.delUserRoleBashAsync(new ArrayList<>(userIdSet));
    }

    /**
     * 更新用户角色关联信息
     *
     * @param userId
     * @param addRoleIdSet
     * @param removeRoleIdSet
     * @return
     */
    @Override
    public void renewUserPermissionBatch(Long userId, Set<Long> addRoleIdSet, Set<Long> removeRoleIdSet) {
        if (userId == null) {
            return;
        }
        //1.0.删除用户权限
        removeBatch(userId, removeRoleIdSet);

        //1.1.添加用户权限
        saveUserPermissionBatch(userId, new ArrayList<>(addRoleIdSet));
    }
}
