package com.hworld.service.api.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hworld.entity.sys.SysHotelDO;
import com.hworld.entity.sys.SysRoleDO;
import com.hworld.entity.sys.SysUserHotelDO;
import com.hworld.vo.resp.sys.SysUserHotelRespVO;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Set;

/**
 * 用户所属酒店关系业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-07-21 16:43:06
 **/
public interface SysUserHotelService extends IService<SysUserHotelDO> {

    /**
     * 查询拥有hotelId父节点管理员权限的用户ID列表
     *
     * @param hoteDO
     * @return
     */
    List<Long> getParentManagerUserIdByHotelDO(SysHotelDO hoteDO);

    /**
     * 查询拥有hotelId管理员权限的用户ID列表
     *
     * @param hotelIdSet
     * @return
     */
    List<Long> getManagerUserIdByHotelIds(Set<Long> hotelIdSet);

    /**
     * 根据userid查询UserHotel列表
     *
     * @param userId
     * @return
     */
    List<SysUserHotelRespVO> getUserHotelByUserId(Long userId);

    /**
     * 批量关联用户与酒店关联关系
     *
     * @param hotelDO
     * @return
     */
    @Async
    Boolean saveManagerUserHotelBatch(SysHotelDO hotelDO);

    /**
     * 批量关联用户与酒店关联关系
     *
     * @param hotelDO
     * @param userIdSet
     * @return
     */
    Boolean saveManagerUserHoteBatch(SysHotelDO hotelDO, Set<Long> userIdSet);

    /**
     *  根据酒店ID删除用户酒店关联数据
     * @param hotelIdSet
     * @return
     */
    Integer deleltByHoteIds(Set<Long> hotelIdSet);
}
