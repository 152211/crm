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
 * 系统字典选项Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-02-08 16:00:52
 **/
@TableName("sys_dict_item")
@Getter
@Setter
@ToString
public class SysDictItemDO extends BaseDO {


   /**
    *  主键
    */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

   /**
    *  分组ID
    */
    @TableField("group_id")
    private String groupId;

   /**
    *  分组编码
    */
    @TableField("group_code")
    private String groupCode;

   /**
    *  选项编码
    */
    @TableField("item_code")
    private String itemCode;

   /**
    *  选项值
    */
    @TableField("item_value")
    private String itemValue;

   /**
    *  英文名称
    */
    @TableField("item_name_en")
    private String itemNameEn;

   /**
    *  中文名称
    */
    @TableField("item_name_cn")
    private String itemNameCn;

   /**
    *  序号
    */
    @TableField("order_num")
    private Integer orderNum;

   /**
    *  描述
    */
    @TableField("description")
    private String description;
}