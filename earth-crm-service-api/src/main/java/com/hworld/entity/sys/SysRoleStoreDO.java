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
 * 角色酒店对应关系Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-02-08 16:00:53
 **/
@TableName("sys_role_store")
@Getter
@Setter
@ToString
public class SysRoleStoreDO extends BaseDO {


   /**
    *  主键
    */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

   /**
    *  角色ID
    */
    @TableField("role_id")
    private Long roleId;

   /**
    *  角色code
    */
    @TableField("role_code")
    private String roleCode;

   /**
    *  酒店ID
    */
    @TableField("store_id")
    private Long storeId;

   /**
    *  酒店编号
    */
    @TableField("store_no")
    private String storeNo;

   /**
    *  R-根节点,G-总部部门,A/B-品牌,S-门店
    */
    @TableField("store_type")
    private String storeType;

   /**
    *  酒店名称
    */
    @TableField("store_name")
    private String storeName;
}