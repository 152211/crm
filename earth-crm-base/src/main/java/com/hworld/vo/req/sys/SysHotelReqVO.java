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

import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * 酒店集团维护DTO
 *
 * @author caoyang
 * @date 2022-07-21 16:43:04
 */
@ApiModel("酒店集团维护")
@Getter
@Setter
@ToString
public class SysHotelReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class, SelectOne.class})
    private Long id;
    /**
     * 父节点ID
     */
    @ApiModelProperty("父节点ID")
    @NotNull(message = "parentId Can not be empty", groups = {Create.class, Update.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 酒店编号
     */
    @ApiModelProperty("酒店编号")
    @Length(min = 0, max = 50, message = "hotelCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "hotelCode Can not be empty", groups = {Create.class})
    private String hotelCode;
    /**
     * 外部酒店编码
     */
    @ApiModelProperty("外部酒店编码")
    @Length(min = 0, max = 50, message = "outerHotelNo length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String outerHotelNo;
    /**
     * 酒店名称
     */
    @ApiModelProperty("酒店名称")
    @Length(min = 0, max = 200, message = "hotelName length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "hotelName Can not be empty", groups = {Create.class})
    private String hotelName;
    /**
     * 酒店简称
     */
    @ApiModelProperty("酒店简称")
    @Length(min = 0, max = 255, message = "hotelShortName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String hotelShortName;
    /**
     * 酒店层级 1:集团 2:酒店品牌 3:酒店门店
     */
    @ApiModelProperty("酒店层级 1:集团 2:酒店品牌 3:酒店门店")
    private Integer hotelLevel;
    /**
     * 酒店路径
     */
    @ApiModelProperty("酒店路径")
    private String hotelPath;
    /**
     * R-根节点,G-总部部门,A/B-品牌,S-门店
     */
    @ApiModelProperty("R-根节点,G-总部部门,A/B-品牌,S-门店")
    @Length(min = 0, max = 1, message = "hotelType length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "hotelType Can not be empty", groups = {Create.class})
    private String hotelType;
    /**
     * 酒店状态
     */
    @ApiModelProperty("酒店状态")
    @Length(min = 0, max = 200, message = "hotelStatus length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String hotelStatus;
    /**
     * 邮编
     */
    @ApiModelProperty("邮编")
    @Length(min = 0, max = 50, message = "zipCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String zipCode;
    /**
     * 国家编号
     */
    @ApiModelProperty("国家编号")
    @Length(min = 0, max = 16, message = "nationNo length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String nationNo;
    /**
     * 国家名称
     */
    @ApiModelProperty("国家名称")
    @Length(min = 0, max = 255, message = "nationName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String nationName;
    /**
     * 省份编号（州编码）
     */
    @ApiModelProperty("省份编号（州编码）")
    @Length(min = 0, max = 16, message = "provinceNo length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String provinceNo;
    /**
     * 省份名称（州名称）
     */
    @ApiModelProperty("省份名称（州名称）")
    @Length(min = 0, max = 255, message = "provinceName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String provinceName;
    /**
     * 城市编号（国际城市编号)
     */
    @ApiModelProperty("城市编号（国际城市编号)")
    @Length(min = 0, max = 16, message = "cityNo length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String cityNo;
    /**
     * 城市名称
     */
    @ApiModelProperty("城市名称")
    @Length(min = 0, max = 255, message = "cityName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String cityName;
    /**
     * 大区编号
     */
    @ApiModelProperty("大区编号")
    @Length(min = 0, max = 16, message = "region length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String region;
    /**
     * 区县编号
     */
    @ApiModelProperty("区县编号")
    @Length(min = 0, max = 16, message = "countyNo length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String countyNo;
    /**
     * 酒店地址提示
     */
    @ApiModelProperty("酒店地址提示")
    @Length(min = 0, max = 500, message = "hotelAddressTip length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String hotelAddressTip;
    /**
     * 路线提示
     */
    @ApiModelProperty("路线提示")
    @Length(min = 0, max = 255, message = "routeTips length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String routeTips;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @Length(min = 0, max = 255, message = "email length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String email;
    /**
     * 传真
     */
    @ApiModelProperty("传真")
    @Length(min = 0, max = 50, message = "fax length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String fax;
    /**
     * 币种编码
     */
    @ApiModelProperty("币种编码")
    @Length(min = 0, max = 20, message = "currencyCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String currencyCode;
    /**
     * 币种名称
     */
    @ApiModelProperty("币种名称")
    @Length(min = 0, max = 255, message = "currencyName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String currencyName;
    /**
     * 数据来源
     */
    @ApiModelProperty("数据来源")
    @Length(min = 0, max = 20, message = "source length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String source;
    /**
     * 数据供应商
     */
    @ApiModelProperty("数据供应商")
    @Length(min = 0, max = 20, message = "supplierType length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String supplierType;
    /**
     * 华住会展示
     */
    @ApiModelProperty("华住会展示")
    @Length(min = 0, max = 20, message = "appDisplay length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String appDisplay;
    /**
     * 开户银行
     */
    @ApiModelProperty("开户银行")
    @Length(min = 0, max = 50, message = "depositBank length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String depositBank;
    /**
     * 银行地址
     */
    @ApiModelProperty("银行地址")
    @Length(min = 0, max = 255, message = "bankAddress length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String bankAddress;
    /**
     * 银行账号
     */
    @ApiModelProperty("银行账号")
    @Length(min = 0, max = 50, message = "bankAccount length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String bankAccount;
    /**
     * 银行电话
     */
    @ApiModelProperty("银行电话")
    @Length(min = 0, max = 30, message = "bankPhoneNumber length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String bankPhoneNumber;
    /**
     * 开票抬头
     */
    @ApiModelProperty("开票抬头")
    @Length(min = 0, max = 50, message = "invoiceTitle length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String invoiceTitle;
    /**
     * 事业部编号
     */
    @ApiModelProperty("事业部编号")
    @Length(min = 0, max = 16, message = "division length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String division;
    /**
     * 干净等级
     */
    @ApiModelProperty("干净等级")
    private Integer cleanLevel;
    /**
     * 产品版本编码
     */
    @ApiModelProperty("产品版本编码")
    @Length(min = 0, max = 50, message = "constructLevelNo length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String constructLevelNo;
    /**
     * 经营模式
     */
    @ApiModelProperty("经营模式")
    private Integer operateModeId;
    /**
     * 对客经营范围
     */
    @ApiModelProperty("对客经营范围")
    private Integer operateScopeId;
    /**
     * 建造年月
     */
    @ApiModelProperty("建造年月")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime whenBuilt;
    /**
     * 首次开业日期
     */
    @ApiModelProperty("首次开业日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime firstOpeningDate;
    /**
     * 试营业日期
     */
    @ApiModelProperty("试营业日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime trialOpeningDate;
    /**
     * 正式营业日期
     */
    @ApiModelProperty("正式营业日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime openingDate;
    /**
     * 恢复营业日期
     */
    @ApiModelProperty("恢复营业日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime reopeningDate;
    /**
     * 大修日期
     */
    @ApiModelProperty("大修日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime overhaulTime;
    /**
     * 停业日期
     */
    @ApiModelProperty("停业日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime closureDate;
    /**
     * 经纬度类型
     */
    @ApiModelProperty("经纬度类型")
    @Length(min = 0, max = 10, message = "locationType length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String locationType;
    /**
     * 维度
     */
    @ApiModelProperty("维度")
    @Length(min = 0, max = 50, message = "latitude length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String latitude;
    /**
     * 管理模式
     */
    @ApiModelProperty("管理模式")
    @Length(min = 0, max = 16, message = "managementMode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String managementMode;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @Length(min = 0, max = 30, message = "mobilePhone length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String mobilePhone;
    /**
     * 电话区号
     */
    @ApiModelProperty("电话区号")
    @Length(min = 0, max = 255, message = "phoneCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String phoneCode;
    /**
     * 法定名称
     */
    @ApiModelProperty("法定名称")
    @Length(min = 0, max = 255, message = "legalName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String legalName;
    /**
     * 管理公司名称
     */
    @ApiModelProperty("管理公司名称")
    @Length(min = 0, max = 255, message = "managementCompanyName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String managementCompanyName;
    /**
     * 统一社会信用代码
     */
    @ApiModelProperty("统一社会信用代码")
    @Length(min = 0, max = 50, message = "uniformSocialCreditCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String uniformSocialCreditCode;
    /**
     * 纳税人识别号
     */
    @ApiModelProperty("纳税人识别号")
    @Length(min = 0, max = 50, message = "taxpayerIdentityNumber length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String taxpayerIdentityNumber;
    /**
     * 纳税人规模
     */
    @ApiModelProperty("纳税人规模")
    private Integer taxpayerScale;
    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    @Length(min = 0, max = 200, message = "telephone length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String telephone;
    /**
     * 时区
     */
    @ApiModelProperty("时区")
    @Length(min = 0, max = 20, message = "timeZone length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String timeZone;
    /**
     * 网页
     */
    @ApiModelProperty("网页")
    @Length(min = 0, max = 255, message = "url length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String url;
    /**
     * 酒店描述
     */
    @ApiModelProperty("酒店描述")
    @Length(min = 0, max = 255, message = "describe length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String describe;
    /**
     * 总经理姓名
     */
    @ApiModelProperty("总经理姓名")
    @Length(min = 0, max = 255, message = "managerName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String managerName;
    /**
     * 总经理邮箱
     */
    @ApiModelProperty("总经理邮箱 ")
    @Length(min = 0, max = 255, message = "managerMail  length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String managerMail;
    /**
     * 预定邮箱
     */
    @ApiModelProperty("预定邮箱")
    @Length(min = 0, max = 255, message = "reservationEmail length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String reservationEmail;
    /**
     * 预定电话国家号
     */
    @ApiModelProperty("预定电话国家号")
    @Length(min = 0, max = 255, message = "reservationPhoneCountryCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String reservationPhoneCountryCode;
    /**
     * 预定电话号码
     */
    @ApiModelProperty("预定电话号码")
    @Length(min = 0, max = 255, message = "reservationPhone length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String reservationPhone;
    /**
     * 预订部经理姓名
     */
    @ApiModelProperty("预订部经理姓名")
    @Length(min = 0, max = 255, message = "reservationManagerName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String reservationManagerName;
    /**
     * 预订部经理邮箱
     */
    @ApiModelProperty("预订部经理邮箱")
    @Length(min = 0, max = 255, message = "reservationManagerMail length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String reservationManagerMail;
    /**
     * 商业登记号
     */
    @ApiModelProperty("商业登记号")
    @Length(min = 0, max = 100, message = "commercialRegistrationId length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String commercialRegistrationId;
    /**
     * 商业登记所在国家编号
     */
    @ApiModelProperty("商业登记所在国家编号")
    @Length(min = 0, max = 16, message = "commercialRegistrationCountryCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String commercialRegistrationCountryCode;
    /**
     * 商业登记所在国家名称
     */
    @ApiModelProperty("商业登记所在国家名称")
    @Length(min = 0, max = 255, message = "commercialRegistrationCountryName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String commercialRegistrationCountryName;
    /**
     * 商业登记所在州编号
     */
    @ApiModelProperty("商业登记所在州编号")
    @Length(min = 0, max = 16, message = "commercialRegistrationStateCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String commercialRegistrationStateCode;
    /**
     * 商业登记所在州名称
     */
    @ApiModelProperty("商业登记所在州名称")
    @Length(min = 0, max = 255, message = "commercialRegistrationStateName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String commercialRegistrationStateName;
    /**
     * 商业登记所在城市编号
     */
    @ApiModelProperty("商业登记所在城市编号")
    @Length(min = 0, max = 16, message = "commercialRegistrationCityCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String commercialRegistrationCityCode;
    /**
     * 商业登记所在城市名称
     */
    @ApiModelProperty("商业登记所在城市名称")
    @Length(min = 0, max = 255, message = "commercialRegistrationCityName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String commercialRegistrationCityName;
    /**
     * 商业登记所在街道与门牌号
     */
    @ApiModelProperty("商业登记所在街道与门牌号")
    @Length(min = 0, max = 255, message = "commercialRegistrationAddr length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String commercialRegistrationAddr;
    /**
     * Swift / BIC
     */
    @ApiModelProperty("Swift / BIC ")
    @Length(min = 0, max = 50, message = "Swift / BIC  length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String swiftOrBIC;
    /**
     * 监事会成员
     */
    @ApiModelProperty("监事会成员")
    @Length(min = 0, max = 255, message = "supervisoryBoard length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String supervisoryBoard;
    /**
     * 董事总经理/董事会成员
     */
    @ApiModelProperty("董事总经理/董事会成员")
    @Length(min = 0, max = 255, message = "managementDirector length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String managementDirector;
    /**
     * 酒店产权人姓名
     */
    @ApiModelProperty("酒店产权人姓名")
    @Length(min = 0, max = 255, message = "ownerName length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String ownerName;
}
