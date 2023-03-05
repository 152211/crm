package com.hworld.vo.req.hdata.region;

import com.hworld.annotation.Create;
import com.hworld.annotation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ApiModel("Hdata国际城市区域")
@Getter
@Setter
@ToString
public class HDataRegionReqVO implements Serializable {

    @ApiModelProperty("对接系统的systemName")
    private String appId;

    @ApiModelProperty("兼容线上，不传只返回有效。如需返回全部(即包含无效)，传值true")
    private Boolean includeDisabled;

    @ApiModelProperty("是否国际行政区域，true-是；false-否")
    private Boolean isInternational;

    @ApiModelProperty("语言code，中文：zh-cn;英文：en-us")
    private String languageCode;
}
