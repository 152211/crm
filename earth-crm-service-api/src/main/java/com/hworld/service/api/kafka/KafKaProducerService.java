package com.hworld.service.api.kafka;

import com.hworld.base.BaseResponse;
import com.hworld.vo.req.sys.SysKafkaMessageReqVO;

public interface KafKaProducerService {
    /**
     * 发送kafka消息
     *
     * @return
     */
    BaseResponse sendKafkaMessage(SysKafkaMessageReqVO reqVO);

    /**
     * 发送日志消息
     *
     * @return
     */
    BaseResponse sendKafkaLogMessage(String topic, String message);
}
