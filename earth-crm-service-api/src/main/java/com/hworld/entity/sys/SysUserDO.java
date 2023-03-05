package com.hworld.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hworld.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 员工管理Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2021-11-30 11:22:10
 **/
@TableName("sys_user")
@Getter
@Setter
@ToString
public class SysUserDO extends BaseDO {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 登录用户ID，global_db.login.id
     */
    @TableField("pass_word")
    private String passWord;

    /**
     * 员工工号，可作为用户名登入CRM
     */
    @TableField("employee_no")
    private String employeeNo;

    /**
     * 员工姓名
     */
    @TableField("name")
    private String name;

    /**
     * 姓
     */
    @TableField("last_name")
    private String lastName;

    /**
     * 名
     */
    @TableField("first_name")
    private String firstName;

    /**
     * F: 女 M: 男 N:未知
     */
    @TableField("gender")
    private String gender;

    /**
     * 身份证
     */
    @TableField("certificate_number")
    private String certificateNumber;

    /**
     * 语言
     */
    @TableField("language")
    private String language;

    /**
     * 岗位头衔
     */
    @TableField("job_title")
    private String jobTitle;

    /**
     * 员工手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 联系地址
     */
    @TableField("address")
    private String address;

    /**
     * O 在职 D离职 T 试用 P 在途
     */
    @TableField("status")
    private String status;

    /**
     * 员工类型 1 内部 2 外部
     */
    @TableField("type")
    private String type;

    /**
     * 来源
     */
    @TableField("source")
    private String source;

    /**
     * 用户头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 最近一次登录时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;
}