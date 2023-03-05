package com.hworld.consumer.kafka;

import com.alibaba.fastjson.JSON;
import com.hworld.base.BaseResponse;
import com.hworld.constants.Constants;
import com.hworld.entity.member.MemberProfileDO;
import com.hworld.enums.ErrorEnum;
import com.hworld.enums.ESMsgTypeEnum;
import com.hworld.enums.KafkaMsgStatusEnum;
import com.hworld.service.api.elasticSearch.member.MemberProfileEsService;
import com.hworld.service.api.sys.SysKafkaMessageService;
import com.hworld.utils.MyBeanUtils;
import com.hworld.vo.req.kafka.MemberProfileKfkReqVO;
import com.hworld.vo.req.sys.SysKafkaMessageReqVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class KafKaConsumer {

    @Autowired
    private MemberProfileEsService memberProfileEsService;

    @Autowired
    private SysKafkaMessageService sysKafkaMessageService;

    @KafkaListener(topicPartitions = {@TopicPartition(topic = Constants.CRM_WORLD_MEMBER, partitions = {"0"})})
    public void topicTest(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        BaseResponse response = BaseResponse.success();

        if (message.isPresent()) {
            Object msg = message.get();
            MemberProfileKfkReqVO memberProfileKfkReqVO = JSON.parseObject(msg.toString(), MemberProfileKfkReqVO.class);
            if (memberProfileKfkReqVO == null) {
                log.info("Topic:{}:消费成功!Message:{}", Constants.CRM_WORLD_MEMBER, msg);
                ack.acknowledge();
                return;
            }
            MemberProfileDO memberProfileDO = MyBeanUtils.dtoToDo(memberProfileKfkReqVO, MemberProfileDO.class);
            if (ESMsgTypeEnum.ADD.getMsgType().equalsIgnoreCase(memberProfileKfkReqVO.getMsgType())) {
                response = memberProfileEsService.memberEsRegister(memberProfileDO);
            } else if (ESMsgTypeEnum.UPDATE.getMsgType().equalsIgnoreCase(memberProfileKfkReqVO.getMsgType())) {
                response = memberProfileEsService.updateEsMember(memberProfileDO);
            } else if (ESMsgTypeEnum.UPDATE_DELETEDFLAG.getMsgType().equalsIgnoreCase(memberProfileKfkReqVO.getMsgType())) {
                response = memberProfileEsService.updateDeletedFlagEsMember(memberProfileDO);
            } else if (ESMsgTypeEnum.DELETE.getMsgType().equalsIgnoreCase(memberProfileKfkReqVO.getMsgType())) {
                response = memberProfileEsService.deleteEsMember(memberProfileDO);
            }
            if (ErrorEnum.SUCCESS.getCode().intValue() == response.getCode().intValue()) {
                log.info("Topic:{}:消费成功!Message:{}", Constants.CRM_WORLD_MEMBER, msg);
                ack.acknowledge();

                String msgId = memberProfileKfkReqVO.getMsgId();
                SysKafkaMessageReqVO reqVO = new SysKafkaMessageReqVO();
                reqVO.setMessageId(msgId);
                reqVO.setMessageStatus(KafkaMsgStatusEnum.CONSUMER_SUCCESS.getMsgStatus());
                sysKafkaMessageService.updateSysKafkaMessageStatusByMsgId(reqVO);
            }
        }
    }
}
