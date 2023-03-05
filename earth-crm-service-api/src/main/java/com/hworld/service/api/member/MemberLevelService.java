package com.hworld.service.api.member;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hworld.entity.member.MemberLevelDO;
import com.hworld.entity.member.MemberProgramDO;

import java.util.List;

/**
 * 会员等级维护业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-02-17 16:26:34
 **/
public interface MemberLevelService extends IService<MemberLevelDO> {

    /**
     * 查询会员计划的默认会员等级
     *
     * @param memberProgramId
     * @return
     */
    List<MemberLevelDO> getDefaultMemberLevelListByProgramId(Long memberProgramId);
}
