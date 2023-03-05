package com.hworld.service.api.external;

import com.hworld.base.BaseResponse;
import com.hworld.vo.req.hdata.HDataReqVO;
import com.hworld.vo.resp.hdata.HDataBaseRespVO;

/**
 * HData业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-07-25 16:26:20
 **/
public interface HDataService {

    /**
     * Hdata推送数据处理
     *
     * @param reqVO
     * @return
     */
    HDataBaseRespVO pushMasterData(HDataReqVO reqVO);
}
