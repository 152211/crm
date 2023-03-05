package com.hworld.serivice.member;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.entity.member.MemberProgramHotelDO;
import com.hworld.mapper.member.MemberProgramHotelMapper;
import com.hworld.service.api.member.MemberProgramHotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员计划于酒店关联关系业务实现
 *
 * @author caoyang
 * @date 2022-02-15 17:26:23
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberProgramHotelServiceImpl extends ServiceImpl<MemberProgramHotelMapper, MemberProgramHotelDO> implements MemberProgramHotelService {

}
