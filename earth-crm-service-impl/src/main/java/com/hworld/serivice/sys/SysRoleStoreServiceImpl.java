//TODO 作废SysRoleStore表 酒店数据不应该跟角色权限相关联，应该将酒店看作部门 caoyang 2022-07-06

//package com.hworld.serivice.sys;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.hworld.entity.sys.*;
//import com.hworld.enums.sys.StoreTypeEnum;
//import com.hworld.mapper.sys.SysRoleStoreMapper;
//import com.hworld.service.api.sys.SysRoleStoreService;
//import com.hworld.service.api.sys.SysStoreService;
//import com.hworld.utils.MyStringUtils;
//import com.hworld.utils.WrapperUtiles;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * 角色酒店对应关系业务实现
// *
// * @author caoyang
// * @date 2022-02-08 16:00:53
// */
//@Slf4j
//@Service
//@Transactional(rollbackFor = Exception.class)
//public class SysRoleStoreServiceImpl extends ServiceImpl<SysRoleStoreMapper, SysRoleStoreDO> implements SysRoleStoreService {
//
//    @Autowired
//    private SysRoleStoreMapper sysRoleStoreMapper;
//
//    @Autowired
//    private SysStoreService sysStoreService;
//
//    /**
//     * 添加超级管理员角色酒店
//     *
//     * @param storeDO
//     * @param adminRoleList
//     */
//    @Override
//    public void saveAdminRoleStoreBatch(SysStoreDO storeDO, List<SysRoleDO> adminRoleList) {
//        if (CollectionUtils.isEmpty(adminRoleList)) {
//            return;
//        }
//        //1.0.封装SysRolestoreDO参数
//        List<SysRoleStoreDO> roleStoreList = new ArrayList<>();
//        for (SysRoleDO role : adminRoleList) {
//            SysRoleStoreDO roleStore = new SysRoleStoreDO();
//            roleStore.setRoleId(role.getId());
//            roleStore.setRoleCode(role.getRoleCode());
//            roleStore.setDeletedFlag(false);
//            roleStore.setStoreNo(storeDO.getStoreNo());
//            roleStore.setStoreId(storeDO.getId());
//            roleStore.setStoreType(storeDO.getType());
//            roleStore.setStoreName(storeDO.getName());
//            roleStoreList.add(roleStore);
//        }
//        saveBatch(roleStoreList);
//    }
//
//    /**
//     * 根据酒店ID删除角色酒店关联信息
//     *
//     * @param storeId
//     */
//    @Override
//    public Boolean removeByStoreId(Long storeId) {
//        if (storeId == null) {
//            return false;
//        }
//        SysRoleStoreDO roleStoreDO = new SysRoleStoreDO();
//        roleStoreDO.setStoreId(storeId);
//        QueryWrapper<SysRoleStoreDO> queryWrapper = WrapperUtiles.entityToWrapper(roleStoreDO);
//
//        return remove(queryWrapper);
//    }
//
//    /**
//     * 根据roleId删除角色酒店关联信息
//     *
//     * @param roleId
//     */
//    @Override
//    public Boolean removeByRoleId(Long roleId) {
//        if (roleId == null) {
//            return false;
//        }
//        SysRoleStoreDO roleStore = new SysRoleStoreDO();
//        roleStore.setRoleId(roleId);
//        QueryWrapper<SysRoleStoreDO> queryWrapper = WrapperUtiles.entityToWrapper(roleStore);
//
//        return remove(queryWrapper);
//    }
//
//    /**
//     * 根据roleId和StoreIdList删除角色酒店关联信息
//     *
//     * @param roleId
//     * @param storeIdList
//     */
//    @Override
//    public Boolean removeByRoleIdAndStoreIdList(Long roleId, List<Long> storeIdList) {
//        if (roleId == null && CollectionUtils.isEmpty(storeIdList)) {
//            return false;
//        }
//
//        SysRoleStoreDO roleStoreDO = new SysRoleStoreDO();
//        roleStoreDO.setRoleId(roleId);
//        QueryWrapper<SysRoleStoreDO> queryWrapper = WrapperUtiles.entityToWrapper(roleStoreDO);
//        queryWrapper.in("permission_id", storeIdList);
//
//        return remove(queryWrapper);
//    }
//
//    /**
//     * 更新角色酒店关联信息
//     *
//     * @param storeDO
//     */
//    @Override
//    public Boolean updateRoleStore(SysStoreDO storeDO) {
//        QueryWrapper<SysRoleStoreDO> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("store_id", storeDO.getId());
//
//        SysRoleStoreDO soleStore = new SysRoleStoreDO();
//        soleStore.setStoreNo(storeDO.getStoreNo());
//        soleStore.setStoreName(storeDO.getName());
//        soleStore.setStoreType(storeDO.getType());
//
//        return update(soleStore, queryWrapper);
//    }
//
//    /**
//     * 添加角色酒店
//     *
//     * @param role
//     * @param storeIdList
//     */
//    @Override
//    public void saveRoleStoreBatch(SysRoleDO role, List<Long> storeIdList) {
//        if (CollectionUtils.isEmpty(storeIdList) || role == null || role.getId() == null || MyStringUtils.isNullParam(role.getRoleCode())) {
//            return;
//        }
//        //对storeIdID去重
//        Set<Long> storeIdSet = new HashSet<>(storeIdList);
//        List<SysStoreDO> storeList = sysStoreService.listByIds(storeIdSet);
//
//        if (CollectionUtils.isEmpty(storeList)) {
//            return;
//        }
//        List<SysRoleStoreDO> rolestoreList = new ArrayList<>();
//        for (SysStoreDO store : storeList) {
//            SysRoleStoreDO rolestore = new SysRoleStoreDO();
//            rolestore.setRoleId(role.getId());
//            rolestore.setRoleCode(role.getRoleCode());
//            rolestore.setStoreId(store.getId());
//            rolestore.setStoreType(store.getType());
//            rolestore.setStoreNo(store.getStoreNo());
//            rolestore.setStoreName(store.getName());
//            rolestoreList.add(rolestore);
//        }
//        saveBatch(rolestoreList);
//    }
//
//    /**
//     * 更新角色酒店数据
//     *
//     * @param role
//     * @param storeIdList
//     * @param addStoreIdSet
//     * @param removeStoreIdIdSet
//     */
//    @Override
//    public void renewRoleStoreBatch(SysRoleDO role, List<Long> storeIdList, Set<Long> addStoreIdSet, Set<Long> removeStoreIdIdSet) {
//        if (role == null || role.getId() == null || MyStringUtils.isNullParam(role.getRoleCode())) {
//            return;
//        }
//
//        //1.0.查询此角色的酒店信息
//        List<SysRoleStoreDO> roleRoleList = getSysRoleStoreListByRoleId(role.getId());
//        if (CollectionUtils.isEmpty(roleRoleList) && CollectionUtils.isEmpty(storeIdList)) {
//            return;
//        }
//
//        //1.1.oldStoreIdSet,newStoreIdSet 求差集
//        Set<Long> oldStoreIdSet = roleRoleList.stream().map(SysRoleStoreDO::getStoreId).collect(Collectors.toSet());
//        //对storeIdID去重
//        Set<Long> newStoreIdSet = new HashSet<>(storeIdList);
//
//        if (CollectionUtils.isEmpty(oldStoreIdSet) && !CollectionUtils.isEmpty(newStoreIdSet)) {
//            addStoreIdSet = newStoreIdSet;
//        } else if (CollectionUtils.isEmpty(newStoreIdSet) && !CollectionUtils.isEmpty(oldStoreIdSet)) {
//            removeStoreIdIdSet = oldStoreIdSet;
//        } else {
//            // 差集 (oldStoreIdSet - newStoreIdSet)
//            removeStoreIdIdSet = oldStoreIdSet.stream().filter(item -> !newStoreIdSet.contains(item)).collect(Collectors.toSet());
//
//            // 差集 (newStoreIdSet - oldStoreIdSet)
//            addStoreIdSet = newStoreIdSet.stream().filter(item -> !oldStoreIdSet.contains(item)).collect(Collectors.toSet());
//        }
//
//        //1.2.新增角色酒店
//        if (!CollectionUtils.isEmpty(addStoreIdSet)) {
//            saveRoleStoreBatch(role, new ArrayList<>(addStoreIdSet));
//        }
//
//        //1.3.删除角色酒店
//        if (!CollectionUtils.isEmpty(removeStoreIdIdSet)) {
//            removeByRoleIdAndStoreIdList(role.getId(), new ArrayList<>(removeStoreIdIdSet));
//        }
//    }
//
//    /**
//     * 根据RoleId查询角色酒店关联信息
//     *
//     * @param roleId
//     */
//    @Override
//    public List<SysRoleStoreDO> getSysRoleStoreListByRoleId(Long roleId) {
//        SysRoleStoreDO queryDO = new SysRoleStoreDO();
//        queryDO.setRoleId(roleId);
//        queryDO.setDeletedFlag(false);
//        QueryWrapper<SysRoleStoreDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
//
//        return sysRoleStoreMapper.selectList(queryWrapper);
//    }
//}
