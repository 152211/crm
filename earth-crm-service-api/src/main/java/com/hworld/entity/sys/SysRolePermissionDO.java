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
 * 角色权限对应关系Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2021-11-30 11:22:09
 **/
@TableName("sys_role_permission")
@Getter
@Setter
@ToString
public class SysRolePermissionDO extends BaseDO {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 角色code
     */
    @TableField("role_code")
    private String roleCode;

    /**
     * 权限id
     */
    @TableField("permission_id")
    private Long permissionId;

    /**
     * 权限code
     */
    @TableField("permission_code")
    private String permissionCode;

    /**
     * A允许 D禁止
     */
    @TableField("auth_type")
    private String authType;
}
