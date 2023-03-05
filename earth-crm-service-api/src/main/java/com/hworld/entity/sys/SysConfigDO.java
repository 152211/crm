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
 * 系统配置Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-02-08 16:00:49
 **/
@TableName("sys_config")
@Getter
@Setter
@ToString
public class SysConfigDO extends BaseDO {


   /**
    *  主键
    */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

   /**
    *  配置编码
    */
    @TableField("config_code")
    private String configCode;

   /**
    *  配置值
    */
    @TableField("config_value")
    private String configValue;

   /**
    *  英文名称
    */
    @TableField("config_name_en")
    private String configNameEn;

   /**
    *  中文名称
    */
    @TableField("config_name_cn")
    private String configNameCn;

   /**
    *  描述
    */
    @TableField("description")
    private String description;
}