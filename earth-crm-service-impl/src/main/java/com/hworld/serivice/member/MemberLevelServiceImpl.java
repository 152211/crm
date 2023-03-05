package com.hworld.serivice.member;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.entity.member.MemberLevelDO;
import com.hworld.mapper.member.MemberLevelMapper;
import com.hworld.service.api.member.MemberLevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会员计划于酒店关联关系业务实现
 *
 * @author caoyang
 * @date 2022-02-15 17:26:23
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevelDO> implements MemberLevelService {
    @Autowired
    private MemberLevelMapper memberLevelMapper;

    /**
     * 查询会员计划的默认会员等级
     *
     * @param memberProgramId
     * @return
     */
    @Override
    public List<MemberLevelDO> getDefaultMemberLevelListByProgramId(Long memberProgramId) {
        if (memberProgramId == null) {
            return null;
        }

        QueryWrapper<MemberLevelDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_flag", 0);
        queryWrapper.eq("member_program_id", memberProgramId);
        queryWrapper.eq("is_default", 1);

        return memberLevelMapper.selectList(queryWrapper);
    }
}
