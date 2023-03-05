package com.hworld.service.api.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hworld.base.BasePagedResponse;
import com.hworld.base.BaseReqPage;
import com.hworld.base.BaseResponse;
import com.hworld.entity.sys.SysHotelDO;
import com.hworld.vo.req.sys.SysHotelReqVO;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * 酒店管理维护业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-02-08 16:00:53
 **/
public interface SysHotelService extends IService<SysHotelDO> {

    /**
     * 添加酒店信息
     *
     * @param reqVO
     * @return
     */
    BaseResponse addHotel(SysHotelReqVO reqVO);

    /**
     * 批量添加酒店信息
     *
     * @param
     * @return
     */
    BaseResponse addHotelBatch(List<SysHotelDO> addHotelList);

    /**
     * 删除酒店信息
     *
     * @param reqVO
     * @return
     */
    BaseResponse deleltHotel(SysHotelReqVO reqVO);

    /**
     * 批量删除酒店信息
     *
     * @param hotelCodeSet
     * @return
     */
    BaseResponse deleltHotelBatch(Set<String> hotelCodeSet);


    /**
     * 更新酒店信息
     *
     * @param reqVO
     * @return
     */
    BaseResponse updateHotel(SysHotelReqVO reqVO);

    /**
     * 批量更新酒店信息
     *
     * @param list
     * @return
     */
    BaseResponse updateHotelBatch(List<SysHotelDO> list);

    /**
     * 获取酒店列表(不分页)
     *
     * @param reqVO
     * @return
     */
    BaseResponse getHotelList(SysHotelReqVO reqVO);

    /**
     * 获取酒店树结构
     *
     * @param reqVO
     * @return
     */
    BaseResponse getHotelTree(SysHotelReqVO reqVO);

    /**
     * 查询酒店列表(分页)
     *
     * @param reqPage
     * @return
     */
    BasePagedResponse getHotelPage(BaseReqPage<SysHotelReqVO> reqPage);

    /**
     * 获取酒店详情
     *
     * @param reqVO
     * @return
     */
    BaseResponse getHotelOne(SysHotelReqVO reqVO);

    /**
     * 保存酒店数据
     *
     * @param sysHotelDO
     * @return
     */
    BaseResponse saveHotel(SysHotelDO sysHotelDO);

    /**
     * 批量保存酒店数据
     *
     * @param sysHotelDOList
     * @return
     */
    BaseResponse saveHotelBatch(List<SysHotelDO> sysHotelDOList);

    /**
     * 根据hotelCode查询酒店列表
     *
     * @param hotelCode
     * @return
     */
    List<SysHotelDO> getHoteListByHotelCode(String hotelCode);

    /**
     * 根据hotelCode查询酒店列表
     *
     * @param hotelCode
     * @return
     */
    SysHotelDO getHoteByHotelCode(String hotelCode, String hotelType);

    /**
     * 根据parentId查询子酒店列表
     *
     * @param parentId
     * @return
     */
    List<SysHotelDO> getHoteListByParentId(Long parentId);

    /**
     * 查询hoteDO酒店所有父节点列表
     *
     * @param hoteDO
     * @return
     */
    List<SysHotelDO> getParentHoteListByDO(SysHotelDO hoteDO);

    /**
     * 根据hotelCodeSet查询酒店列表
     *
     * @param hotelCodeSet
     * @param hotelType
     * @return
     */
    List<SysHotelDO> getHoteListByHotelCodeSet(Set<String> hotelCodeSet, String hotelType);

    /**
     * 查询酒店列表
     *
     * @param deletedFlag
     * @return
     */
    List<SysHotelDO> getHoteListByDeletedFlag(Boolean deletedFlag);

    /**
     * 获取根节点
     *
     * @return
     */
    SysHotelDO getRootHotel();


    /**
     * 批量添加酒店信息
     *
     * @param
     * @return
     */
    Future<BaseResponse> addHotelBatchAsync(List<SysHotelDO> addHotelList);

    /**
     * 批量异步删除酒店信息
     *
     * @param hotelCodeSet
     * @return
     */
    Future<BaseResponse> deleltHotelBatchAsync(Set<String> hotelCodeSet);

    /**
     * 异步批量更新酒店信息
     *
     * @param list
     * @return
     */
    Future<BaseResponse> updateHotelBatchAsync(List<SysHotelDO> list);

    /**
     * 获取根节点
     *
     * @return
     */
    Future<SysHotelDO> getRootHotelAsync();

    /**
     * 更具hotelCode异步查询酒店
     *
     * @return
     */
    Future<SysHotelDO> getHoteByHotelCodeByAsync(String hotelCode);
}
