package com.hworld.vo.req.hdata.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel("Hdat酒店品牌多语言")
@Getter
@Setter
@ToString
public class HotelBrandLanguageVO {

    @ApiModelProperty("语言编号")
    private String languageCode;

    @ApiModelProperty("品牌编号")
    private String brandNo;

    @ApiModelProperty("品牌名称")
    private String brandName;

    @ApiModelProperty("品牌描述")
    private String brandDesc;

    @ApiModelProperty("是否删除")
    private Boolean isDelete;
}
