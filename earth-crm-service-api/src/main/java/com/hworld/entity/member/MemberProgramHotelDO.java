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
 * 会员计划于酒店关联关系Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-07-21 16:43:03
 **/
@TableName("member_program_hotel")
@Getter
@Setter
@ToString
public class MemberProgramHotelDO extends BaseDO {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 会员计划ID
     */
    @TableField("member_program_id")
    private Long memberProgramId;

    /**
     * 酒店ID
     */
    @TableField("hotel_id")
    private Long hotelId;

    /**
     * 酒店code
     */
    @TableField("hotel_code")
    private String hotelCode;

}