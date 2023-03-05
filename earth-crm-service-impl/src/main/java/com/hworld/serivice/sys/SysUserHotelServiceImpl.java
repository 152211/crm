package com.hworld.serivice.sys;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.base.BaseResponse;
import com.hworld.constants.RedisConstants;
import com.hworld.entity.sys.SysHotelDO;
import com.hworld.entity.sys.SysUserHotelDO;
import com.hworld.mapper.sys.SysUserHotelMapper;
import com.hworld.service.api.async.sys.SysRedisAsyncService;
import com.hworld.service.api.sys.SysHotelService;
import com.hworld.service.api.sys.SysRoleService;
import com.hworld.service.api.sys.SysUserHotelService;
import com.hworld.service.api.sys.SysUserRoleService;
import com.hworld.utils.MyBeanUtils;
import com.hworld.utils.MyStringUtils;
import com.hworld.vo.resp.sys.SysUserHotelRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户所属酒店关系业务实现
 *
 * @author caoyang
 * @date 2022-07-21 16:43:06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserHotelServiceImpl extends ServiceImpl<SysUserHotelMapper, SysUserHotelDO> implements SysUserHotelService {

    @Autowired
    public SysUserHotelMapper sysUserHotelMapper;

    @Autowired
    public SysHotelService sysHotelService;

    @Autowired
    public SysRoleService sysRoleService;

    @Autowired
    public SysUserRoleService sysUserRoleService;

    @Autowired
    public SysRedisAsyncService sysRedisAsyncService;

    /**
     * 查询拥有hotelId父节点管理员权限的用户ID列表
     *
     * @param hoteDO
     * @return
     */
    @Override
    public List<Long> getParentManagerUserIdByHotelDO(SysHotelDO hoteDO) {
        if (hoteDO == null) {
            return null;
        }

        List<SysHotelDO> parentHoteList = sysHotelService.getParentHoteListByDO(hoteDO);
        if (CollectionUtils.isEmpty(parentHoteList)) {
            return null;
        }
        Set<Long> hotelIdSet = parentHoteList.stream().map(SysHotelDO::getId).collect(Collectors.toSet());
        return getManagerUserIdByHotelIds(hotelIdSet);
    }

    /**
     * 查询拥有hotelId管理员权限的用户ID列表
     *
     * @param hotelIdSet
     * @return
     */
    @Override
    public List<Long> getManagerUserIdByHotelIds(Set<Long> hotelIdSet) {
        if (CollectionUtils.isEmpty(hotelIdSet)) {
            return null;
        }
        QueryWrapper<SysUserHotelDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_id");
        queryWrapper.eq("is_manager", 1);
        queryWrapper.eq("deleted_flag", 0);
        queryWrapper.in("hotel_id", hotelIdSet);
        List<SysUserHotelDO> sysUserHotelDOList = sysUserHotelMapper.selectList(queryWrapper);

        if (CollectionUtils.isEmpty(sysUserHotelDOList)) {
            return null;
        }
        Set<Long> userIdSet = sysUserHotelDOList.stream().map(SysUserHotelDO::getUserId).collect(Collectors.toSet());
        return new ArrayList<>(userIdSet);
    }

    /**
     * 根据userid查询UserHotel列表
     *
     * @param userId
     * @return
     */
    @Override
    @Cached(area = "default", name = RedisConstants.CRM_USER_HOTEL, key = "#userId",
            expire = RedisConstants.REDIS_EXPIRE, cacheType = CacheType.REMOTE)
    public List<SysUserHotelRespVO> getUserHotelByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        QueryWrapper<SysUserHotelDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("deleted_flag", 0);
        List<SysUserHotelDO> sysUserHotelDOList = sysUserHotelMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(sysUserHotelDOList)) {
            return null;
        }
        List<SysUserHotelRespVO> respVOList = new ArrayList<>();
        for (SysUserHotelDO sysUserHotelDO : sysUserHotelDOList) {
            respVOList.add(MyBeanUtils.doToDto(sysUserHotelDO, SysUserHotelRespVO.class));
        }
        return respVOList;
    }

    @Override
    public Boolean saveManagerUserHotelBatch(SysHotelDO hotelDO) {
        //需要关联酒店的用户列表
        Set<Long> addHotelUsetIdSet = new HashSet<>();

        //1.0.查询所有拥有该酒店父级,爷爷级酒店管理员权限的用户
        List<Long> userIdList = getParentManagerUserIdByHotelDO(hotelDO);
        if (!CollectionUtils.isEmpty(userIdList)) {
            addHotelUsetIdSet.addAll(userIdList);
        }

        //1.1.查询超级管理员角色的用户
        List<Long> adminUserIdList = sysUserRoleService.getAdminRoleUser();
        if (!CollectionUtils.isEmpty(adminUserIdList)) {
            addHotelUsetIdSet.addAll(adminUserIdList);
        }

        if (CollectionUtils.isEmpty(adminUserIdList)) {
            return true;
        }

        //1.2.异步清空管理员用户酒店缓存
        sysRedisAsyncService.delUserHotelBashAsync(adminUserIdList);

        //1.3.将酒店与addHoteUsetIdSet关联
        return saveManagerUserHoteBatch(hotelDO, addHotelUsetIdSet);
    }

    /**
     * 批量关联用户与酒店关联关系
     *
     * @param hotelDO
     * @param userIdSet
     * @return
     */
    @Override
    public Boolean saveManagerUserHoteBatch(SysHotelDO hotelDO, Set<Long> userIdSet) {
        if (CollectionUtils.isEmpty(userIdSet) || hotelDO == null || hotelDO.getId() == null || MyStringUtils.isNullParam(hotelDO.getHotelCode())) {
            return false;
        }
        List<SysUserHotelDO> sysUserHotelDOList = new ArrayList<>();
        for (Long userId : userIdSet) {
            SysUserHotelDO sysUserHotelDO = new SysUserHotelDO();
            sysUserHotelDO.setHotelId(hotelDO.getId());
            sysUserHotelDO.setHotelNo(hotelDO.getHotelCode());
            sysUserHotelDO.setUserId(userId);
            sysUserHotelDO.setIsManager(true);
            sysUserHotelDOList.add(sysUserHotelDO);
        }
        return saveBatch(sysUserHotelDOList, 200);
    }

    /**
     * 根据酒店ID删除用户酒店关联数据
     *
     * @param hotelIdSet
     * @return
     */
    @Override
    public Integer deleltByHoteIds(Set<Long> hotelIdSet) {
        if (CollectionUtils.isEmpty(hotelIdSet)) {
            return 0;
        }
        QueryWrapper<SysUserHotelDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("hotel_id", hotelIdSet);
        return sysUserHotelMapper.delete(queryWrapper);
    }
}
