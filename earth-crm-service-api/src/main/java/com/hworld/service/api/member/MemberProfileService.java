package com.hworld.service.api.member;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hworld.base.BaseResponse;
import com.hworld.entity.member.MemberProfileDO;
import com.hworld.vo.req.member.MemberProfileReqVO;


/**
 * 会员业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-02-15 17:26:20
 **/
public interface MemberProfileService extends IService<MemberProfileDO> {
    /**
     * 会员注册
     * @param reqVO
     * @return
     */
    BaseResponse memberRegister(MemberProfileReqVO reqVO);

    /**
     * 会员修改
     * @param reqVO
     * @return
     */
    BaseResponse updateMember(MemberProfileReqVO reqVO);
}
