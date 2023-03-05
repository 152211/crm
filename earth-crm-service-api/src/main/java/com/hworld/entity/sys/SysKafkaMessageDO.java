package com.hworld.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hworld.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

/**
 * 未知Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-07-14 10:18:47
 **/
@TableName("sys_kafka_message")
@Getter
@Setter
@ToString
public class SysKafkaMessageDO extends BaseDO {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 消息ID
     */
    @TableField("message_id")
    private String messageId;

    /**
     * 消息主题
     */
    @TableField("message_topic")
    private String messageTopic;

    /**
     * 消息状态。-1:未发送kafka；0:发送kafka失败；1:发送kafka成功；2:kafka消费失败 3:kafka消费成功
     */
    @TableField("message_status")
    private Integer messageStatus;

    /**
     * 消息内容
     */
    @TableField("message_log")
    private String messageLog;

    /**
     * 分区
     */
    @TableField("message_partition")
    private Integer messagePartition;

    /**
     * 生产消息次数
     */
    @TableField("producer_times")
    private Integer producerTimes;

    /**
     * 消费次数
     */
    @TableField("consumer_times")
    private Integer consumerTimes;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}