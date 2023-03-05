package com.hworld.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hworld.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 酒店集团维护Entity
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-07-21 16:43:04
 **/
@TableName("sys_hotel")
@Getter
@Setter
@ToString
public class SysHotelDO extends BaseDO {


   /**
    *  主键
    */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

   /**
    *  父节点ID
    */
    @TableField("parent_id")
    private Long parentId;

   /**
    *  酒店编号
    */
    @TableField("hotel_code")
    private String hotelCode;

   /**
    *  外部酒店编码
    */
    @TableField("outer_hotel_no")
    private String outerHotelNo;

   /**
    *  酒店名称
    */
    @TableField("hotel_name")
    private String hotelName;

   /**
    *  酒店简称
    */
    @TableField("hotel_short_name")
    private String hotelShortName;

   /**
    *  酒店层级 1:集团 2:酒店品牌 3:酒店门店
    */
    @TableField("hotel_level")
    private Integer hotelLevel;

   /**
    *  酒店路径
    */
    @TableField("hotel_path")
    private String hotelPath;

   /**
    *  R-根节点,G-总部部门,A/B-品牌,S-门店
    */
    @TableField("hotel_type")
    private String hotelType;

   /**
    *  酒店状态
    */
    @TableField("hotel_status")
    private String hotelStatus;

   /**
    *  邮编
    */
    @TableField("zip_code")
    private String zipCode;

   /**
    *  国家编号
    */
    @TableField("nation_no")
    private String nationNo;

   /**
    *  国家名称
    */
    @TableField("nation_name")
    private String nationName;

   /**
    *  省份编号（州编码）
    */
    @TableField("province_no")
    private String provinceNo;

   /**
    *  省份名称（州名称）
    */
    @TableField("province_name")
    private String provinceName;

   /**
    *  城市编号（国际城市编号)
    */
    @TableField("city_no")
    private String cityNo;

   /**
    *  城市名称
    */
    @TableField("city_name")
    private String cityName;

   /**
    *  大区编号
    */
    @TableField("region")
    private String region;

   /**
    *  区县编号
    */
    @TableField("county_no")
    private String countyNo;

   /**
    *  酒店地址提示
    */
    @TableField("hotel_address_tip")
    private String hotelAddressTip;

   /**
    *  路线提示
    */
    @TableField("route_tips")
    private String routeTips;

   /**
    *  邮箱
    */
    @TableField("email")
    private String email;

   /**
    *  传真
    */
    @TableField("fax")
    private String fax;

   /**
    *  币种编码
    */
    @TableField("currency_code")
    private String currencyCode;

   /**
    *  币种名称
    */
    @TableField("currency_name")
    private String currencyName;

   /**
    *  数据来源
    */
    @TableField("source")
    private String source;

   /**
    *  数据供应商
    */
    @TableField("supplier_type")
    private String supplierType;

   /**
    *  华住会展示
    */
    @TableField("app_display")
    private String appDisplay;

   /**
    *  开户银行
    */
    @TableField("deposit_bank")
    private String depositBank;

   /**
    *  银行地址
    */
    @TableField("bank_address")
    private String bankAddress;

   /**
    *  银行账号
    */
    @TableField("bank_account")
    private String bankAccount;

   /**
    *  银行电话
    */
    @TableField("bank_phone_number")
    private String bankPhoneNumber;

   /**
    *  开票抬头
    */
    @TableField("invoice_title")
    private String invoiceTitle;

   /**
    *  事业部编号
    */
    @TableField("division")
    private String division;

   /**
    *  干净等级
    */
    @TableField("clean_level")
    private Integer cleanLevel;

   /**
    *  产品版本编码
    */
    @TableField("construct_level_no")
    private String constructLevelNo;

   /**
    *  经营模式
    */
    @TableField("operate_mode_id")
    private Integer operateModeId;

   /**
    *  对客经营范围
    */
    @TableField("operate_scope_id")
    private Integer operateScopeId;

   /**
    *  建造年月
    */
    @TableField("when_built")
    private LocalDateTime whenBuilt;

   /**
    *  首次开业日期
    */
    @TableField("first_opening_date")
    private LocalDateTime firstOpeningDate;

