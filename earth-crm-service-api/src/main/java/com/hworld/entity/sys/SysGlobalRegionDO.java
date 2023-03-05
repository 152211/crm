package com.hworld.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hworld.base.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

/**
 * 全球地区Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-07-21 16:43:04
 **/
@TableName("sys_global_region")
@Getter
@Setter
@ToString
public class SysGlobalRegionDO extends BaseDO {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 父ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 父areaCode
     */
    @TableField("parent_code")
    private String parentCode;

    /**
     * 层级。Continent:7大洲 Country:国家 State:省/州 City:市
     */
    @TableField("area_level_code")
    private String areaLevelCode;

    /**
     * 路径
     */
    @TableField("area_path")
    private String areaPath;

    /**
     * areaCode路径
     */
    @TableField("area_code_path")
    private String areaCodePath;

    /**
     * 国际行政区域编号
     */
    @TableField("area_code")
    private String areaCode;


    /**
     * 中文名称
     */
    @TableField("name_cn")
    private String nameCn;

    /**
     * 英文名称
     */
    @TableField("name_en")
    private String nameEn;

    /**
     * 中文拼音
     */
    @TableField("name_pinyin_all")
    private String namePinyinAll;

    /**
     * 拼音缩写
     */
    @TableField("name_pinyin_short")
    private String namePinyinShort;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;
}