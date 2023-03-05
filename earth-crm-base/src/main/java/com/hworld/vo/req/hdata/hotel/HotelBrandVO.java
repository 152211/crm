package com.hworld.vo.req.hdata.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 品牌列表
 */
@ApiModel("Hdata酒店品牌数据")
@Getter
@Setter
@ToString
public class HotelBrandVO {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("集团编号")
    private String groupNo;

    @ApiModelProperty("外部等级编号")
    private String outerLevelCode;

    @ApiModelProperty("内部等级编号")
    private String innerLevelCode;

    @ApiModelProperty("品牌编号")
    private String brandNo;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("是否删除")
    private Boolean isDelete;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date modifyTime;

    @ApiModelProperty("多语言集合")
    private List<HotelBrandLanguageVO> languageList;
}
