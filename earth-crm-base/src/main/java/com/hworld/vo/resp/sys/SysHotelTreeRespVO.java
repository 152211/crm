package com.hworld.vo.resp.sys;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.vo.req.BaseReqVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 酒店管理维护DTO
 *
 * @author caoyang
 * @date 2022-02-08 16:00:53
 */
@ApiModel("酒店树结构")
@Getter
@Setter
@ToString
public class SysHotelTreeRespVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 父节点ID
     */
    @ApiModelProperty("父节点ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 酒店编号
     */
    @ApiModelProperty("酒店编号")
    private String hotelNo;
    /**
     * R-根节点,G-总部部门,A/B-品牌,S-门店
     */
    @ApiModelProperty("R-根节点,G-总部部门,A/B-品牌,S-门店")
    private String type;
    /**
     * 酒店名称
     */
    @ApiModelProperty("酒店名称")
    private String name;
    /**
     * 酒店简称
     */
    @ApiModelProperty("酒店简称")
    private String hotelShortName;

    /**
     * 权限英文名称
     */
    @ApiModelProperty("子节点")
    private List<SysHotelTreeRespVO> children;
}
