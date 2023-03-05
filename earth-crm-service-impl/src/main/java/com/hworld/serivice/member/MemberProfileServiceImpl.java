package com.hworld.serivice.member;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.base.BaseResponse;
import com.hworld.entity.member.MemberProfileDO;
import com.hworld.enums.ESMsgTypeEnum;
import com.hworld.enums.KafKaTopicEnum;
import com.hworld.mapper.member.MemberProfileMapper;
import com.hworld.service.api.member.MemberProfileService;
import com.hworld.service.api.sys.SysKafkaMessageService;
import com.hworld.utils.MyBeanUtils;
import com.hworld.utils.SnowflakeIdUtil;
import com.hworld.vo.req.kafka.MemberProfileKfkReqVO;
import com.hworld.vo.req.member.MemberProfileReqVO;
import com.hworld.vo.req.sys.SysKafkaMessageReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会员业务实现
 *
 * @author caoyang
 * @date 2022-02-15 17:26:20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberProfileServiceImpl extends ServiceImpl<MemberProfileMapper, MemberProfileDO> implements MemberProfileService {

    @Autowired
    private MemberProfileMapper memberProfileMapper;

    @Autowired
    private SysKafkaMessageService sysKafkaMessageService;


    @Override
    public BaseResponse memberRegister(MemberProfileReqVO reqVO) {
        MemberProfileDO memberProfileDO = MyBeanUtils.dtoToDo(reqVO, MemberProfileDO.class);
        boolean bool = save(memberProfileDO);
        if (!bool) {
            return BaseResponse.error("保存失败");
        }
        String msgId=String.valueOf(SnowflakeIdUtil.getSnowflakeId());

        MemberProfileKfkReqVO kfkReqVO = MyBeanUtils.doToDto(memberProfileDO, MemberProfileKfkReqVO.class);
        kfkReqVO.setMsgType(ESMsgTypeEnum.ADD.getMsgType());
        kfkReqVO.setMsgId(msgId);

        SysKafkaMessageReqVO sysKafkaMessageReqVO = new SysKafkaMessageReqVO();
        sysKafkaMessageReqVO.setMessageId(msgId);
        sysKafkaMessageReqVO.setMessageLog(JSON.toJSONString(kfkReqVO));
        sysKafkaMessageReqVO.setMessageTopic(KafKaTopicEnum.CRM_WORLD_MEMBER.getTopic());

        sysKafkaMessageService.addSysKafkaMessage(sysKafkaMessageReqVO);
        return BaseResponse.success(memberProfileDO);
    }

    @Override
    public BaseResponse updateMember(MemberProfileReqVO reqVO) {
        MemberProfileDO memberProfileDO = MyBeanUtils.dtoToDo(reqVO, MemberProfileDO.class);
        boolean bool = updateById(memberProfileDO);

        if (!bool) {
            return BaseResponse.error("保存失败");
        }

        MemberProfileKfkReqVO kfkReqVO = MyBeanUtils.doToDto(memberProfileDO, MemberProfileKfkReqVO.class);
        kfkReqVO.setMsgType(ESMsgTypeEnum.UPDATE.getMsgType());
        SysKafkaMessageReqVO sysKafkaMessageReqVO = new SysKafkaMessageReqVO();
        sysKafkaMessageReqVO.setMessageLog(JSON.toJSONString(kfkReqVO));
        sysKafkaMessageService.addSysKafkaMessage(sysKafkaMessageReqVO);

        return BaseResponse.success();
    }


}
