package com.hworld.service.api.sys;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hworld.base.BaseResponse;
import com.hworld.entity.sys.SysGlobalRegionDO;
import com.hworld.vo.req.hdata.region.HDataRegionReqVO;

/**
 * 全球地区业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-07-21 16:43:04
 **/
public interface SysGlobalRegionService extends IService<SysGlobalRegionDO> {

    /**
     * 保存所有HData行政区域信息
     *
     * @return
     */
    BaseResponse saveHDataRegionAll(HDataRegionReqVO reqVO);

    /**
     * 查询HData行政区域信息
     *
     * @return
     */
    BaseResponse getHDataRegionAll(HDataRegionReqVO reqVO);
}
