package com.hworld.service.api.sys;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hworld.base.BaseResponse;
import com.hworld.entity.sys.SysKafkaMessageDO;
import com.hworld.vo.req.sys.SysKafkaMessageReqVO;

/**
 * kafka消息业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-07-14 10:18:47
 **/
public interface SysKafkaMessageService extends IService<SysKafkaMessageDO> {

    /**
     * 添加SysKafkaMessage消息记录
     * @param reqVO
     * @return
     */
    BaseResponse addSysKafkaMessage(SysKafkaMessageReqVO reqVO);

    /**
     * 添加SysKafkaMessage消息记录
     * @param reqVO
     * @return
     */
    BaseResponse updateSysKafkaMessageStatusByMsgId(SysKafkaMessageReqVO reqVO);
}
