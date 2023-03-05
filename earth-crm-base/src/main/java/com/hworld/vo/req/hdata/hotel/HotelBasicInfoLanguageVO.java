package com.hworld.vo.req.hdata.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@ApiModel("酒店基本信息多语言")
@Getter
@Setter
@ToString
public class HotelBasicInfoLanguageVO implements Serializable {

    @ApiModelProperty("语言编号")
    private String languageCode;

    @ApiModelProperty("酒店编号")
    private String hotelNo;

    @ApiModelProperty("酒店名称")
    private String hotelName;

    @ApiModelProperty("酒店地址")
    private String hotelAddress;

    @ApiModelProperty("酒店地址描述")
    private String hotelAddressDescription;

    @ApiModelProperty("酒店描述")
    private String describe;

    @ApiModelProperty("酒店文字介绍")
    private String hotelWordIntroduce;

    @ApiModelProperty("是否删除")
    private Boolean isDelete;
}