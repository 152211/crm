package com.hworld.vo.req.kafka;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Create;
import com.hworld.annotation.Update;
import com.hworld.vo.req.BaseReqVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.Instant;

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
public class MemberProfileKfkReqVO extends BaseReqVO {

    /**
     *
     */
    @ApiModelProperty("")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    @Min(value = 20, message = "租户ID不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long companyId;
    /**
     * 客历ID
     */
    @ApiModelProperty("客历ID")
    @Length(min = 0, max = 50, message = "客历ID必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String profId;
    /**
     * ssoid
     */
    @ApiModelProperty("ssoid")
    @Length(min = 0, max = 255, message = "ssoid必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String ssoId;
    /**
     * 会员计划ID
     */
    @ApiModelProperty("会员计划ID")
    @Min(value = 20, message = "会员计划ID不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long memberProgramId;
    /**
     * 会员计划名称
     */
    @ApiModelProperty("会员计划名称")
    @Length(min = 0, max = 255, message = "会员计划名称必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String memberProgramName;
    /**
     * 会员计划编码
     */
    @ApiModelProperty("会员计划编码")
    @Length(min = 0, max = 50, message = "会员计划编码必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String mapCode;
    /**
     *
     */
    @ApiModelProperty("")
    @Max(value = 5, message = "不能超过{value}个字符", groups = {Create.class, Update.class})
    private Integer memberNumberDigits;
    /**
     * 会员计划前缀
     */
    @ApiModelProperty("会员计划前缀")
    @Length(min = 0, max = 5, message = "会员计划前缀必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String memberNumberPrefix;
    /**
     * 会员当前等级ID,company_db.member_level.id
     */
    @ApiModelProperty("会员当前等级ID,company_db.member_level.id")
    @Min(value = 20, message = "会员当前等级ID,company_db.member_level.id不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long memberLevelId;
    /**
     * 会员等级code
     */
    @ApiModelProperty("会员等级code")
    @Length(min = 0, max = 50, message = "会员等级code必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String code;
    /**
     * 会员等级变更时间
     */
    @ApiModelProperty("会员等级变更时间")
    private Instant memberLevelModifiedTime;
    /**
     * 会员等级过期时间
     */
    @ApiModelProperty("会员等级过期时间")
    private Instant memberLevelExpiredTime;
    /**
     * 是否是默认会员1:是 0:不是
     */
    @ApiModelProperty("是否是默认会员1:是 0:不是")
    @Length(min = 0, max = 1, message = "是否是默认会员1:是 0:不是必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String isDefault;
    /**
     * 可用积分
     */
    @ApiModelProperty("可用积分")
    @Length(min = 0, max = 0, message = "可用积分必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String point;
    /**
     * 定级积分
     */
    @ApiModelProperty("定级积分")
    @Length(min = 0, max = 0, message = "定级积分必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String upgradePoint;
    /**
     * 定级间夜
     */
    @ApiModelProperty("定级间夜")
    @Max(value = 11, message = "定级间夜不能超过{value}个字符", groups = {Create.class, Update.class})
    private Integer upgradeRoomNight;
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    @Length(min = 0, max = 100, message = "姓名必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String name;
    /**
     * 姓名中文拼音
     */
    @ApiModelProperty("姓名中文拼音")
    @Length(min = 0, max = 100, message = "姓名中文拼音必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String namePinyin;
    /**
     * 姓名中文首拼
     */
    @ApiModelProperty("姓名中文首拼")
    @Length(min = 0, max = 100, message = "姓名中文首拼必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String nameFirstSpelling;
    /**
     * 名
     */
    @ApiModelProperty("名")
    @Length(min = 0, max = 50, message = "名必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String firstName;
    /**
     * 姓
     */
    @ApiModelProperty("姓")
    @Length(min = 0, max = 50, message = "姓必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String lastName;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @Length(min = 0, max = 64, message = "手机号必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String mobile;
    /**
     * 手机区号，global_db.iso_country.mobile_code
     */
    @ApiModelProperty("手机区号，global_db.iso_country.mobile_code")
    @Length(min = 0, max = 10, message = "手机区号，global_db.iso_country.mobile_code必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String mobileCountryCode;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @Length(min = 0, max = 100, message = "邮箱必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String email;
    /**
     * 邮箱激活状态，0=未激活 1或者空=已激活
     */
    @ApiModelProperty("邮箱激活状态，0=未激活 1或者空=已激活")
    @Length(min = 0, max = 1, message = "邮箱激活状态，0=未激活 1或者空=已激活必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String status;
    /**
     * 有效性  0：禁用  1：激活
     */
    @ApiModelProperty("有效性  0：禁用  1：激活")
    @Length(min = 0, max = 1, message = "有效性  0：禁用  1：激活必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String isValid;
    /**
     * 是否隐藏 0：非  1：是
     */
    @ApiModelProperty("是否隐藏 0：非  1：是")
    @Length(min = 0, max = 1, message = "是否隐藏 0：非  1：是必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String isHidden;
    /**
     * 学术头衔
     */
    @ApiModelProperty("学术头衔")
    @Length(min = 0, max = 255, message = "学术头衔必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String titleLearn;
    /**
     * 证件类型，global_db.certificate_type.id
     */
    @ApiModelProperty("证件类型，global_db.certificate_type.id")
    @Length(min = 0, max = 20, message = "证件类型，global_db.certificate_type.id必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String certificateTypeCode;
    /**
     * 证件号
     */
    @ApiModelProperty("证件号")
    @Length(min = 0, max = 50, message = "证件号必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String certificateNumber;
    /**
     * 会员类型，1=个人会员
     */
    @ApiModelProperty("会员类型，1=个人会员")
    @Min(value = 20, message = "会员类型，1=个人会员不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long memberTypeId;
    /**
     * M=Male, F=Female
     */
    @ApiModelProperty("M=Male, F=Female")
    @Length(min = 0, max = 1, message = "M=Male, F=Female必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String gender;
    /**
     * 生日，yyyy-MM-dd
     */
    @ApiModelProperty("生日，yyyy-MM-dd")
    @Length(min = 0, max = 0, message = "生日，yyyy-MM-dd必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String birthday;
    /**
     * 家庭电话
     */
    @ApiModelProperty("家庭电话")
    @Length(min = 0, max = 20, message = "家庭电话必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String phone;
    /**
     * 传真，暂时没用
     */
    @ApiModelProperty("传真，暂时没用")
    @Length(min = 0, max = 20, message = "传真，暂时没用必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String fax;
    /**
     * 暂时没用
     */
    @ApiModelProperty("暂时没用")
    @Length(min = 0, max = 20, message = "暂时没用必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String otherMobile;
    /**
     * 公司电话
     */
    @ApiModelProperty("公司电话")
    @Length(min = 0, max = 20, message = "公司电话必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String otherPhone;
    /**
     * 联系人，暂时没用
     */
    @ApiModelProperty("联系人，暂时没用")
    @Length(min = 0, max = 100, message = "联系人，暂时没用必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String contactWith;
    /**
     * 微信昵称（小程序/H5用）
     */
    @ApiModelProperty("微信昵称（小程序/H5用）")
    @Length(min = 0, max = 100, message = "微信昵称（小程序/H5用）必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String nickName;
    /**
     * 国籍，global_db.iso_country.country_code_2
     */
    @ApiModelProperty("国籍，global_db.iso_country.country_code_2")
    @Length(min = 0, max = 3, message = "国籍，global_db.iso_country.country_code_2必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String nationalityCode;
    /**
     * 国家，global_db.iso_country.country_code_2
     */
    @ApiModelProperty("国家，global_db.iso_country.country_code_2")
    @Length(min = 0, max = 255, message = "国家，global_db.iso_country.country_code_2必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String countryAreaCode;
    /**
     * 省
     */
    @ApiModelProperty("省")
    @Length(min = 0, max = 100, message = "省必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String province;
    /**
     * 城市
     */
    @ApiModelProperty("城市")
    @Length(min = 0, max = 100, message = "城市必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String city;
    /**
     * 没用了
     */
    @ApiModelProperty("没用了")
    @Min(value = 20, message = "没用了不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long areaId;
    /**
     * 区
     */
    @ApiModelProperty("区")
    @Length(min = 0, max = 100, message = "区必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String district;
    /**
     * 地址一
     */
    @ApiModelProperty("地址一")
    @Length(min = 0, max = 500, message = "地址一必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String address;
    /**
     * 地址二
     */
    @ApiModelProperty("地址二")
    @Length(min = 0, max = 500, message = "地址二必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String address2;
    /**
     * 是否启用积分，默认是，0=否 1=是
     */
    @ApiModelProperty("是否启用积分，默认是，0=否 1=是")
    private Boolean pointEnabled;
    /**
     * 是否启用储值，默认是，0=否 1=是
     */
    @ApiModelProperty("是否启用储值，默认是，0=否 1=是")
    private Boolean valueEnabled;
    /**
     * 会员状态，1=在用 3=在用 4=停用 5=过期 6=删除 7=挂失 8=注销
     */
    @ApiModelProperty("会员状态，1=在用 3=在用 4=停用 5=过期 6=删除 7=挂失 8=注销")
    @Min(value = 20, message = "会员状态，1=在用 3=在用 4=停用 5=过期 6=删除 7=挂失 8=注销不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long memberStatusId;
    /**
     * 称谓，company_db.company_dictionary.JOB_TITLE
     */
    @ApiModelProperty("称谓，company_db.company_dictionary.JOB_TITLE")
    @Length(min = 0, max = 50, message = "称谓，company_db.company_dictionary.JOB_TITLE必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String jobTitle;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @Length(min = 0, max = 1000, message = "备注必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String remarks;
    /**
     * 注册时间
     */
    @ApiModelProperty("注册时间")
    private Instant registrationTime;
    /**
     * 邮编
     */
    @ApiModelProperty("邮编")
    @Length(min = 0, max = 255, message = "邮编必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String zipCode;
    /**
     * 失效时间，暂时没用
     */
    @ApiModelProperty("失效时间，暂时没用")
    private Instant expiryTime;
    /**
     * 邮箱是否激活，废弃
     */
    @ApiModelProperty("邮箱是否激活，废弃")
    private Boolean emailActivated;
    /**
     * 加密后的密码，用于会员登录
     */
    @ApiModelProperty("加密后的密码，用于会员登录")
    @Length(min = 0, max = 50, message = "加密后的密码，用于会员登录必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String passwordHash;
    /**
     * 加密密码的盐
     */
    @ApiModelProperty("加密密码的盐")
    @Length(min = 0, max = 10, message = "加密密码的盐必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String passwordSalt;
    /**
     * 累计获得积分
     */
    @ApiModelProperty("累计获得积分")
    @Length(min = 0, max = 0, message = "累计获得积分必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String creditPoint;
    /**
     * 累计消费积分
     */
    @ApiModelProperty("累计消费积分")
    @Length(min = 0, max = 0, message = "累计消费积分必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String chargePoint;
    /**
     * 冻结积分，暂时没用
     */
    @ApiModelProperty("冻结积分，暂时没用")
    @Length(min = 0, max = 0, message = "冻结积分，暂时没用必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String frozenPoint;
    /**
     * 即将失效积分
     */
    @ApiModelProperty("即将失效积分")
    @Length(min = 0, max = 0, message = "即将失效积分必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String expiringPoint;
    /**
     * 已失效积分呢
     */
    @ApiModelProperty("已失效积分呢")
    @Length(min = 0, max = 0, message = "已失效积分呢必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String expiredPoint;
    /**
     * 累计储值
     */
    @ApiModelProperty("累计储值")
    @Length(min = 0, max = 0, message = "累计储值必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String creditValue;
    /**
     * 累计消费储值
     */
    @ApiModelProperty("累计消费储值")
    @Length(min = 0, max = 0, message = "累计消费储值必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String chargeValue;
    /**
     * 储值余额
     */
    @ApiModelProperty("储值余额")
    @Length(min = 0, max = 0, message = "储值余额必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String value;
    /**
     * 累计冻结余额
     */
    @ApiModelProperty("累计冻结余额")
    @Length(min = 0, max = 0, message = "累计冻结余额必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String frozenValue;
    /**
     * 可用赠送储值
     */
    @ApiModelProperty("可用赠送储值")
    @Length(min = 0, max = 0, message = "可用赠送储值必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String givenValue;
    /**
     * 累计赠送储值
     */
    @ApiModelProperty("累计赠送储值")
    @Length(min = 0, max = 0, message = "累计赠送储值必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String creditGivenValue;
    /**
     * 累计消费赠送储值
     */
    @ApiModelProperty("累计消费赠送储值")
    @Length(min = 0, max = 0, message = "累计消费赠送储值必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String chargeGivenValue;
    /**
     * 注册渠道ID,global_db.channel.id
     */
    @ApiModelProperty("注册渠道ID,global_db.channel.id")
    @Min(value = 20, message = "注册渠道ID,global_db.channel.id不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long channelId;
    /**
     * 渠道信息，暂时没用
     */
    @ApiModelProperty("渠道信息，暂时没用")
    @Length(min = 0, max = 20, message = "渠道信息，暂时没用必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String channelInfo;
    /**
     * 注册来源ID
     */
    @ApiModelProperty("注册来源ID")
    @Min(value = 20, message = "注册来源ID不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long sourceId;
    /**
     * 注册来源Code
     */
    @ApiModelProperty("注册来源Code")
    @Length(min = 0, max = 64, message = "注册来源Code必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String sourceCode;
    /**
     * 注册来源名称
     */
    @ApiModelProperty("注册来源名称")
    @Length(min = 0, max = 255, message = "注册来源名称必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String sourceName;
    /**
     * 销售人编号
     */
    @ApiModelProperty("销售人编号")
    @Length(min = 0, max = 50, message = "销售人编号必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String salesNo;
    /**
     * 销售人姓名
     */
    @ApiModelProperty("销售人姓名")
    @Length(min = 0, max = 50, message = "销售人姓名必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String salesName;
    /**
     * 暂时没用，用于接收邮件通知1接收0拒绝 空未设置
     */
    @ApiModelProperty("暂时没用，用于接收邮件通知1接收0拒绝 空未设置")
    @Max(value = 4, message = "暂时没用，用于接收邮件通知1接收0拒绝 空未设置不能超过{value}个字符", groups = {Create.class, Update.class})
    private Integer emailNotificationFlag;
    /**
     * 激活时间，暂时没用
     */
    @ApiModelProperty("激活时间，暂时没用")
    private Instant activationTime;
    /**
     * 注册酒店ID
     */
    @ApiModelProperty("注册酒店ID")
    @Min(value = 20, message = "注册酒店ID不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long storeId;
    /**
     * 注册费用，没用
     */
    @ApiModelProperty("注册费用，没用")
    @Length(min = 0, max = 0, message = "注册费用，没用必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String registrationFee;
    /**
     * 注册时会员等级ID，company_db.member_level.id
     */
    @ApiModelProperty("注册时会员等级ID，company_db.member_level.id")
    @Min(value = 20, message = "注册时会员等级ID，company_db.member_level.id不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long initialMemberLevelId;
    /**
     * 注册来源，company_db.member_vcard_prefix.id
     */
    @ApiModelProperty("注册来源，company_db.member_vcard_prefix.id")
    @Min(value = 20, message = "注册来源，company_db.member_vcard_prefix.id不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long memberVcardPrefixId;
    /**
     * unionId，微信小程序/H5授权获取
     */
    @ApiModelProperty("unionId，微信小程序/H5授权获取")
    @Length(min = 0, max = 64, message = "unionId，微信小程序/H5授权获取必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String unionId;
    /**
     * 第三方系统会员号，外部对接用
     */
    @ApiModelProperty("第三方系统会员号，外部对接用")
    @Length(min = 0, max = 255, message = "第三方系统会员号，外部对接用必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String memberNo;
    /**
     * 第三方系统卡号，外部对接用
     */
    @ApiModelProperty("第三方系统卡号，外部对接用")
    @Length(min = 0, max = 20, message = "第三方系统卡号，外部对接用必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String cardNo;
    /**
     * 暂时没用，语言code
     */
    @ApiModelProperty("暂时没用，语言code")
    @Length(min = 0, max = 20, message = "暂时没用，语言code必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String languageCode;
    /**
     * 暂时没用
     */
    @ApiModelProperty("暂时没用")
    @Length(min = 0, max = 10, message = "暂时没用必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String isNewGuest;
    /**
     * 暂时没用
     */
    @ApiModelProperty("暂时没用")
    @Max(value = 11, message = "暂时没用不能超过{value}个字符", groups = {Create.class, Update.class})
    private Integer experienceValue;
    /**
     * 头像地址，微信小程序/H5授权获取
     */
    @ApiModelProperty("头像地址，微信小程序/H5授权获取")
    @Length(min = 0, max = 255, message = "头像地址，微信小程序/H5授权获取必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String headImageUrl;
    /**
     * H =家 C=公司
     */
    @ApiModelProperty("H =家 C=公司 ")
    @Length(min = 0, max = 1, message = "H =家 C=公司 必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String addressType;
    /**
     * 是否需要授权
     */
    @ApiModelProperty("是否需要授权")
    private Boolean needAuthorized;
    /**
     * 已废弃，即将生日标记:做任务更新
     */
    @ApiModelProperty("已废弃，即将生日标记:做任务更新")
    private Boolean birthdayNotificationFlag;
    /**
     * 公司电话区号
     */
    @ApiModelProperty("公司电话区号")
    @Length(min = 0, max = 10, message = "公司电话区号必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String otherPhoneCode;
    /**
     * 家庭电话区号
     */
    @ApiModelProperty("家庭电话区号")
    @Length(min = 0, max = 10, message = "家庭电话区号必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String phoneCode;
    /**
     * 已废弃，S=单身, M=已婚, D=离异
     */
    @ApiModelProperty("已废弃，S=单身, M=已婚, D=离异")
    @Length(min = 0, max = 1, message = "已废弃，S=单身, M=已婚, D=离异必须在{min}和{max}个字符之间", groups = {Create.class, Update.class})
    private String maritalStatus;
    /**
     * 已废弃
     */
    @ApiModelProperty("已废弃")
    @Min(value = 20, message = "已废弃不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long educationDegreeId;
    /**
     * 已废弃
     */
    @ApiModelProperty("已废弃")
    @Min(value = 20, message = "已废弃不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long monthlyIncomeId;
    /**
     * 已废弃
     */
    @ApiModelProperty("已废弃")
    @Min(value = 20, message = "已废弃不能小于{value}个字符", groups = {Create.class, Update.class})
    private Long industryId;

    @ApiModelProperty("消息类型")
    private String msgType;

    @ApiModelProperty("消息id")
    private String msgId;
}
