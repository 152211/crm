package com.hworld.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@ToString
@ApiModel("BaseRespVO")
public class BaseRespVO implements Serializable {

    @JsonIgnore
    protected final String defaultTimeZone = "UTC";

    /**
     * 删除标记 1:删除 0:未删除
     */
    @ApiModelProperty("删除标记 1:删除 0:未删除")
    private Boolean deletedFlag;
    /**
     * 创建人ID
     */
    @ApiModelProperty("创建人ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;
    /**
     * 创建人姓名
     */
    @ApiModelProperty("创建人姓名")
    private String createdName;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
    /**
     * 修改人ID
     */
    @ApiModelProperty("修改人ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long modifiedBy;
    /**
     * 修改人姓名
     */
    @ApiModelProperty("修改人姓名")
    private String modifiedName;
    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedOn;

}
