package com.hworld.vo.req.hdata.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 酒店基本信息
 */
@ApiModel("酒店基本信息")
@Getter
@Setter
@ToString
public class HotelBasicInfoVO implements Serializable {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("华住会展示")
    private String appDisplay;

    @ApiModelProperty("银行地址")
    private String bankAddress;

    @ApiModelProperty("银行账号")
    private String bankAccount;

    @ApiModelProperty("电话")
    private String bankPhoneNumber;

    @ApiModelProperty("品牌编号")
    private String brandNo;

    @ApiModelProperty("经营模式")
    private Integer operateModeId;

    @ApiModelProperty("对客经营范围")
    private Integer operateScopeId;

    @ApiModelProperty("事业部编号")
    private String division;

    @ApiModelProperty("城市编号（国际城市编号）")
    private String cityNo;

    @ApiModelProperty("干净等级")
    private Integer cleanLevel;

    @ApiModelProperty("停业日期")
    private Date closureDate;

    @ApiModelProperty("产品版本编码")
    private String constructLevelNo;

    @ApiModelProperty("国家编号")
    private String nationNo;

    @ApiModelProperty("区县编号")
    private String countyNo;

    @ApiModelProperty("币种编码")
    private String currencyCode;

    @ApiModelProperty("数据来源")
    private String source;

    @ApiModelProperty("数据供应商")
    private String supplierType;

    @ApiModelProperty("开户银行")
    private String depositBank;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("是否停用ETT")
    private String isSupportETT;

    @ApiModelProperty("加盟合同类型")
    private String franchiseContractType;

    @ApiModelProperty("传真")
    private String fax;

    @ApiModelProperty("首次开业日期")
    private Date firstOpeningDate;

    @ApiModelProperty("酒店地址提示")
    private String hotelAddressTip;

    @ApiModelProperty("酒店编号")
    private String hotelNo;

    @ApiModelProperty("大区编号")
    private String region;

    @ApiModelProperty("酒店简称")
    private String hotelShortName;

    @ApiModelProperty("酒店状态")
    private String hotelStatus;

    @ApiModelProperty("中枢品牌编号")
    private Integer hotelStyle;

    @ApiModelProperty("分区编号")
    private String partition;

    @ApiModelProperty("省份编号（州编码）")
    private String provinceNo;

    @ApiModelProperty("开票抬头")
    private String invoiceTitle;

    @ApiModelProperty("发票类型")
    private Integer invoiceType;

    @ApiModelProperty("是否启用自营餐厅")
    private Boolean isEnableRestaurant;

    @ApiModelProperty("是否国际")
    private Boolean isInternational;

    @ApiModelProperty("是否在web开放")
    private Integer isOpenInWeb;

    @ApiModelProperty("是否同步TARS主数据")
    private Boolean isSyncTars;

    @ApiModelProperty("经纬度类型")
    private String locationType;

    @ApiModelProperty("经度")
    private String longitude;

    @ApiModelProperty("维度")
    private String latitude;

    @ApiModelProperty("法定名称")
    private String legalName;

    @ApiModelProperty("管理公司名称")
    private String managementCompanyName;

    @ApiModelProperty("管理模式")
    private String managementMode;

    @ApiModelProperty("手机号")
    private String mobilePhone;

    @ApiModelProperty("正式营业日期")
    private Date openingDate;

    @ApiModelProperty("是否开通在线预授权")
    private Integer isOpenOnlinePreAuth;

    @ApiModelProperty("外部酒店编码")
    private String outerHotelNo;

    @ApiModelProperty("大修日期")
    private Date overhaulTime;

    @ApiModelProperty("电话区号")
    private String phoneCode;

    @ApiModelProperty("拼音")
    private String spellShortName;

    @ApiModelProperty("PMS系统类型")
    private String pmsType;

    @ApiModelProperty("恢复营业日期")
    private Date reopeningDate;

    @ApiModelProperty("路线提示")
    private String routeTips;

    @ApiModelProperty("纳税人识别号")
    private String taxpayerIdentityNumber;

    @ApiModelProperty("纳税人规模")
    private Integer taxpayerScale;

    @ApiModelProperty("电话号码")
    private String telephone;

    @ApiModelProperty("时区")
    private String timeZone;

    @ApiModelProperty("试营业日期")
    private Date trialOpeningDate;

    @ApiModelProperty("统一社会信用代码")
    private String uniformSocialCreditCode;

    @ApiModelProperty("网页")
    private String url;

    @ApiModelProperty("建造年月")
    private String whenBuilt;

    @ApiModelProperty("邮编")
    private String zipCode;

    @ApiModelProperty("是否支持储值卡支付")
    private Boolean isSupportValueCardPay;

    @ApiModelProperty("修改时间")
    private Date modifyTime;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("是否删除")
    private Boolean isDelete;

    @ApiModelProperty("多语言集合")
    private List<HotelBasicInfoLanguageVO> languageList;

    @ApiModelProperty("酒店联系信息")
    private HotelBasicInfoContactVO hotelBasicInfoContactVo;

    @ApiModelProperty("酒店联系信息")
    private HotelBasicInfoExtendVO hotelBasicInfoExtendVo;
}
