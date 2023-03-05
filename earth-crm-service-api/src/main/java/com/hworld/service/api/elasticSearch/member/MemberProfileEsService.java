package com.hworld.service.api.elasticSearch.member;

import com.hworld.base.BaseResponse;
import com.hworld.entity.member.MemberProfileDO;
import com.hworld.vo.req.member.MemberProfileReqVO;

public interface MemberProfileEsService {
    /**
     * 创建会员
     *
     * @param reqDO
     * @return
     */
    BaseResponse memberEsRegister(MemberProfileDO reqDO);


    /**
     * 修改会员
     *
     * @param reqDO
     * @return
     */
    BaseResponse updateEsMember(MemberProfileDO reqDO);

    /**
     * 删除会员(伪删除)
     *
     * @param reqDO
     * @return
     */
    BaseResponse updateDeletedFlagEsMember(MemberProfileDO reqDO);

    /**
     * 删除会员(物理删除)
     *
     * @param reqDO
     * @return
     */
    BaseResponse deleteEsMember(MemberProfileDO reqDO);

    /**
     * 根据ID查询会员
     *
     * @param reqVO
     * @return
     */
    BaseResponse getMemberEsByID(MemberProfileReqVO reqVO);

    /**
     * 查询会员
     *
     * @param reqVO
     * @return
     */
    BaseResponse queryByVO(MemberProfileReqVO reqVO);
}
