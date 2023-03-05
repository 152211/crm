package com.hworld.vo.req.member;

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

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 会员
 *
 * @author caoyang
 * @date 2022-07-21 16:43:01
 */
@ApiModel("会员")
@Getter
@Setter
@ToString
public class MemberProfileReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class, SelectOne.class})
    private Long id;
    /**
     * 会员编号
     */
    @ApiModelProperty("会员编号")
    @Length(min = 0, max = 20, message = "memberCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String memberCode;
    /**
     * 会员姓名
     */
    @ApiModelProperty("会员姓名")
    @Length(min = 0, max = 50, message = "memberName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String memberName;
    /**
     * 会员类型编号
     */
    @ApiModelProperty("会员类型编号")
    @Length(min = 0, max = 2, message = "memberTypeCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String memberTypeCode;
    /**
     * 名
     */
    @ApiModelProperty("名")
    @Length(min = 0, max = 50, message = "firstName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String firstName;
    /**
     * 姓
     */
    @ApiModelProperty("姓")
    @Length(min = 0, max = 50, message = "lastName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String lastName;
    /**
     * 客例ID
     */
    @ApiModelProperty("客例ID")
    @Length(min = 0, max = 20, message = "profId length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String profId;
    /**
     * sso会员id
     */
    @ApiModelProperty("sso会员id")
    @Length(min = 0, max = 20, message = "ssoId length must in [{min},{max}]", groups = {Create.class, Update.class})
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
    @Length(min = 0, max = 20, message = "memberProgramCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String memberProgramCode;
    /**
     * 会员编号长度
     */
    @ApiModelProperty("会员编号长度")
    @Length(min = 0, max = 2, message = "memberCodeLength length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String memberCodeLength;
    /**
     * 会员编号前缀
     */
    @ApiModelProperty("会员编号前缀")
    @Length(min = 0, max = 5, message = "memberCodePrefix length must in [{min},{max}]", groups = {Create.class, Update.class})
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
    @Length(min = 0, max = 20, message = "memberLevelCode length must in [{min},{max}]", groups = {Create.class, Update.class})
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
    @Length(min = 0, max = 18, message = "consumptionPoint length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String consumptionPoint;
    /**
     * 定级积分
     */
    @ApiModelProperty("定级积分")
    @Length(min = 0, max = 18, message = "upgradePoint length must in [{min},{max}]", groups = {Create.class, Update.class})
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
    @Length(min = 0, max = 20, message = "mobilePhone length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String mobilePhone;
    /**
     * 手机号码区号
     */
    @ApiModelProperty("手机号码区号")
    @Length(min = 0, max = 20, message = "mobilePhoneCountryCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String mobilePhoneCountryCode;
    /**
     * 家庭电话
     */
    @ApiModelProperty("家庭电话")
    @Length(min = 0, max = 20, message = "homePhone length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String homePhone;
    /**
     * 家庭电话区号
     */
    @ApiModelProperty("家庭电话区号")
    @Length(min = 0, max = 20, message = "homePhoneCountryCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String homePhoneCountryCode;
    /**
     * 公司电话
     */
    @ApiModelProperty("公司电话")
    @Length(min = 0, max = 20, message = "companyPhone length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String companyPhone;
    /**
     * 公司电话区号
     */
    @ApiModelProperty("公司电话区号")
    @Length(min = 0, max = 20, message = "companyPhoneCountryCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String companyPhoneCountryCode;
    /**
     * 邮箱地址
     */
    @ApiModelProperty("邮箱地址")
    @Length(min = 0, max = 255, message = "email length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String email;
    /**
     * 邮箱状态，0=未激活 1=已激活
     */
    @ApiModelProperty("邮箱状态，0=未激活 1=已激活")
    @Length(min = 0, max = 1, message = "emailStates，0=未激活 1=已激活 length must in [{min},{max}]", groups = {Create.class, Update.class})
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
    @Length(min = 0, max = 20, message = "certificateTypeCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String certificateTypeCode;
    /**
     * 证件号码
     */
    @ApiModelProperty("证件号码")
    @Length(min = 0, max = 50, message = "certificateNumber length must in [{min},{max}]", groups = {Create.class, Update.class})
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
    @Length(min = 0, max = 0, message = "birthday length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String birthday;
    /**
     * 国籍编号
     */
    @ApiModelProperty("国籍编号")
    @Length(min = 0, max = 20, message = "nationalityCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String nationalityCode;
    /**
     * 国籍名称
     */
    @ApiModelProperty("国籍名称")
    @Length(min = 0, max = 255, message = "nationalityName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String nationalityName;
    /**
     * 国家编号
     */
    @ApiModelProperty("国家编号")
    @Length(min = 0, max = 20, message = "countryCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String countryCode;
    /**
     * 国家名称
     */
    @ApiModelProperty("国家名称")
    @Length(min = 0, max = 255, message = "countryName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String countryName;
    /**
     * 州/省编号
     */
    @ApiModelProperty("州/省编号")
    @Length(min = 0, max = 20, message = "stateCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String stateCode;
    /**
     * 州/省名称
     */
    @ApiModelProperty("州/省名称")
    @Length(min = 0, max = 50, message = "stateName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String stateName;
    /**
     * 城市编号
     */
    @ApiModelProperty("城市编号")
    @Length(min = 0, max = 20, message = "cityCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String cityCode;
    /**
     * 城市名称
     */
    @ApiModelProperty("城市名称")
    @Length(min = 0, max = 255, message = "cityName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String cityName;
    /**
     * 邮编
     */
    @ApiModelProperty("邮编")
    @Length(min = 0, max = 20, message = "zipCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String zipCode;
    /**
     * 区编号
     */
    @ApiModelProperty("区编号")
    @Length(min = 0, max = 20, message = "districtCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String districtCode;
    /**
     * 区名称
     */
    @ApiModelProperty("区名称")
    @Length(min = 0, max = 255, message = "districtName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String districtName;
    /**
     * 地址类型:H:家 C:公司
     */
    @ApiModelProperty("地址类型:H:家 C:公司")
    @Length(min = 0, max = 1, message = "addressType length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String addressType;
    /**
     * 地址
     */
    @ApiModelProperty("地址")
    @Length(min = 0, max = 500, message = "address length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String address;
    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    @Length(min = 0, max = 500, message = "detailedAddress length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String detailedAddress;
    /**
     * 职称编号
     */
    @ApiModelProperty("职称编号")
    @Length(min = 0, max = 20, message = "jobTitleCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String jobTitleCode;
    /**
     * 职称
     */
    @ApiModelProperty("职称")
    @Length(min = 0, max = 255, message = "jobTitleName length must in [{min},{max}]", groups = {Create.class, Update.class})
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
    @Length(min = 0, max = 50, message = "passwordHash length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String passwordHash;
    /**
     * 加密密码的盐
     */
    @ApiModelProperty("加密密码的盐")
    @Length(min = 0, max = 10, message = "passwordSalt length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String passwordSalt;
    /**
     * 注册来源Code
     */
    @ApiModelProperty("注册来源Code")
    @Length(min = 0, max = 20, message = "sourceCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String sourceCode;
    /**
     * 注册来源名称
     */
    @ApiModelProperty("注册来源名称")
    @Length(min = 0, max = 255, message = "sourceName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String sourceName;
    /**
     * 渠道code
     */
    @ApiModelProperty("渠道code")
    @Length(min = 0, max = 20, message = "channelCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String channelCode;
    /**
     * 渠道名称
     */
    @ApiModelProperty("渠道名称")
    @Length(min = 0, max = 255, message = "channelName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String channelName;
    /**
     * 语言编号
     */
    @ApiModelProperty("语言编号")
    @Length(min = 0, max = 20, message = "languageCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String languageCode;
    /**
     * 语言名称
     */
    @ApiModelProperty("语言名称")
    @Length(min = 0, max = 255, message = "languageName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String languageName;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @Length(min = 0, max = 255, message = "remarks length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String remarks;
}
