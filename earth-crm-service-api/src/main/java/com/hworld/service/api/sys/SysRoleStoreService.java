//TODO 作废SysRoleStore表 酒店数据不应该跟角色权限相关联，应该将酒店看作部门 caoyang 2022-07-06

//package com.hworld.service.api.sys;
//
//
//import com.baomidou.mybatisplus.extension.service.IService;
//import com.hworld.entity.sys.*;
//
//import java.util.List;
//import java.util.Set;
//
///**
// * 角色酒店对应关系业务接口
// *
// * @author caoyang
// * @version 1.0
// * @date 2022-02-08 16:00:53
// **/
//public interface SysRoleStoreService extends IService<SysRoleStoreDO> {
//
//    /**
//     * 添加超级管理员角色酒店
//     *
//     * @param storeDO
//     * @param adminRoleList
//     */
//    void saveAdminRoleStoreBatch(SysStoreDO storeDO, List<SysRoleDO> adminRoleList);
//
//    /**
//     * 根据酒店ID删除角色酒店关联信息
//     *
//     * @param storeId
//     */
//    Boolean removeByStoreId(Long storeId);
//
//    /**
//     * 根据roleId删除角色酒店关联信息
//     *
//     * @param roleId
//     */
//    Boolean removeByRoleId(Long roleId);
//
//    /**
//     * 根据roleId和StoreIdList删除角色酒店关联信息
//     *
//     * @param roleId
//     * @param storeIdList
//     */
//    Boolean removeByRoleIdAndStoreIdList(Long roleId, List<Long> storeIdList);
//
//    /**
//     * 更新角色酒店关联信息
//     *
//     * @param storeDO
//     */
//    Boolean updateRoleStore(SysStoreDO storeDO);
//
//
//    /**
//     * 添加角色酒店
//     *
//     * @param role
//     * @param storeIdList
//     */
//    void saveRoleStoreBatch(SysRoleDO role, List<Long> storeIdList);
//
//    /**
//     * 更新角色酒店数据
//     *
//     * @param role
//     * @param storeIdList
//     * @param addStoreIdSet
//     * @param removeStoreIdIdSet
//     */
//    void renewRoleStoreBatch(SysRoleDO role, List<Long> storeIdList, Set<Long> addStoreIdSet, Set<Long> removeStoreIdIdSet);
//
//    /**
//     * 根据RoleId查询角色酒店关联信息
//     *
//     * @param roleId
//     */
//    List<SysRoleStoreDO> getSysRoleStoreListByRoleId(Long roleId);
//}
