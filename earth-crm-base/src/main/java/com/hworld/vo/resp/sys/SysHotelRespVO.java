package com.hworld.vo.resp.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Create;
import com.hworld.annotation.Delelt;
import com.hworld.annotation.SelectOne;
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
import java.time.LocalDateTime;

/**
 * 酒店管理维护DTO
 *
 * @author caoyang
 * @date 2022-02-08 16:00:53
 */
@ApiModel("酒店管理维护")
@Getter
@Setter
@ToString
public class SysHotelRespVO extends BaseRespVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 父节点ID
     */
    @ApiModelProperty("父节点ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 酒店编号
     */
    @ApiModelProperty("酒店编号")
    private String hotelCode;
    /**
     * 外部酒店编码
     */
    @ApiModelProperty("外部酒店编码")
    private String outerHotelNo;
    /**
     * 酒店名称
     */
    @ApiModelProperty("酒店名称")
    private String hotelName;
    /**
     * 酒店简称
     */
    @ApiModelProperty("酒店简称")
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
    private String hotelType;
    /**
     * 酒店状态
     */
    @ApiModelProperty("酒店状态")
    private String hotelStatus;
    /**
     * 邮编
     */
    @ApiModelProperty("邮编")
    private String zipCode;
    /**
     * 国家编号
     */
    @ApiModelProperty("国家编号")
    private String nationNo;
    /**
     * 国家名称
     */
    @ApiModelProperty("国家名称")
    private String nationName;
    /**
     * 省份编号（州编码）
     */
    @ApiModelProperty("省份编号（州编码）")
    private String provinceNo;
    /**
     * 省份名称（州名称）
     */
    @ApiModelProperty("省份名称（州名称）")
    private String provinceName;
    /**
     * 城市编号（国际城市编号)
     */
    @ApiModelProperty("城市编号（国际城市编号)")
    private String cityNo;
    /**
     * 城市名称
     */
    @ApiModelProperty("城市名称")
    private String cityName;
    /**
     * 大区编号
     */
    @ApiModelProperty("大区编号")
    private String region;
    /**
     * 区县编号
     */
    @ApiModelProperty("区县编号")
    private String countyNo;
    /**
     * 酒店地址提示
     */
    @ApiModelProperty("酒店地址提示")
    private String hotelAddressTip;
    /**
     * 路线提示
     */
    @ApiModelProperty("路线提示")
    private String routeTips;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;
    /**
     * 传真
     */
    @ApiModelProperty("传真")
    private String fax;
    /**
     * 币种编码
     */
    @ApiModelProperty("币种编码")
    private String currencyCode;
    /**
     * 币种名称
     */
    @ApiModelProperty("币种名称")
    private String currencyName;
    /**
     * 数据来源
     */
    @ApiModelProperty("数据来源")
    private String source;
    /**
     * 数据供应商
     */
    @ApiModelProperty("数据供应商")
    private String supplierType;
    /**
     * 华住会展示
     */
    @ApiModelProperty("华住会展示")
    private String appDisplay;
    /**
     * 开户银行
     */
    @ApiModelProperty("开户银行")
    private String depositBank;
    /**
     * 银行地址
     */
    @ApiModelProperty("银行地址")
    private String bankAddress;
    /**
     * 银行账号
     */
    @ApiModelProperty("银行账号")
    private String bankAccount;
    /**
     * 银行电话
     */
    @ApiModelProperty("银行电话")
    private String bankPhoneNumber;
    /**
     * 开票抬头
     */
    @ApiModelProperty("开票抬头")
    private String invoiceTitle;
    /**
     * 事业部编号
     */
    @ApiModelProperty("事业部编号")
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
    private String locationType;
    /**
     * 维度
     */
    @ApiModelProperty("维度")
    private String latitude;
    /**
     * 管理模式
     */
    @ApiModelProperty("管理模式")
    private String managementMode;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobilePhone;
    /**
     * 电话区号
     */
    @ApiModelProperty("电话区号")
    private String phoneCode;
    /**
     * 法定名称
     */
    @ApiModelProperty("法定名称")
    private String legalName;
    /**
     * 管理公司名称
     */
    @ApiModelProperty("管理公司名称")
    private String managementCompanyName;
    /**
     * 统一社会信用代码
     */
    @ApiModelProperty("统一社会信用代码")
    private String uniformSocialCreditCode;
    /**
     * 纳税人识别号
     */
    @ApiModelProperty("纳税人识别号")
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
    private String telephone;
    /**
     * 时区
     */
    @ApiModelProperty("时区")
    private String timeZone;
    /**
     * 网页
     */
    @ApiModelProperty("网页")
    private String url;
    /**
     * 酒店描述
     */
    @ApiModelProperty("酒店描述")
    private String describe;
    /**
     * 总经理姓名
     */
    @ApiModelProperty("总经理姓名")
    private String managerName;
    /**
     * 总经理邮箱
     */
    @ApiModelProperty("总经理邮箱 ")
    private String managerMail;
    /**
     * 预定邮箱
     */
    @ApiModelProperty("预定邮箱")
    private String reservationEmail;
    /**
     * 预定电话国家号
     */
    @ApiModelProperty("预定电话国家号")
    private String reservationPhoneCountryCode;
    /**
     * 预定电话号码
     */
    @ApiModelProperty("预定电话号码")
    private String reservationPhone;
    /**
     * 预订部经理姓名
     */
    @ApiModelProperty("预订部经理姓名")
    private String reservationManagerName;
    /**
     * 预订部经理邮箱
     */
    @ApiModelProperty("预订部经理邮箱")
    private String reservationManagerMail;
    /**
     * 商业登记号
     */
    @ApiModelProperty("商业登记号")
    private String commercialRegistrationId;
    /**
     * 商业登记所在国家编号
     */
    @ApiModelProperty("商业登记所在国家编号")
    private String commercialRegistrationCountryCode;
    /**
     * 商业登记所在国家名称
     */
    @ApiModelProperty("商业登记所在国家名称")
    private String commercialRegistrationCountryName;
    /**
     * 商业登记所在州编号
     */
    @ApiModelProperty("商业登记所在州编号")
    private String commercialRegistrationStateCode;
    /**
     * 商业登记所在州名称
     */
    @ApiModelProperty("商业登记所在州名称")
    private String commercialRegistrationStateName;
    /**
     * 商业登记所在城市编号
     */
    @ApiModelProperty("商业登记所在城市编号")
    private String commercialRegistrationCityCode;
    /**
     * 商业登记所在城市名称
     */
    @ApiModelProperty("商业登记所在城市名称")
    private String commercialRegistrationCityName;
    /**
     * 商业登记所在街道与门牌号
     */
    @ApiModelProperty("商业登记所在街道与门牌号")
    private String commercialRegistrationAddr;
    /**
     * Swift / BIC
     */
    @ApiModelProperty("Swift / BIC ")
    private String swiftOrBIC;
    /**
     * 监事会成员
     */
    @ApiModelProperty("监事会成员")
    private String supervisoryBoard;
    /**
     * 董事总经理/董事会成员
     */
    @ApiModelProperty("董事总经理/董事会成员")
    private String managementDirector;
    /**
     * 酒店产权人姓名
     */
    @ApiModelProperty("酒店产权人姓名")
    private String ownerName;
}
