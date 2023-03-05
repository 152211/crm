package com.hworld.serivice.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.entity.sys.SysPermissionDO;
import com.hworld.entity.sys.SysRoleDO;
import com.hworld.entity.sys.SysRolePermissionDO;
import com.hworld.mapper.sys.SysRolePermissionMapper;
import com.hworld.service.api.sys.SysPermissionService;
import com.hworld.service.api.sys.SysRolePermissionService;
import com.hworld.service.api.sys.SysRoleService;
import com.hworld.utils.MyStringUtils;
import com.hworld.utils.WrapperUtiles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.security.Permission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色权限对应关系业务实现
 *
 * @author caoyang
 * @date 2021-11-30 11:22:09
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermissionDO> implements SysRolePermissionService {

    @Autowired
    public SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    public SysRoleService sysRoleService;

    @Autowired
    public SysPermissionService sysPermissionService;

    /**
     * 添加超级管理员角色权限
     *
     * @param permissionDO
     */
    @Override
    public void saveAdminRolePermissionBatch(SysPermissionDO permissionDO, List<SysRoleDO> adminRoleList) {

        if (CollectionUtils.isEmpty(adminRoleList)) {
            return;
        }
        //1.0.封装SysRolePermissionDO参数
        List<SysRolePermissionDO> rolePermissionList = new ArrayList<>();
        for (SysRoleDO role : adminRoleList) {
            SysRolePermissionDO rolePermission = new SysRolePermissionDO();
            rolePermission.setRoleId(role.getId());
            rolePermission.setRoleCode(role.getRoleCode());
            rolePermission.setPermissionId(permissionDO.getId());
            rolePermission.setPermissionCode(permissionDO.getPermissionCode());
            rolePermission.setAuthType("A");
            rolePermission.setDeletedFlag(false);
            rolePermissionList.add(rolePermission);
        }
        saveBatch(rolePermissionList);
    }

    /**
     * 添加角色权限
     *
     * @param role
     * @param permissionIdList
     */
    @Override
    public void saveRolePermissionBatch(SysRoleDO role, List<Long> permissionIdList) {
        if (CollectionUtils.isEmpty(permissionIdList) || role == null || role.getId() == null || MyStringUtils.isNullParam(role.getRoleCode())) {
            return;
        }
        //对permissionIdID去重
        Set<Long> permissionIdSet = new HashSet<>(permissionIdList);
        List<SysPermissionDO> permissionList = sysPermissionService.listByIds(permissionIdSet);

        if (CollectionUtils.isEmpty(permissionList)) {
            return;
        }
        List<SysRolePermissionDO> rolePermissionList = new ArrayList<>();
        for (SysPermissionDO permission : permissionList) {
            SysRolePermissionDO rolePermission = new SysRolePermissionDO();
            rolePermission.setRoleId(role.getId());
            rolePermission.setRoleCode(role.getRoleCode());
            rolePermission.setPermissionId(permission.getId());
            rolePermission.setPermissionCode(permission.getPermissionCode());
            rolePermission.setAuthType("A");
            rolePermissionList.add(rolePermission);
        }
        saveBatch(rolePermissionList);
    }

    /**
     * 更新角色权限
     *
     * @param role
     * @param permissionIdList
     */
    @Override
    public void renewRolePermissionBatch(SysRoleDO role, List<Long> permissionIdList, Set<Long> addPermissionIdSet, Set<Long> removePermissionIdSet) {
        if (role == null || role.getId() == null || MyStringUtils.isNullParam(role.getRoleCode())) {
            return;
        }

        //1.0.查询此角色的权限
        List<SysRolePermissionDO> rolePermissionList = getSysRolePermissionListByRoleId(role.getId());
        if (CollectionUtils.isEmpty(rolePermissionList) && CollectionUtils.isEmpty(permissionIdList)) {
            return;
        }

        //1.1.对oldPermissionIdSet,newPermissionIdSet 求差集
        Set<Long> oldPermissionIdSet = rolePermissionList.stream().map(SysRolePermissionDO::getPermissionId).collect(Collectors.toSet());
        //对permissionIdID去重
        Set<Long> newPermissionIdSet = new HashSet<>(permissionIdList);

        if (CollectionUtils.isEmpty(oldPermissionIdSet) && !CollectionUtils.isEmpty(newPermissionIdSet)) {
            addPermissionIdSet.addAll(newPermissionIdSet);
        } else if (CollectionUtils.isEmpty(newPermissionIdSet) && !CollectionUtils.isEmpty(oldPermissionIdSet)) {
            removePermissionIdSet.addAll(oldPermissionIdSet);
        } else {
            // 差集 (oldPermissionIdSet - newPermissionIdSet)
            removePermissionIdSet.addAll(oldPermissionIdSet.stream().filter(item -> !newPermissionIdSet.contains(item)).collect(Collectors.toSet()));

            // 差集 (newPermissionIdSet - oldPermissionIdSet)
            addPermissionIdSet.addAll(newPermissionIdSet.stream().filter(item -> !oldPermissionIdSet.contains(item)).collect(Collectors.toSet()));
        }

        //1.2.新增角色权限
        if (!CollectionUtils.isEmpty(addPermissionIdSet)) {
            saveRolePermissionBatch(role, new ArrayList<>(addPermissionIdSet));
        }

        //1.3.删除角色权限
        if (!CollectionUtils.isEmpty(removePermissionIdSet)) {
            removeByRoleIdAndPermissionIdList(role.getId(), new ArrayList<>(removePermissionIdSet));
        }
    }

    /**
     * 根据权限ID删除角色权限关联信息
     *
     * @param permissionId
     */
    @Override
    public Boolean removeByPermissionId(Long permissionId) {
        if (permissionId == null) {
            return false;
        }
        SysRolePermissionDO rolePermission = new SysRolePermissionDO();
        rolePermission.setPermissionId(permissionId);
        QueryWrapper<SysRolePermissionDO> queryWrapper = WrapperUtiles.entityToWrapper(rolePermission);

        return remove(queryWrapper);
    }

    /**
     * 根据roleId删除角色权限关联信息
     *
     * @param roleId
     */
    @Override
    public Boolean removeByRoleId(Long roleId) {

        if (roleId == null) {
            return false;
        }
        SysRolePermissionDO rolePermission = new SysRolePermissionDO();
        rolePermission.setRoleId(roleId);
        QueryWrapper<SysRolePermissionDO> queryWrapper = WrapperUtiles.entityToWrapper(rolePermission);

        return remove(queryWrapper);
    }

    /**
     * 根据roleId，permissionIdList删除角色权限关联信息
     *
     * @param roleId
     * @param permissionIdList
     */
    @Override
    public Boolean removeByRoleIdAndPermissionIdList(Long roleId, List<Long> permissionIdList) {

        if (roleId == null && CollectionUtils.isEmpty(permissionIdList)) {
            return false;
        }

        SysRolePermissionDO rolePermission = new SysRolePermissionDO();
        rolePermission.setRoleId(roleId);
        QueryWrapper<SysRolePermissionDO> queryWrapper = WrapperUtiles.entityToWrapper(rolePermission);
        queryWrapper.in("permission_id", permissionIdList);

        return remove(queryWrapper);
    }

    /**
     * 根据RoleId查询角色权限关联信息
     *
     * @param roleId
     */
    @Override
    public List<SysRolePermissionDO> getSysRolePermissionListByRoleId(Long roleId) {
        SysRolePermissionDO queryDO = new SysRolePermissionDO();
        queryDO.setRoleId(roleId);
        queryDO.setDeletedFlag(false);
        QueryWrapper<SysRolePermissionDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);

        return sysRolePermissionMapper.selectList(queryWrapper);
    }

    /**
     * 根据RoleId查询角色权限关联信息
     *
     * @param roleIdSet
     */
    @Override
    public List<SysRolePermissionDO> getSysRolePermissionList(Set<Long> roleIdSet) {
        if (CollectionUtils.isEmpty(roleIdSet)) {
            return null;
        }
        QueryWrapper<SysRolePermissionDO> queryWrapper = new QueryWrapper();
        queryWrapper.in("role_id", roleIdSet);

        return sysRolePermissionMapper.selectList(queryWrapper);
    }
}
