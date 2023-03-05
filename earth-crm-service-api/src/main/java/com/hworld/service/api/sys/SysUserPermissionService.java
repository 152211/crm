package com.hworld.service.api.sys;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hworld.entity.sys.*;

import java.util.List;
import java.util.Set;

/**
 * 用户权限对应关系业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2021-11-30 11:22:10
 **/
public interface SysUserPermissionService extends IService<SysUserPermissionDO> {

    /**
     * 根据userId查询用户权限信息
     *
     * @param userId
     * @return
     */
    List<SysUserPermissionDO> selectUserPermissionByUserId(Long userId);

    /**
     * 查询用户权限信息
     *
     * @param queryDO
     * @return
     */
    List<SysUserPermissionDO> selectUserPermission(SysUserPermissionDO queryDO);

    /**
     * 根据userId查询用户权限code
     *
     * @param userId
     * @return
     */
    List<String> selectUserPermissionCodeByUserId(Long userId);

    /**
     * 批量添加用户权限
     *
     * @param permissionDO
     * @param roleList
     */
    void saveUserPermissionBatch(SysPermissionDO permissionDO, List<SysRoleDO> roleList);

    /**
     * 批量添加用户权限
     *
     * @param userList
     * @param permissionIdSet
     */
    void saveUserPermissionBatch(List<SysUserDO> userList, Set<Long> permissionIdSet);

    /**
     * 批量添加用户权限
     *
     * @param userId
     * @param roleIdList
     */
    Boolean saveUserPermissionBatch(Long userId, List<Long> roleIdList);

    /**
     * 根据权限ID删除用户权限关联信息
     *
     * @param permissionId
     */
    Boolean removeByPermissionId(Long permissionId);

    /**
     * 根据UserId删除用户权限关联信息
     *
     * @param userId
     */
    Boolean removeByUserId(Long userId);

    /**
     * 根据roleId删除用户权限关联信息
     *
     * @param roleId
     */
    Boolean removeByRoleId(Long roleId);

    /**
     * 根据userIdSet permissionIdSet删除用户权限关联信息
     *
     * @param userIdSet
     * @param permissionIdSet
     */
    Boolean removeBatch(Set<Long> userIdSet, Set<Long> permissionIdSet);

    /**
     * 根据userId roleIdSet删除用户权限关联信息
     *
     * @param userId
     * @param roleIdSet
     */
    Boolean removeBatch(Long userId, Set<Long> roleIdSet);


    /**
     * 更新用户权限
     *
     * @param role
     * @param addPermissionIdSet
     * @param removePermissionIdSet
     */
    void renewUserPermissionBatch(SysRoleDO role, Set<Long> addPermissionIdSet, Set<Long> removePermissionIdSet);

    /**
     * 更新用户角色关联信息
     *
     * @param userId
     * @param addRoleIdSet
     * @param removeRoleIdSet
     * @return
     */
    void renewUserPermissionBatch(Long userId, Set<Long> addRoleIdSet, Set<Long> removeRoleIdSet);
}
