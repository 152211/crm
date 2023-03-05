package com.hworld.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hworld.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

/**
 * 用户所属酒店关系Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-07-21 16:43:06
 **/
@TableName("sys_user_hotel")
@Getter
@Setter
@ToString
public class SysUserHotelDO extends BaseDO {


    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 酒店ID
     */
    @TableField("hotel_id")
    private Long hotelId;

    /**
     * 酒店编号于hdata保持一致
     */
    @TableField("hotel_no")
    private String hotelNo;

    /**
     * 是否是管理员0:不是 1:是
     */
    @TableField("is_manager")
    private Boolean isManager;
}