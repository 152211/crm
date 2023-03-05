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
 * 用户权限对应关系Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2021-11-30 11:22:10
 **/
@TableName("sys_user_permission")
@Getter
@Setter
@ToString
public class SysUserPermissionDO extends BaseDO {


   /**
    *  主键
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

   /**
    *  用户ID
    */
    @TableField("user_id")
    private Long userId;

   /**
    *  权限id
    */
    @TableField("permission_id")
    private Long permissionId;

   /**
    *  权限code
    */
    @TableField("permission_code")
    private String permissionCode;

}
