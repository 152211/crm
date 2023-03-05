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
 * 会员计划Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-07-21 16:43:03
 **/
@TableName("member_program")
@Getter
@Setter
@ToString
public class MemberProgramDO extends BaseDO {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 会员计划code
     */
    @TableField("program_code")
    private String programCode;

    /**
     * 会员计划名称
     */
    @TableField("program_name")
    private String programName;

    /**
     * 是否校验最后一位：1:校验,0:不校验
     */
    @TableField("is_check_code")
    private Boolean isCheckCode;

    /**
     * 是否时默认:0:非默认 1:默认
     */
    @TableField("is_default")
    private Boolean isDefault;

    /**
     * 是否禁用:1:禁用 0:启用
     */
    @TableField("is_disable")
    private Boolean isDisable;

    /**
     * 内部 I,外部 E
     */
    @TableField("type")
    private String type;

    /**
     * 会员编号长度
     */
    @TableField("member_code_length")
    private String memberCodeLength;

    /**
     * 会员编号前缀
     */
    @TableField("member_number_prefix")
    private String memberNumberPrefix;

    /**
     * 会员计划图标
     */
    @TableField("logo_url")
    private String logoUrl;

    /**
     * 积分POINT;里程MILEAGE
     */
    @TableField("reward_type")
    private String rewardType;
}