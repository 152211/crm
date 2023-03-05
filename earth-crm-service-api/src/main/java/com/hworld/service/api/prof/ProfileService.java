package com.hworld.service.api.prof;

import com.earth.diana.bo.apol.BProfAddrInfo;
import com.earth.diana.bo.apol.BProfBaseInd;
import com.earth.diana.bo.apol.ext.IndividualVo;
import com.hworld.base.BaseResponse;
import com.hworld.vo.req.prof.ProfBaseIndReqVO;
import com.hworld.vo.req.prof.ProfileSearchReqVO;

/**
 * 客例业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-08-15 15:00:52
 **/
public interface ProfileService {

    /**
     * 保存个人客例
     *
     * @param req
     * @return
     */
    BaseResponse saveIndividualProf(IndividualVo req);

    /**
     * 根据档案ID查询档案详情
     *
     * @param req
     * @return
     */
    BaseResponse getProfOne(ProfileSearchReqVO req);

    /**
     * 查询档案基本信息
     *
     * @param req
     * @return
     */
    BaseResponse getProfBaseIndOne(ProfBaseIndReqVO req);



    /**
     * 查询地址信息表
     *
     * @param req
     * @return
     */
    BaseResponse getProfAddrInfoAll(BProfAddrInfo req);
}
