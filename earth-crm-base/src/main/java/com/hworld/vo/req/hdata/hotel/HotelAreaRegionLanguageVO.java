package com.hworld.vo.req.hdata.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 大区信息多语言
 */
@ApiModel("大区信息多语言")
@Getter
@Setter
@ToString
public class HotelAreaRegionLanguageVO implements Serializable {

    @ApiModelProperty("语言编号")
    private String languageCode;

    @ApiModelProperty("大区编号")
    private String regionNumber;

    @ApiModelProperty("大区名称")
    private String regionName;

    @ApiModelProperty("是否删除")
    private Boolean isDelete;
}
