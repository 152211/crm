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
 * 系统权限DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:07
 */
@ApiModel("系统权限")
@Getter
@Setter
@ToString
public class SysPermissionRespVO extends BaseRespVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 父级权限ID
     */
    @ApiModelProperty("父级权限ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * 权限Code
     */
    @ApiModelProperty("权限Code")
    private String permissionCode;

    /**
     * 组件
     */
    @ApiModelProperty("组件")
    private String permissionComponent;

    /**
     * 权限类型 M：菜单权限，F：功能权限
     */
    @ApiModelProperty("权限类型 M：菜单权限，F：功能权限")
    private String permissionType;

    /**
     * 权限url
     */
    @ApiModelProperty("权限url")
    private String permissionUrl;

    /**
     * 权限中文名称
     */
    @ApiModelProperty("权限中文名称")
    private String permissionNameCn;

    /**
     * 权限英文名称
     */
    @ApiModelProperty("权限英文名称")
    private String permissionNameEn;

    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String permissionIcon;

    /**
     * 是否默认，1=是 0=否
     */
    @ApiModelProperty("是否默认，1=是 0=否")
    private Boolean isDefault;

    /**
     * 是否是多级目录，1=是 0=否
     */
    @ApiModelProperty("是否是多级目录，1=是 0=否")
    private Boolean isMultiLevel;
}
