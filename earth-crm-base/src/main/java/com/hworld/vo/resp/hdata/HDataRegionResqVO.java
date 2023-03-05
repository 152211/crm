package com.hworld.vo.resp.hdata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ApiModel("Hdata国际城市区域")
@Getter
@Setter
@ToString
public class HDataRegionResqVO implements Serializable {

    @ApiModelProperty("国际行政区域编号")
    private String itemCode;

    @ApiModelProperty("国际行政区域名称")
    private String itemName;

    @ApiModelProperty("行政区域等级编码")
    private String itemLevelCode;

    @ApiModelProperty("拼音全称")
    private String allPinyin;

    @ApiModelProperty("拼音缩写")
    private String shortPinyin;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("子行政区域")
    private List<Object> subList;
}
