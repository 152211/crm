package com.hworld.vo.req.hdata.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiModel("房型基本信息")
@Getter
@Setter
@ToString
public class HotelRoomTypeVO implements Serializable {

    private Long id;

    @ApiModelProperty("房型大类编号")
    private String typeClass;

    @ApiModelProperty("酒店编号")
    private String hotelNo;

    @ApiModelProperty("豪华级别编号")
    private String luxuryLevel;

    @ApiModelProperty("景观编号")
    private String scenery;

    @ApiModelProperty("状态")
    private Byte status;

    @ApiModelProperty("房型代码")
    private String typeCode;

    @ApiModelProperty("来源")
    private String source;

    @ApiModelProperty("最大可入住人数（总）")
    private Integer maxCheckInNum;

    @ApiModelProperty("最大可入住人数（成人）")
    private Integer maxAdultCheckInNum;

    @ApiModelProperty("最大可入住人数（儿童）")
    private Integer maxChildCheckInNum;

    @ApiModelProperty("房间面积")
    private Integer roomArea;

    @ApiModelProperty("是否有窗户")
    private String isHasWindow;

    @ApiModelProperty("物理房量")
    private Integer roomNum;

    @ApiModelProperty("是否能加床")
    private String isCanAddBed;

    @ApiModelProperty("可加床数量")
    private Integer canAddBedCount;

    @ApiModelProperty("加床费")
    private BigDecimal addBedFee;

    @ApiModelProperty("是否无烟")
    private String hasSmokeFree;

    @ApiModelProperty("套房类型")
    private Byte isSuite;

    @ApiModelProperty("网站排序")
    private Integer siteSort;

    @ApiModelProperty("是否删除")
    private Boolean isDelete;

    @ApiModelProperty("修改时间")
    private Date modifyTime;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("多语言集合")
    private List<HotelRoomTypeLanguageVO> languageList;

    @ApiModelProperty("床型集合")
    private List<HotelRoomBedVO> bedList;
}
