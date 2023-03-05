package com.hworld.serivice.member;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.entity.member.MemberProgramDO;
import com.hworld.entity.sys.SysRoleStoreDO;
import com.hworld.mapper.member.MemberProgramMapper;
import com.hworld.service.api.member.MemberProgramService;
import com.hworld.utils.WrapperUtiles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会员计划业务实现
 *
 * @author caoyang
 * @date 2022-02-15 17:26:20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberProgramServiceImpl extends ServiceImpl<MemberProgramMapper, MemberProgramDO> implements MemberProgramService {

    @Autowired
    private MemberProgramMapper memberProgramMapper;

    /**
     * 查询默认/非默认会员计划
     *
     * @param isDefault
     * @return
     */
    @Override
    public List<MemberProgramDO> getMemberProgramListByIsDefault(Boolean isDefault) {
        QueryWrapper<MemberProgramDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_flag", 0);
        queryWrapper.eq("is_default", isDefault ? 1 : 0);

        return memberProgramMapper.selectList(queryWrapper);
    }
}
