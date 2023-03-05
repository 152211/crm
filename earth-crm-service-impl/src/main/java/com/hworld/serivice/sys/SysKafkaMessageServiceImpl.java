package com.hworld.serivice.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.base.BaseResponse;
import com.hworld.constants.Constants;
import com.hworld.entity.sys.SysKafkaMessageDO;
import com.hworld.enums.KafkaMsgStatusEnum;
import com.hworld.mapper.sys.SysKafkaMessageMapper;
import com.hworld.service.api.kafka.KafKaProducerService;
import com.hworld.service.api.sys.SysKafkaMessageService;
import com.hworld.utils.MyBeanUtils;
import com.hworld.utils.SnowflakeIdUtil;
import com.hworld.vo.req.sys.SysKafkaMessageReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * kafka消息业务实现
 *
 * @author caoyang
 * @date 2022-07-14 10:18:47
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysKafkaMessageServiceImpl extends ServiceImpl<SysKafkaMessageMapper, SysKafkaMessageDO> implements SysKafkaMessageService {

    @Autowired
    private KafKaProducerService kafKaProducerService;

    /**
     * 当前消费者区
     */
    @Value("${spring.kafka.producer.partition}")
    private int partition;


    @Override
    public BaseResponse addSysKafkaMessage(SysKafkaMessageReqVO reqVO) {
        //1.0保存消息到mysql
        SysKafkaMessageDO sysKafkaMessageDO = MyBeanUtils.dtoToDo(reqVO, SysKafkaMessageDO.class);
        sysKafkaMessageDO.setMessageStatus(KafkaMsgStatusEnum.NO_SEND.getMsgStatus());
        sysKafkaMessageDO.setProducerTimes(Constants.ZERO);
        sysKafkaMessageDO.setConsumerTimes(Constants.ZERO);
        sysKafkaMessageDO.setMessagePartition(partition);
        save(sysKafkaMessageDO);

        //发送至kafka
        SysKafkaMessageReqVO sysKafkaMessageReqVO = MyBeanUtils.dtoToDo(sysKafkaMessageDO, SysKafkaMessageReqVO.class);
        kafKaProducerService.sendKafkaMessage(sysKafkaMessageReqVO);

        return BaseResponse.success();
    }

    @Override
    public BaseResponse updateSysKafkaMessageStatusByMsgId(SysKafkaMessageReqVO reqVO) {

        QueryWrapper<SysKafkaMessageDO> queryWrapper = new QueryWrapper();
        queryWrapper.eq("message_id", reqVO.getMessageId());
        SysKafkaMessageDO sysKafkaMessage = new SysKafkaMessageDO();
        sysKafkaMessage.setMessageStatus(reqVO.getMessageStatus());
        update(sysKafkaMessage, queryWrapper);
        return BaseResponse.success();
    }
}
