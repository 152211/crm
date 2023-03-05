package com.hworld.vo.req.sys;

import com.hworld.annotation.Create;
import com.hworld.annotation.Update;
import com.hworld.vo.req.BaseReqVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import javax.validation.constraints.*;

/**
 * kafka消息 DTO
 *
 * @author caoyang
 * @date 2022-07-14 10:18:47
 */
@ApiModel("kafka消息reqVO")
@Getter
@Setter
@ToString
public class SysKafkaMessageReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String id;
    /**
     * 消息ID
     */
    @ApiModelProperty("消息ID")
    @Length(min = 0, max = 50, message = "消息ID必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String messageId;
    /**
     * 消息主题
     */
    @ApiModelProperty("消息主题")
    @Length(min = 0, max = 255, message = "消息主题必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String messageTopic;
    /**
     * 消息状态。-1:未发送kafka；0:发送kafka失败；1:发送kafka成功；2:kafka消费失败 3:kafka消费成功
     */
    @ApiModelProperty("消息状态。-1:未发送kafka；0:发送kafka失败；1:发送kafka成功；2:kafka消费失败 3:kafka消费成功")
    @Max(value = 11, message = "消息状态。-1:未发送kafka；0:发送kafka失败；1:发送kafka成功；2:kafka消费失败 3:kafka消费成功不能超过{value}个字符", groups = {Create.class, Update.class})
    private Integer messageStatus;

    /**
     * 消息内容
     */
    @ApiModelProperty("消息内容")
    private String messageLog;

    /**
     * 分区
     */
    @ApiModelProperty("分区")
    private Integer messagePartition;
    /**
     * 生产消息次数
     */
    @ApiModelProperty("生产消息次数")
    @Max(value = 11, message = "生产消息次数不能超过{value}个字符", groups = {Create.class, Update.class})
    private Integer producerTimes;
    /**
     * 消费次数
     */
    @ApiModelProperty("消费次数")
    @Max(value = 11, message = "消费次数不能超过{value}个字符", groups = {Create.class, Update.class})
    private Integer consumerTimes;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @Length(min = 0, max = 255, message = "备注必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String remark;
}
