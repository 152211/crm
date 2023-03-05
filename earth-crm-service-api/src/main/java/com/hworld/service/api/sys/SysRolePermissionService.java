package com.hworld.service.api.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hworld.entity.sys.SysPermissionDO;
import com.hworld.entity.sys.SysRoleDO;
import com.hworld.entity.sys.SysRolePermissionDO;

import java.util.List;
import java.util.Set;

/**
 * 角色权限对应关系业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2021-11-30 11:22:09
 **/
public interface SysRolePermissionService extends IService<SysRolePermissionDO> {

    /**
     * 添加超级管理员角色权限
     *
     * @param permissionDO
     * @param adminRoleList
     */
    void saveAdminRolePermissionBatch(SysPermissionDO permissionDO, List<SysRoleDO> adminRoleList);

    /**
     * 添加角色权限
     *
     * @param role
     * @param permissionIdList
     */
    void saveRolePermissionBatch(SysRoleDO role, List<Long> permissionIdList);

    /**
     * 更新角色权限
     *
     * @param role
     * @param permissionIdList
     * @param addPermissionIdSet
     * @param removePermissionIdSet
     */
    void renewRolePermissionBatch(SysRoleDO role, List<Long> permissionIdList, Set<Long> addPermissionIdSet, Set<Long> removePermissionIdSet);

    /**
     * 根据权限ID删除角色权限关联信息
     *
     * @param permissionId
     */
    Boolean removeByPermissionId(Long permissionId);

    /**
     * 根据roleId删除角色权限关联信息
     *
     * @param roleId
     */
    Boolean removeByRoleId(Long roleId);

    /**
     * 根据roleId，permissionIdList删除角色权限关联信息
     *
     * @param roleId
     * @param permissionIdList
     */
    Boolean removeByRoleIdAndPermissionIdList(Long roleId, List<Long> permissionIdList);

    /**
     * 根据RoleId查询角色权限关联信息
     *
     * @param roleId
     */
    List<SysRolePermissionDO> getSysRolePermissionListByRoleId(Long roleId);

    /**
     * 根据RoleId查询角色权限关联信息
     *
     * @param roleIdSet
     */
    List<SysRolePermissionDO> getSysRolePermissionList(Set<Long> roleIdSet);
}
