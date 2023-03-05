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
 * 角色维护Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2021-11-30 11:22:09
 **/
@TableName("sys_role")
@Getter
@Setter
@ToString
public class SysRoleDO extends BaseDO {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 角色code
     */
    @TableField("role_code")
    private String roleCode;

    /**
     * 角色中文名称
     */
    @TableField("role_name_cn")
    private String roleNameCn;

    /**
     * 角色英文名称
     */
    @TableField("role_name_en")
    private String roleNameEn;

    /**
     * 是否超级管理员，忽略角色，取租户下所有权限，0=否 1=是
     */
    @TableField("is_admin")
    private Boolean isAdmin;

    /**
     * 是否默认，0=否 1=是
     */
    @TableField("is_default")
    private Boolean isDefault;

    /**
     * 状态，E=启用 D=禁用
     */
    @TableField("status")
    private String status;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * CUSTOMER自定义;TEMPLATE模板
     */
    @TableField("rule_type")
    private String ruleType;
}