   /**
    *  试营业日期
    */
    @TableField("trial_opening_date")
    private LocalDateTime trialOpeningDate;

   /**
    *  正式营业日期
    */
    @TableField("opening_date")
    private LocalDateTime openingDate;

   /**
    *  恢复营业日期
    */
    @TableField("reopening_date")
    private LocalDateTime reopeningDate;

   /**
    *  大修日期
    */
    @TableField("overhaul_time")
    private LocalDateTime overhaulTime;

   /**
    *  停业日期
    */
    @TableField("closure_date")
    private LocalDateTime closureDate;

   /**
    *  经纬度类型
    */
    @TableField("location_type")
    private String locationType;

   /**
    *  维度
    */
    @TableField("latitude")
    private String latitude;

   /**
    *  管理模式
    */
    @TableField("management_mode")
    private String managementMode;

   /**
    *  手机号
    */
    @TableField("mobile_phone")
    private String mobilePhone;

   /**
    *  电话区号
    */
    @TableField("phone_code")
    private String phoneCode;

   /**
    *  法定名称
    */
    @TableField("legal_name")
    private String legalName;

   /**
    *  管理公司名称
    */
    @TableField("management_company_name")
    private String managementCompanyName;

   /**
    *  统一社会信用代码
    */
    @TableField("uniform_social_credit_code")
    private String uniformSocialCreditCode;

   /**
    *  纳税人识别号
    */
    @TableField("taxpayer_identity_number")
    private String taxpayerIdentityNumber;

   /**
    *  纳税人规模
    */
    @TableField("taxpayer_scale")
    private Integer taxpayerScale;

   /**
    *  电话号码
    */
    @TableField("telephone")
    private String telephone;

   /**
    *  时区
    */
    @TableField("time_zone")
    private String timeZone;

   /**
    *  网页
    */
    @TableField("url")
    private String url;

   /**
    *  酒店描述
    */
    @TableField("'describe'")
    private String describe;

   /**
    *  总经理姓名
    */
    @TableField("manager_name")
    private String managerName;

   /**
    *  总经理邮箱 
    */
    @TableField("manager_mail")
    private String managerMail;

   /**
    *  预定邮箱
    */
    @TableField("reservation_email")
    private String reservationEmail;

   /**
    *  预定电话国家号
    */
    @TableField("reservation_phone_country_code")
    private String reservationPhoneCountryCode;

   /**
    *  预定电话号码
    */
    @TableField("reservation_phone")
    private String reservationPhone;

   /**
    *  预订部经理姓名
    */
    @TableField("reservation_manager_name")
    private String reservationManagerName;

   /**
    *  预订部经理邮箱
    */
    @TableField("reservation_manager_mail")
    private String reservationManagerMail;

   /**
    *  商业登记号
    */
    @TableField("commercial_registration_id")
    private String commercialRegistrationId;

   /**
    *  商业登记所在国家编号
    */
    @TableField("commercial_registration_country_code")
    private String commercialRegistrationCountryCode;

   /**
    *  商业登记所在国家名称
    */
    @TableField("commercial_registration_country_name")
    private String commercialRegistrationCountryName;

   /**
    *  商业登记所在州编号
    */
    @TableField("commercial_registration_state_code")
    private String commercialRegistrationStateCode;

   /**
    *  商业登记所在州名称
    */
    @TableField("commercial_registration_state_name")
    private String commercialRegistrationStateName;

   /**
    *  商业登记所在城市编号
    */
    @TableField("commercial_registration_city_code")
    private String commercialRegistrationCityCode;

   /**
    *  商业登记所在城市名称
    */
    @TableField("commercial_registration_city_name")
    private String commercialRegistrationCityName;

   /**
    *  商业登记所在街道与门牌号
    */
    @TableField("commercial_registration_addr")
    private String commercialRegistrationAddr;

   /**
    *  Swift / BIC 
    */
    @TableField("swift_or_bic")
    private String swiftOrBIC;

   /**
    *  监事会成员
    */
    @TableField("supervisory_board")
    private String supervisoryBoard;

   /**
    *  董事总经理/董事会成员
    */
    @TableField("management_director")
    private String managementDirector;

   /**
    *  酒店产权人姓名
    */
    @TableField("owner_name")
    private String ownerName;
}