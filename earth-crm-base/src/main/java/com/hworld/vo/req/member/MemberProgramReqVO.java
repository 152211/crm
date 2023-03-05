package com.hworld.vo.req.member;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 会员计划DTO
 *
 * @author caoyang
 * @date 2022-02-15 17:26:20
 */
@ApiModel("会员计划")
@Getter
@Setter
@ToString
public class MemberProgramReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class, SelectOne.class})
    private Long id;
    /**
     * 会员计划code
     */
    @ApiModelProperty("会员计划code")
    @Length(min = 0, max = 20, message = "programCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String programCode;
    /**
     * 会员计划名称
     */
    @ApiModelProperty("会员计划名称")
    @Length(min = 0, max = 255, message = "programName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String programName;
    /**
     * 是否校验最后一位：1:校验,0:不校验
     */
    @ApiModelProperty("是否校验最后一位：1:校验,0:不校验")
    private Boolean isCheckCode;
    /**
     * 是否时默认:0:非默认 1:默认
     */
    @ApiModelProperty("是否时默认:0:非默认 1:默认")
    private Boolean isDefault;
    /**
     * 有效性:0:禁用 1:启用
     */
    @ApiModelProperty("有效性:0:禁用 1:启用")
    private Boolean isDisable;
    /**
     * 内部 I,外部 E
     */
    @ApiModelProperty("内部 I,外部 E")
    @Length(min = 0, max = 1, message = "type length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String type;
    /**
     * 会员编号长度
     */
    @ApiModelProperty("会员编号长度")
    @Length(min = 0, max = 2, message = "memberCodeLength length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String memberCodeLength;
    /**
     * 会员编号前缀
     */
    @ApiModelProperty("会员编号前缀")
    @Length(min = 0, max = 3, message = "memberNumberPrefix length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String memberNumberPrefix;
    /**
     * 会员计划图标
     */
    @ApiModelProperty("会员计划图标")
    @Length(min = 0, max = 255, message = "logoUrl length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String logoUrl;
    /**
     * 积分POINT;里程MILEAGE
     */
    @ApiModelProperty("积分POINT;里程MILEAGE")
    @Length(min = 0, max = 20, message = "rewardType length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String rewardType;
}
