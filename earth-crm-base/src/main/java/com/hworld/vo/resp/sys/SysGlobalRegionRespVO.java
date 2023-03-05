package com.hworld.vo.resp.sys;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Create;
import com.hworld.annotation.Delelt;
import com.hworld.annotation.SelectOne;
import com.hworld.annotation.Update;
import com.hworld.vo.req.BaseReqVO;
import com.hworld.vo.resp.BaseRespVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 全球地区DTO
 *
 * @author caoyang
 * @date 2022-07-21 16:43:04
 */
@ApiModel("全球地区")
@Getter
@Setter
@ToString
public class SysGlobalRegionRespVO extends BaseRespVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 父ID
     */
    @ApiModelProperty("父ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 层级。1:7大洲 2:国家 3:省/州 4:市
     */
    @ApiModelProperty("层级。1:7大洲 2:国家 3:省/州 4:市")
    private Integer areaLevel;
    /**
     * 路径
     */
    @ApiModelProperty("路径")
    private String areaPath;
    /**
     * 国家编号
     */
    @ApiModelProperty("国家编号")
    private String countryCode;
    /**
     * 国家数字编号
     */
    @ApiModelProperty("国家数字编号")
    private String countryNumber;
    /**
     * 国家电话区号
     */
    @ApiModelProperty("国家电话区号")
    private String countryPhoneNumber;
    /**
     * 地区编号
     */
    @ApiModelProperty("地区编号")
    private String areaCode;
    /**
     * 时区
     */
    @ApiModelProperty("时区")
    private String timeZone;
    /**
     * 中文名称
     */
    @ApiModelProperty("中文名称")
    private String nameCn;
    /**
     * 英文名称
     */
    @ApiModelProperty("英文名称")
    private String nameEn;
    /**
     * 中文拼音
     */
    @ApiModelProperty("中文拼音")
    private String namePinyin;
}
