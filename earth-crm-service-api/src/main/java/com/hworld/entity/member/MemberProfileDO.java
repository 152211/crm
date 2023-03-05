package com.hworld.entity.member;

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
 * 未知Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-07-21 16:43:01
 **/
@TableName("member_profile")
@Getter
@Setter
@ToString
public class MemberProfileDO extends BaseDO {


    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 会员编号
     */
    @TableField("member_code")
    private String memberCode;

    /**
     * 会员姓名
     */
    @TableField("member_name")
    private String memberName;

    /**
     * 会员类型编号
     */
    @TableField("member_type_code")
    private String memberTypeCode;

    /**
     * 名
     */
    @TableField("first_name")
    private String firstName;

    /**
     * 姓
     */
    @TableField("last_name")
    private String lastName;

    /**
     * 客例ID
     */
    @TableField("prof_id")
    private String profId;

    /**
     * sso会员id
     */
    @TableField("sso_id")
    private String ssoId;

    /**
     * 会员计划id
     */
    @TableField("member_program_id")
    private Long memberProgramId;

    /**
     * 会员计划code
     */
    @TableField("member_program_code")
    private String memberProgramCode;

    /**
     * 会员编号长度
     */
    @TableField("member_code_length")
    private String memberCodeLength;

    /**
     * 会员编号前缀
     */
    @TableField("member_code_prefix")
    private String memberCodePrefix;

    /**
     * 会员等级ID
     */
    @TableField("member_level_id")
    private Long memberLevelId;

    /**
     * 会员等级编号
     */
    @TableField("member_level_code")
    private String memberLevelCode;

    /**
     * 会员等级变化时间
     */
    @TableField("member_level_modified_time")
    private LocalDateTime memberLevelModifiedTime;

    /**
     * 会员等级过期时间
     */
    @TableField("member_level_expired_time")
    private LocalDateTime memberLevelExpiredTime;

    /**
     * 奖励积分,消费积分
     */
    @TableField("consumption_point")
    private String consumptionPoint;

    /**
     * 定级积分
     */
    @TableField("upgrade_point")
    private String upgradePoint;

    /**
     * 定级间夜
     */
    @TableField("upgrade_night")
    private Integer upgradeNight;

    /**
     * 手机号码
     */
    @TableField("mobile_phone")
    private String mobilePhone;

    /**
     * 手机号码区号
     */
    @TableField("mobile_phone_country_code")
    private String mobilePhoneCountryCode;

    /**
     * 家庭电话
     */
    @TableField("home_phone")
    private String homePhone;

    /**
     * 家庭电话区号
     */
    @TableField("home_phone_country_code")
    private String homePhoneCountryCode;

    /**
     * 公司电话
     */
    @TableField("company_phone")
    private String companyPhone;

    /**
     * 公司电话区号
     */
    @TableField("company_phone_country_code")
    private String companyPhoneCountryCode;

    /**
     * 邮箱地址
     */
    @TableField("email")
    private String email;

    /**
     * 邮箱状态，0=未激活 1=已激活
     */
    @TableField("email_states")
    private String emailStates;

    /**
     * 是否禁用: 0:未禁用  1:已禁用
     */
    @TableField("is_disable")
    private Boolean isDisable;

    /**
     * 是否隐藏: 0:未隐藏  1:已隐藏
     */
    @TableField("is_hidden")
    private Boolean isHidden;

    /**
     * 是否匿名: 0:未匿名  1:已匿名
     */
    @TableField("is_anonymity")
    private Boolean isAnonymity;

    /**
     * 证件类型编号
     */
    @TableField("certificate_type_code")
    private String certificateTypeCode;

    /**
     * 证件号码
     */
    @TableField("certificate_number")
    private String certificateNumber;

    /**
     * 性别:0:未知 1:男 2:女 3:第三性别
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 生日
     */
    @TableField("birthday")
    private String birthday;

    /**
     * 国籍编号
     */
    @TableField("nationality_code")
    private String nationalityCode;

    /**
     * 国籍名称
     */
    @TableField("nationality_name")
    private String nationalityName;

    /**
     * 国家编号
     */
    @TableField("country_code")
    private String countryCode;

    /**
     * 国家名称
     */
    @TableField("country_name")
    private String countryName;

    /**
     * 州/省编号
     */
    @TableField("state_code")
    private String stateCode;

    /**
     * 州/省名称
     */
    @TableField("state_name")
    private String stateName;

    /**
     * 城市编号
     */
    @TableField("city_code")
    private String cityCode;

    /**
     * 城市名称
     */
    @TableField("city_name")
    private String cityName;

    /**
     * 邮编
     */
    @TableField("zip_code")
    private String zipCode;

    /**
     * 区编号
     */
    @TableField("district_code")
    private String districtCode;

    /**
     * 区名称
     */
    @TableField("district_name")
    private String districtName;

    /**
     * 地址类型:H:家 C:公司
     */
    @TableField("address_type")
    private String addressType;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 详细地址
     */
    @TableField("detailed_address")
    private String detailedAddress;

    /**
     * 职称编号
     */
    @TableField("job_title_code")
    private String jobTitleCode;

    /**
     * 职称
     */
    @TableField("job_title_name")
    private String jobTitleName;

    /**
     * 注册时间
     */
    @TableField("registration_time")
    private LocalDateTime registrationTime;

    /**
     * 密码
     */
    @TableField("password_hash")
    private String passwordHash;

    /**
     * 加密密码的盐
     */
    @TableField("password_salt")
    private String passwordSalt;

    /**
     * 注册来源Code
     */
    @TableField("source_code")
    private String sourceCode;

    /**
     * 注册来源名称
     */
    @TableField("source_name")
    private String sourceName;

    /**
     * 渠道code
     */
    @TableField("channel_code")
    private String channelCode;

    /**
     * 渠道名称
     */
    @TableField("channel_name")
    private String channelName;

    /**
     * 语言编号
     */
    @TableField("language_code")
    private String languageCode;

    /**
     * 语言名称
     */
    @TableField("language_name")
    private String languageName;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;
}