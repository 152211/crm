package com.hworld.vo.resp.sys;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Create;
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
 * 角色维护DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:10
 */
@ApiModel("角色维护")
@Getter
@Setter
@ToString
public class SysRoleRespVO extends BaseRespVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 角色code
     */
    @ApiModelProperty("角色code")
    private String roleCode;

    /**
     * 角色中文名称
     */
    @ApiModelProperty("角色中文名称")
    private String roleNameCn;

    /**
     * 角色英文名称
     */
    @ApiModelProperty("角色英文名称")
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
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String description;

    /**
     * CUSTOMER自定义;TEMPLATE模板
     */
    @ApiModelProperty("CUSTOMER自定义;TEMPLATE模板")
    private String ruleType;

    /**
     * 是否默认，默认不可编辑 0=否 1=是
     */
    @ApiModelProperty("是否默认，默认不可编辑 0=否 1=是")
    private Boolean isDefault;

}
