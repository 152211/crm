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
 * 系统字典分组Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-02-08 16:00:50
 **/
@TableName("sys_dict_group")
@Getter
@Setter
@ToString
public class SysDictGroupDO extends BaseDO {


   /**
    *  主键
    */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

   /**
    *  分组编码
    */
    @TableField("group_code")
    private String groupCode;

   /**
    *  分组英文名称
    */
    @TableField("group_name_en")
    private String groupNameEn;

   /**
    *  分组中文名称
    */
    @TableField("group_name_cn")
    private String groupNameCn;

   /**
    *  描述
    */
    @TableField("description")
    private String description;
}