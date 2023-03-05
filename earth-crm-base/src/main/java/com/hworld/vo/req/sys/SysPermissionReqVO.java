package com.hworld.vo.req.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Create;
import com.hworld.annotation.Delelt;
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
 * 系统权限DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:07
 */
@ApiModel("系统权限")
@Getter
@Setter
@ToString
public class SysPermissionReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class})
    private Long id;

    /**
     * 父级权限ID
     */
    @ApiModelProperty("父级权限ID")
    @NotNull(message = "parentId Can not be empty", groups = {Create.class, Update.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * 权限Code
     */
    @ApiModelProperty("权限Code")
    @Length(min = 0, max = 50, message = "permissionCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "permissionCode Can not be empty", groups = {Create.class, Update.class})
    private String permissionCode;

    /**
     * 组件
     */
    @ApiModelProperty("组件")
    @Length(min = 0, max = 255, message = "permissionComponent length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "permissionComponent Can not be empty", groups = {Create.class, Update.class})
    private String permissionComponent;

    /**
     * 权限类型 M：菜单权限，F：功能权限
     */
    @ApiModelProperty("权限类型 M：菜单权限，F：功能权限")
    @NotNull(message = "permissionType Can not be empty", groups = {Create.class, Update.class})
    private String permissionType;

    /**
     * 权限url
     */
    @ApiModelProperty("权限url")
    @Length(min = 0, max = 255, message = "permissionUrl length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String permissionUrl;

    /**
     * 权限中文名称
     */
    @ApiModelProperty("权限中文名称")
    @Length(min = 0, max = 50, message = "permissionNameCn length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String permissionNameCn;

    /**
     * 权限英文名称
     */
    @ApiModelProperty("权限英文名称")
    @Length(min = 0, max = 50, message = "permissionNameEn length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String permissionNameEn;

    /**
     * 图标
     */
    @ApiModelProperty("图标")
    @Length(min = 0, max = 50, message = "permissionIcon length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "permissionIcon Can not be empty", groups = {Create.class, Update.class})
    private String permissionIcon;

    /**
     * 是否默认，1=是 0=否
     */
    @ApiModelProperty("是否默认，1=是 0=否")
    @NotNull(message = "isDefault Can not be empty", groups = {Create.class, Update.class})
    private Boolean isDefault;

    /**
     * 是否是多级目录，1=是 0=否
     */
    @ApiModelProperty("是否是多级目录，1=是 0=否")
    @NotNull(message = "isMultiLevel Can not be empty", groups = {Create.class, Update.class})
    private Boolean isMultiLevel;
}
