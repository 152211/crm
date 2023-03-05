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

import javax.validation.constraints.*;
import java.util.List;

/**
 * 角色维护DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:10
 */
@ApiModel("角色维护")
@Getter
@Setter
@ToString
public class SysRoleReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class, SelectOne.class})
    private Long id;

    /**
     * 角色code
     */
    @ApiModelProperty("角色code")
    @Length(min = 0, max = 50, message = "roleCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "roleCode Can not be empty", groups = {Create.class, Update.class})
    private String roleCode;

    /**
     * 角色中文名称
     */
    @ApiModelProperty("角色中文名称")
    @Length(min = 0, max = 50, message = "roleNameCn length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String roleNameCn;

    /**
     * 角色英文名称
     */
    @ApiModelProperty("角色英文名称")
    @Length(min = 0, max = 50, message = "roleNameEn length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String roleNameEn;

    /**
     * 是否超级管理员，忽略角色，取租户下所有权限，0=否 1=是
     */
    @ApiModelProperty("是否超级管理员，忽略角色，取租户下所有权限，0=否 1=是")
    private Boolean isAdmin;

    /**
     * 状态，E=启用 D=禁用
     */
    @ApiModelProperty("状态，E=启用 D=禁用")
    @Length(min = 0, max = 1, message = "status length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @Length(min = 0, max = 400, message = "description length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String description;

    /**
     * CUSTOMER自定义;TEMPLATE模板
     */
    @ApiModelProperty("CUSTOMER自定义;TEMPLATE模板")
    @Length(min = 0, max = 10, message = "ruleType length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String ruleType;

    /**
     * 是否默认，默认不可编辑 0=否 1=是
     */
    @ApiModelProperty("是否默认，默认不可编辑 0=否 1=是")
    private Boolean isDefault;
}
