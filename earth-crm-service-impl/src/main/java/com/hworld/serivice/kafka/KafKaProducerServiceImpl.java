package com.hworld.serivice.kafka;

import com.alibaba.fastjson.JSON;
import com.hworld.base.BaseResponse;
import com.hworld.entity.sys.SysKafkaMessageDO;
import com.hworld.enums.ErrorEnum;
import com.hworld.enums.KafKaTopicEnum;
import com.hworld.enums.KafkaMsgStatusEnum;
import com.hworld.service.api.kafka.KafKaProducerService;
import com.hworld.service.api.sys.SysKafkaMessageService;
import com.hworld.utils.MyBeanUtils;
import com.hworld.vo.req.sys.SysKafkaMessageReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * kafka业务实现
 *
 * @author caoyang
 * @date 2022-02-15 17:26:20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class KafKaProducerServiceImpl implements KafKaProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private SysKafkaMessageService sysKafkaMessageService;

    /**
     * 当前消费者区
     */
    @Value("${spring.kafka.producer.partition}")
    private int partition;

    /**
     * 发送会员消息
     *
     * @return
     */
    @Override
    public BaseResponse sendKafkaMessage(SysKafkaMessageReqVO reqVO) {
        if (reqVO == null) {
            return BaseResponse.error(ErrorEnum.NOT_EXIST_ERROR.getCode(), "reqVO:" + ErrorEnum.NOT_EXIST_ERROR.getMsgEn());
        }

        if (reqVO.getMessageStatus() == null || reqVO.getMessageStatus().intValue() >= 1) {
            return BaseResponse.error(ErrorEnum.MESSAGE_STATUS_ERROR.getCode(), ErrorEnum.MESSAGE_STATUS_ERROR.getMsgEn());
        }

        //发送kafka消息
        ListenableFuture<SendResult<String, Object>> future =
                kafkaTemplate.send(reqVO.getMessageTopic(), reqVO.getMessagePartition(), "", reqVO.getMessageLog());

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理  throwable.getMessage()
                reqVO.setProducerTimes(reqVO.getProducerTimes() == null ? 1 : reqVO.getProducerTimes() + 1);
                reqVO.setMessageStatus(KafkaMsgStatusEnum.SEND_ERROR.getMsgStatus());
                SysKafkaMessageDO sysKafkaMessageDO = MyBeanUtils.dtoToDo(reqVO, SysKafkaMessageDO.class);
                sysKafkaMessageService.updateById(sysKafkaMessageDO);

                log.info("线程:{},topic:{}-生产者 发送消息失败：", Thread.currentThread().getName(), reqVO.getMessageTopic());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理  stringObjectSendResult.toString()
                reqVO.setProducerTimes(reqVO.getProducerTimes() == null ? 1 : reqVO.getProducerTimes() + 1);
                reqVO.setMessageStatus(KafkaMsgStatusEnum.SEND_SUCCESS.getMsgStatus());
                SysKafkaMessageDO sysKafkaMessageDO = MyBeanUtils.dtoToDo(reqVO, SysKafkaMessageDO.class);
                sysKafkaMessageService.updateById(sysKafkaMessageDO);
                log.info("线程:{},topic:{}-生产者 发送消息成功：", Thread.currentThread().getName(), reqVO.getMessageTopic());
            }
        });

        return BaseResponse.success();
    }

    @Override
    public BaseResponse sendKafkaLogMessage(String topic, String message) {
        //发送kafka消息
        ListenableFuture<SendResult<String, Object>> future =
                kafkaTemplate.send(topic, partition, "", message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理  throwable.getMessage()
                log.info("线程:{},topic:{}-生产者 发送消息失败：", Thread.currentThread().getName(), topic);
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理  stringObjectSendResult.toString()
                log.info("线程:{},topic:{}-生产者 发送消息成功：", Thread.currentThread().getName(), topic);
            }
        });
        return BaseResponse.success();
    }
}
