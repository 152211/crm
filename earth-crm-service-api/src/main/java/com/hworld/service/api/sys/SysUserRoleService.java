package com.hworld.service.api.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hworld.entity.sys.SysRoleDO;
import com.hworld.entity.sys.SysUserDO;
import com.hworld.entity.sys.SysUserRoleDO;

import java.util.List;
import java.util.Set;

/**
 * 用户角色对应关系业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2021-11-30 11:22:10
 **/
public interface SysUserRoleService extends IService<SysUserRoleDO> {

    /**
     * 根据userId 查询用户角色信息
     *
     * @param userId
     * @return
     */
    List<SysUserRoleDO> selectListByUserId(Long userId);

    /**
     * 根据userId 查询用户角色code
     *
     * @param userId
     * @return
     */
    List<String> selectRoleCodeByUserId(Long userId);

    /**
     * 根据roleId 查询用户角色信息
     *
     * @param roleId
     * @return
     */
    List<SysUserRoleDO> selectListByRoleId(Long roleId);

    /**
     * 根据roleId集合 查询userId集合（去重）
     *
     * @param roleIdList
     * @return
     */
    List<Long> selectUserIdListByRoleId(List<Long> roleIdList);

    /**
     * 获取是超级管理员角色的用户ID列表
     *
     * @return
     */
    List<Long> getAdminRoleUser();

    /**
     * 根据roleId集合 查询user集合（去重）
     *
     * @param roleIdList
     * @return
     */
    List<SysUserDO> selectUserListByRoleId(List<Long> roleIdList);


    /**
     * 根据roleId删除用户角色关联信息
     *
     * @param roleId
     */
    Boolean removeByRoleId(Long roleId);

    /**
     * 根据UserId删除用户角色关联信息
     *
     * @param userId
     */
    Boolean removeByUserId(Long userId);

    /**
     * 根据roleId删除用户角色关联信息
     *
     * @param roleIdSet
     */
    Boolean removeByUserIdAndRoleIdSet(Long userId, Set<Long> roleIdSet);

    /**
     * 批量添加用户角色关联信息
     *
     * @param userId
     * @param roleList
     */
    Boolean saveBatch(Long userId, List<SysRoleDO> roleList);

    /**
     * 批量添加用户角色关联信息
     *
     * @param userId
     * @param roleIdSet
     */
    Boolean saveBatch(Long userId, Set<Long> roleIdSet);

    /**
     * 更新用户角色关联信息
     *
     * @param userId
     * @param roleIdList
     * @param addRoleIdSet
     * @param removeRoleIdSet
     * @return
     */
    void renewUserRoleBatch(Long userId, List<Long> roleIdList, Set<Long> addRoleIdSet, Set<Long> removeRoleIdSet);
}
