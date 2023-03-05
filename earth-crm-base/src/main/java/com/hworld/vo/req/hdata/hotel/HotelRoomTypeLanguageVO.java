package com.hworld.vo.req.hdata.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 房型基本信息多语言
 */
@ApiModel("房型基本信息多语言")
@Getter
@Setter
@ToString
public class HotelRoomTypeLanguageVO implements Serializable {

    @ApiModelProperty("语言编号")
    private String languageCode;

    @ApiModelProperty("酒店编号")
    private String hotelNo;

    @ApiModelProperty("房型编号")
    private String typeCode;

    @ApiModelProperty("房型名称")
    private String typeName;

    @ApiModelProperty("酒店自定义对客展示房型名称")
    private String typeSaleName;

    @ApiModelProperty("房型描述")
    private String descript;

    @ApiModelProperty("房型介绍")
    private String introduce;

    @ApiModelProperty("楼层")
    private String floorPlace;

    @ApiModelProperty("是否有窗户描述")
    private String isHasWindowDesc;

    @ApiModelProperty("是否能加床描述")
    private String isCanAddBedDesc;

    @ApiModelProperty("是否无烟描述")
    private String hasSmokeFreeDesc;

    @ApiModelProperty("酒店个性化名称")
    private String hotelPersonalName;

    @ApiModelProperty("是否删除")
    private Boolean isDelete;

    @ApiModelProperty("修改时间")
    private Date modifyTime;

    @ApiModelProperty("创建时间")
    private Date createTime;
}