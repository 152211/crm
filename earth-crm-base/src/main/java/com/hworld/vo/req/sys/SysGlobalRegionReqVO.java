package com.hworld.vo.req.sys;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Create;
import com.hworld.annotation.Delelt;
import com.hworld.annotation.SelectOne;
import com.hworld.annotation.Update;
import com.hworld.vo.req.BaseReqVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import javax.validation.constraints.*;

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
public class SysGlobalRegionReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class, SelectOne.class})
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
    @Length(min = 0, max = 16, message = "areaPath length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String areaPath;
    /**
     * 国家编号
     */
    @ApiModelProperty("国家编号")
    @Length(min = 0, max = 8, message = "countryCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String countryCode;
    /**
     * 国家数字编号
     */
    @ApiModelProperty("国家数字编号")
    @Length(min = 0, max = 8, message = "countryNumber length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String countryNumber;
    /**
     * 国家电话区号
     */
    @ApiModelProperty("国家电话区号")
    @Length(min = 0, max = 8, message = "countryPhoneNumber length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String countryPhoneNumber;
    /**
     * 地区编号
     */
    @ApiModelProperty("地区编号")
    @Length(min = 0, max = 16, message = "areaCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String areaCode;
    /**
     * 时区
     */
    @ApiModelProperty("时区")
    @Length(min = 0, max = 16, message = "timeZone length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String timeZone;
    /**
     * 中文名称
     */
    @ApiModelProperty("中文名称")
    @Length(min = 0, max = 64, message = "nameCn length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String nameCn;
    /**
     * 英文名称
     */
    @ApiModelProperty("英文名称")
    @Length(min = 0, max = 64, message = "nameEn length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String nameEn;
    /**
     * 中文拼音
     */
    @ApiModelProperty("中文拼音")
    @Length(min = 0, max = 64, message = "namePinyin length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String namePinyin;
}
