package com.hworld.vo.req.member;

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

import javax.validation.constraints.*;

/**
 * 会员等级维护DTO
 *
 * @author caoyang
 * @date 2022-02-17 16:26:34
 */
@ApiModel("会员等级维护")
@Getter
@Setter
@ToString
public class MemberLevelReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class, SelectOne.class})
    private Long id;
    /**
     * 会员等级名称
     */
    @ApiModelProperty("会员等级名称")
    @Length(min = 0, max = 50, message = "name length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "name Can not be empt", groups = {Create.class})
    private String name;
    /**
     * 等级code
     */
    @ApiModelProperty("等级code")
    @Length(min = 0, max = 50, message = "code length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "code Can not be empt", groups = {Create.class})
    private String code;
    /**
     * 等级排序，数字越大级别越大
     */
    @ApiModelProperty("等级排序，数字越大级别越大")
    @NotNull(message = "ordinal Can not be empt", groups = {Create.class})
    private Integer ordinal;
    /**
     * 会员计划ID
     */
    @ApiModelProperty("会员计划ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long memberProgramId;
    /**
     * 会员计划code
     */
    @ApiModelProperty("会员计划code")
    @Length(min = 0, max = 50, message = "memberProgramCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String memberProgramCode;
    /**
     * 会员计划名称
     */
    @ApiModelProperty("会员计划名称")
    @Length(min = 0, max = 255, message = "memberProgramName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String memberProgramName;
    /**
     * 会员类型
     */
    @ApiModelProperty("会员类型")
    @Length(min = 0, max = 32, message = "memberType length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String memberType;
    /**
     * 售价
     */
    @ApiModelProperty("售价")
    @Length(min = 0, max = 0, message = "price length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String price;
    /**
     * icon图标
     */
    @ApiModelProperty("icon图标")
    @Length(min = 0, max = 255, message = "icon length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String icon;
    /**
     * 描述
     */
    @ApiModelProperty("描述")
    @Length(min = 0, max = 255, message = "description length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String description;
    /**
     * 是否默认，1=是 0=否
     */
    @ApiModelProperty("是否默认，1=是 0=否")
    private Boolean isDefault;
    /**
     * 0:禁用 1:启用
     */
    @ApiModelProperty("有效性:0:禁用 1:启用")
    private Boolean isDisable;
    /**
     * 是否允许销售，1=允许 0=不允许
     */
    @ApiModelProperty("是否允许销售，1=允许 0=不允许")
    private Boolean saleFlag;
}
