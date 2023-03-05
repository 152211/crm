package com.hworld.vo.resp.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Create;
import com.hworld.annotation.Update;
import com.hworld.vo.req.BaseReqVO;
import com.hworld.vo.resp.BaseRespVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * 员工管理DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:12
 */
@ApiModel("员工管理")
@Getter
@Setter
@ToString
public class SysUserRespVO extends BaseRespVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

//    /**
//     * 密码加密
//     */
//    @ApiModelProperty("密码加密")
//    @Length(min = 0, max = 255, message = "passWord length must in [{min},{max}]", groups = {Create.class, Update.class})
//    private String passWord;

    /**
     * 员工工号，可作为用户名登入CRM
     */
    @ApiModelProperty("员工工号，可作为用户名登入CRM")
    private String employeeNo;

    /**
     * 员工姓名
     */
    @ApiModelProperty("员工姓名")
    private String name;

    /**
     * 姓
     */
    @ApiModelProperty("姓")
    private String lastName;

    /**
     * 名
     */
    @ApiModelProperty("名")
    private String firstName;

    /**
     * F: 女 M: 男 N:未知
     */
    @ApiModelProperty("F: 女 M: 男 N:未知")
    private String gender;

    /**
     * 身份证
     */
    @ApiModelProperty("身份证")
    private String certificateNumber;

    /**
     * 语言
     */
    @ApiModelProperty("语言")
    private String language;

    /**
     * 岗位头衔
     */
    @ApiModelProperty("岗位头衔")
    private String jobTitle;

    /**
     * 员工手机号
     */
    @ApiModelProperty("员工手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 联系地址
     */
    @ApiModelProperty("联系地址")
    private String address;

    /**
     * O 在职 D离职 T 试用 P 在途
     */
    @ApiModelProperty("O 在职 D离职 T 试用 P 在途")
    private String status;

    /**
     * 员工类型 1 内部 2 外部
     */
    @ApiModelProperty("员工类型 1 内部 2 外部")
    private String type;

    /**
     * 来源
     */
    @ApiModelProperty("来源")
    private String source;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;

    /**
     * 最近一次登录时间
     */
    @ApiModelProperty("最近一次登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;
}
