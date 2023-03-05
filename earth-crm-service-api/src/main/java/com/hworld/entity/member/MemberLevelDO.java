package com.hworld.entity.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hworld.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 会员等级维护Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-07-21 16:43:00
 **/
@TableName("member_level")
@Getter
@Setter
@ToString
public class MemberLevelDO extends BaseDO {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 会员等级名称
     */
    @TableField("name")
    private String name;

    /**
     * 等级code
     */
    @TableField("code")
    private String code;

    /**
     * 等级排序，数字越大级别越大
     */
    @TableField("ordinal")
    private Integer ordinal;

    /**
     * 会员计划ID
     */
    @TableField("member_program_id")
    private Long memberProgramId;

    /**
     * 会员计划code
     */
    @TableField("member_program_code")
    private String memberProgramCode;

    /**
     * 会员类型
     */
    @TableField("member_type")
    private String memberType;

    /**
     * 售价
     */
    @TableField("price")
    private String price;

    /**
     * icon图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 是否默认，1=是 0=否
     */
    @TableField("is_default")
    private Boolean isDefault;

    /**
     * 有效性:0:禁用 1:启用
     */
    @TableField("is_disable")
    private Boolean isDisable;

    /**
     * 是否允许销售，1=允许 0=不允许
     */
    @TableField("sale_flag")
    private Boolean saleFlag;
}