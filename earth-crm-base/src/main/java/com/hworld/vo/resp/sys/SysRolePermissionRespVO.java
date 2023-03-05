package com.hworld.vo.resp.sys;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 角色权限对应关系DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:10
 */
@ApiModel("角色权限对应关系")
@Getter
@Setter
@ToString
public class SysRolePermissionRespVO extends BaseRespVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    /**
     * 角色code
     */
    @ApiModelProperty("角色code")
    private String roleCode;

    /**
     * 权限id
     */
    @ApiModelProperty("权限id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long permissionId;

    /**
     * 权限code
     */
    @ApiModelProperty("权限code")
    private String permissionCode;

    /**
     * A允许 D禁止
     */
    @ApiModelProperty("A允许 D禁止")
    private String authType;
}
