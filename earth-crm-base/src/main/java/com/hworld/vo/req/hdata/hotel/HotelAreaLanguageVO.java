package com.hworld.vo.req.hdata.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 事业部信息多语言
 */
@ApiModel("事业部信息多语言")
@Getter
@Setter
@ToString
public class HotelAreaLanguageVO implements Serializable {
    @ApiModelProperty("语言编号")
    private String languageCode;

    @ApiModelProperty("事业部编号")
    private String areaNumber;

    @ApiModelProperty("事业部名称")
    private String areaName;

    @ApiModelProperty("是否删除")
    private Boolean isDelete;
}
