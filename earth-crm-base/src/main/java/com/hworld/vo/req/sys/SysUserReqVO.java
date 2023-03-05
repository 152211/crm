package com.hworld.vo.req.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Create;
import com.hworld.annotation.Delelt;
import com.hworld.annotation.SelectOne;
import com.hworld.annotation.Update;
import com.hworld.vo.req.BaseReqVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

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
public class SysUserReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class, SelectOne.class})
    private Long id;

    /**
     * 密码加密
     */
    @ApiModelProperty("密码加密")
    @Length(min = 0, max = 255, message = "passWord length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String passWord;

    /**
     * 员工工号，可作为用户名登入CRM
     */
    @ApiModelProperty("员工工号，可作为用户名登入CRM")
    @Length(min = 0, max = 50, message = "employeeNo length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "employeeNo Can not be empty", groups = {Create.class, Update.class})
    private String employeeNo;

    /**
     * 员工姓名
     */
    @ApiModelProperty("员工姓名")
    @Length(min = 0, max = 100, message = "name length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "name Can not be empty", groups = {Create.class, Update.class})
    private String name;

    /**
     * 姓
     */
    @ApiModelProperty("姓")
    @Length(min = 0, max = 100, message = "lastName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String lastName;

    /**
     * 名
     */
    @ApiModelProperty("名")
    @Length(min = 0, max = 100, message = "firstName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String firstName;

    /**
     * F: 女 M: 男 N:未知
     */
    @ApiModelProperty("F: 女 M: 男 N:未知")
    @Length(min = 0, max = 1, message = "gender length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String gender;

    /**
     * 身份证
     */
    @ApiModelProperty("身份证")
    @Length(min = 0, max = 50, message = "certificateNumber length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String certificateNumber;

    /**
     * 语言
     */
    @ApiModelProperty("语言")
    @Length(min = 0, max = 50, message = "language length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String language;

    /**
     * 岗位头衔
     */
    @ApiModelProperty("岗位头衔")
    @Length(min = 0, max = 50, message = "jobTitle length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String jobTitle;

    /**
     * 员工手机号
     */
    @ApiModelProperty("员工手机号")
    @Length(min = 0, max = 20, message = "mobile length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String mobile;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @Length(min = 0, max = 50, message = "email length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String email;

    /**
     * 联系地址
     */
    @ApiModelProperty("联系地址")
    @Length(min = 0, max = 500, message = "address length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String address;

    /**
     * O 在职 D离职 T 试用 P 在途
     */
    @ApiModelProperty("O 在职 D离职 T 试用 P 在途")
    @Length(min = 0, max = 1, message = "status length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String status;

    /**
     * 员工类型 1 内部 2 外部
     */
    @ApiModelProperty("员工类型 1 内部 2 外部")
    @Length(min = 0, max = 1, message = "type length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String type;

    /**
     * 来源
     */
    @ApiModelProperty("来源")
    @Length(min = 0, max = 10, message = "source length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String source;

    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    @Length(min = 0, max = 255, message = "avatar length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String avatar;

    /**
     * 最近一次登录时间
     */
    @ApiModelProperty("最近一次登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;
}
