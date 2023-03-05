package com.hworld.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hworld.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统权限Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2021-11-30 11:22:06
 **/
@TableName("sys_permission")
@Getter
@Setter
@ToString
public class SysPermissionDO extends BaseDO {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级权限ID
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 权限Code
     */
    @TableField("permission_code")
    private String permissionCode;

    /**
     * 组件
     */
    @TableField("permission_component")
    private String permissionComponent;

    /**
     * 权限类型 M：菜单权限，F：功能权限
     */
    @TableField("permission_type")
    private String permissionType;

    /**
     * 权限url
     */
    @TableField("permission_url")
    private String permissionUrl;

    /**
     * 权限中文名称
     */
    @TableField("permission_name_cn")
    private String permissionNameCn;

    /**
     * 权限英文名称
     */
    @TableField("permission_name_en")
    private String permissionNameEn;

    /**
     * 组件
     */
    @TableField("permission_icon")
    private String permissionIcon;

    /**
     * 是否默认，1=是 0=否
     */
    @TableField("is_default")
    private Boolean isDefault;

    /**
     * 是否是多级目录，1=是 0=否
     */
    @TableField("is_multi_level")
    private Boolean isMultiLevel;
}
