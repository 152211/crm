package com.hworld.service.api.member;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hworld.entity.member.MemberProgramDO;

import java.util.List;

/**
 * 会员计划业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-02-15 17:26:20
 **/
public interface MemberProgramService extends IService<MemberProgramDO> {

    /**
     * 查询默认/非默认会员计划
     *
     * @param isDefault
     * @return
     */
    List<MemberProgramDO> getMemberProgramListByIsDefault(Boolean isDefault);

}
