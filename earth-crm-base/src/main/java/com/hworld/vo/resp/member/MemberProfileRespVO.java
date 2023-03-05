package com.hworld.vo.resp.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Update;
import com.hworld.vo.resp.BaseRespVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 会员DTO
 *
 * @author caoyang
 * @date 2022-02-15 17:26:20
 */
@ApiModel("会员")
@Getter
@Setter
@ToString
public class MemberProfileRespVO extends BaseRespVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 会员编号
     */
    @ApiModelProperty("会员编号")
    private String memberCode;
    /**
     * 会员姓名
     */
    @ApiModelProperty("会员姓名")
    private String memberName;
    /**
     * 会员类型编号
     */
    @ApiModelProperty("会员类型编号")
    private String memberTypeCode;
    /**
     * 名
     */
    @ApiModelProperty("名")
    private String firstName;
    /**
     * 姓
     */
    @ApiModelProperty("姓")
    private String lastName;
    /**
     * 客例ID
     */
    @ApiModelProperty("客例ID")
    private String profId;
    /**
     * sso会员id
     */
    @ApiModelProperty("sso会员id")
    private String ssoId;
    /**
     * 会员计划id
     */
    @ApiModelProperty("会员计划id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long memberProgramId;
    /**
     * 会员计划code
     */
    @ApiModelProperty("会员计划code")
    private String memberProgramCode;
    /**
     * 会员编号长度
     */
    @ApiModelProperty("会员编号长度")
    private String memberCodeLength;
    /**
     * 会员编号前缀
     */
    @ApiModelProperty("会员编号前缀")
    private String memberCodePrefix;
    /**
     * 会员等级ID
     */
    @ApiModelProperty("会员等级ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long memberLevelId;
    /**
     * 会员等级编号
     */
    @ApiModelProperty("会员等级编号")
    private String memberLevelCode;
    /**
     * 会员等级变化时间
     */
    @ApiModelProperty("会员等级变化时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime memberLevelModifiedTime;
    /**
     * 会员等级过期时间
     */
    @ApiModelProperty("会员等级过期时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime memberLevelExpiredTime;
    /**
     * 奖励积分,消费积分
     */
    @ApiModelProperty("奖励积分,消费积分")
    private String consumptionPoint;
    /**
     * 定级积分
     */
    @ApiModelProperty("定级积分")
    private String upgradePoint;
    /**
     * 定级间夜
     */
    @ApiModelProperty("定级间夜")
    private Integer upgradeNight;
    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String mobilePhone;
    /**
     * 手机号码区号
     */
    @ApiModelProperty("手机号码区号")
    private String mobilePhoneCountryCode;
    /**
     * 家庭电话
     */
    @ApiModelProperty("家庭电话")
    private String homePhone;
    /**
     * 家庭电话区号
     */
    @ApiModelProperty("家庭电话区号")
    private String homePhoneCountryCode;
    /**
     * 公司电话
     */
    @ApiModelProperty("公司电话")
    private String companyPhone;
    /**
     * 公司电话区号
     */
    @ApiModelProperty("公司电话区号")
    private String companyPhoneCountryCode;
    /**
     * 邮箱地址
     */
    @ApiModelProperty("邮箱地址")
    private String email;
    /**
     * 邮箱状态，0=未激活 1=已激活
     */
    @ApiModelProperty("邮箱状态，0=未激活 1=已激活")
    private String emailStates;
    /**
     * 是否禁用: 0:未禁用  1:已禁用
     */
    @ApiModelProperty("是否禁用: 0:未禁用  1:已禁用")
    private Boolean isDisable;
    /**
     * 是否隐藏: 0:未隐藏  1:已隐藏
     */
    @ApiModelProperty("是否隐藏: 0:未隐藏  1:已隐藏")
    private Boolean isHidden;
    /**
     * 是否匿名: 0:未匿名  1:已匿名
     */
    @ApiModelProperty("是否匿名: 0:未匿名  1:已匿名")
    private Boolean isAnonymity;
    /**
     * 证件类型编号
     */
    @ApiModelProperty("证件类型编号")
    private String certificateTypeCode;
    /**
     * 证件号码
     */
    @ApiModelProperty("证件号码")
    private String certificateNumber;
    /**
     * 性别:0:未知 1:男 2:女 3:第三性别
     */
    @ApiModelProperty("性别:0:未知 1:男 2:女 3:第三性别")
    private Integer gender;
    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private String birthday;
    /**
     * 国籍编号
     */
    @ApiModelProperty("国籍编号")
    private String nationalityCode;
    /**
     * 国籍名称
     */
    @ApiModelProperty("国籍名称")
    private String nationalityName;
    /**
     * 国家编号
     */
    @ApiModelProperty("国家编号")
    private String countryCode;
    /**
     * 国家名称
     */
    @ApiModelProperty("国家名称")
    private String countryName;
    /**
     * 州/省编号
     */
    @ApiModelProperty("州/省编号")
    private String stateCode;
    /**
     * 州/省名称
     */
    @ApiModelProperty("州/省名称")
    private String stateName;
    /**
     * 城市编号
     */
    @ApiModelProperty("城市编号")
    private String cityCode;
    /**
     * 城市名称
     */
    @ApiModelProperty("城市名称")
    private String cityName;
    /**
     * 邮编
     */
    @ApiModelProperty("邮编")
    private String zipCode;
    /**
     * 区编号
     */
    @ApiModelProperty("区编号")
    private String districtCode;
    /**
     * 区名称
     */
    @ApiModelProperty("区名称")
    private String districtName;
    /**
     * 地址类型:H:家 C:公司
     */
    @ApiModelProperty("地址类型:H:家 C:公司")
    private String addressType;
    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;
    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    private String detailedAddress;
    /**
     * 职称编号
     */
    @ApiModelProperty("职称编号")
    private String jobTitleCode;
    /**
     * 职称
     */
    @ApiModelProperty("职称")
    private String jobTitleName;
    /**
     * 注册时间
     */
    @ApiModelProperty("注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationTime;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String passwordHash;
    /**
     * 加密密码的盐
     */
    @ApiModelProperty("加密密码的盐")
    private String passwordSalt;
    /**
     * 注册来源Code
     */
    @ApiModelProperty("注册来源Code")
    private String sourceCode;
    /**
     * 注册来源名称
     */
    @ApiModelProperty("注册来源名称")
    private String sourceName;
    /**
     * 渠道code
     */
    @ApiModelProperty("渠道code")
    private String channelCode;
    /**
     * 渠道名称
     */
    @ApiModelProperty("渠道名称")
    private String channelName;
    /**
     * 语言编号
     */
    @ApiModelProperty("语言编号")
    private String languageCode;
    /**
     * 语言名称
     */
    @ApiModelProperty("语言名称")
    private String languageName;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;
}
