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
 * 用户角色对应关系Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2021-11-30 11:22:10
 **/
@TableName("sys_user_role")
@Getter
@Setter
@ToString
public class SysUserRoleDO extends BaseDO {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 角色code
     */
    @TableField("role_code")
    private String roleCode;
}