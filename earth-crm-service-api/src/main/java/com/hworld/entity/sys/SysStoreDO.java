//package com.hworld.entity.sys;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.hworld.base.BaseDO;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
///**
// * 酒店管理维护Entity
// *
// * @author caoyang
// * @version 1.0
// * @date 2022-02-08 16:00:53
// **/
//@TableName("sys_store")
//@Getter
//@Setter
//@ToString
//public class SysStoreDO extends BaseDO {
//
//
//    /**
//     * 主键
//     */
//    @TableId(value = "id", type = IdType.ID_WORKER)
//    private Long id;
//
//    /**
//     * 父节点ID
//     */
//    @TableField("parent_id")
//    private Long parentId;
//
//    /**
//     * 酒店编号
//     */
//    @TableField("store_no")
//    private String storeNo;
//
//    /**
//     * R-根节点,G-总部部门,A/B-品牌,S-门店
//     */
//    @TableField("type")
//    private String type;
//
//    /**
//     * 酒店名称
//     */
//    @TableField("name")
//    private String name;
//
//    /**
//     * 酒店简称
//     */
//    @TableField("short_name")
//    private String shortName;
//
//    /**
//     * 国家
//     */
//    @TableField("country_area_code")
//    private String countryAreaCode;
//
//    /**
//     * 币种
//     */
//    @TableField("currency_code")
//    private String currencyCode;
//
//    /**
//     * 省
//     */
//    @TableField("province")
//    private String province;
//
//    /**
//     * 城市
//     */
//    @TableField("city")
//    private String city;
//
//    /**
//     * 邮编
//     */
//    @TableField("zip")
//    private String zip;
//
//    /**
//     * 详细地址
//     */
//    @TableField("address")
//    private String address;
//
//    /**
//     * 地址2
//     */
//    @TableField("address2")
//    private String address2;
//
//    /**
//     * 财务系统代码（第三方）
//     */
//    @TableField("control_code")
//    private String controlCode;
//
//    /**
//     * 酒店编号EHR主数据
//     */
//    @TableField("hr_code")
//    private String hrCode;
//
//    /**
//     * 描述
//     */
//    @TableField("description")
//    private String description;
//
//    /**
//     * 联系方式国家码
//     */
//    @TableField("phone_country")
//    private String phoneCountry;
//
//    /**
//     * 电话
//     */
//    @TableField("phone")
//    private String phone;
//
//    /**
//     * 移动电话
//     */
//    @TableField("mobile")
//    private String mobile;
//
//    /**
//     * 邮箱
//     */
//    @TableField("email")
//    private String email;
//
//    /**
//     * 传真
//     */
//    @TableField("fax")
//    private String fax;
//
//    /**
//     * 联系人
//     */
//    @TableField("contact_with")
//    private String contactWith;
//
//    /**
//     * 状态,O=营业中 R=试营业
//     */
//    @TableField("status")
//    private String status;
//
//    /**
//     * 城市税货币类型
//     */
//    @TableField("city_tax_hotel_currency")
//    private String cityTaxHotelCurrency;
//
//    /**
//     * 城市税比例
//     */
//    @TableField("city_tax_percent")
//    private String cityTaxPercent;
//
//    /**
//     * 注册号
//     */
//    @TableField("register_number")
//    private String registerNumber;
//
//    /**
//     * 总部名称
//     */
//    @TableField("legal_headquarter")
//    private String legalHeadquarter;
//
//    /**
//     * 公司主体名称
//     */
//    @TableField("legal_entity_name")
//    private String legalEntityName;
//
//    /**
//     * 税务登记证号
//     */
//    @TableField("tax_number")
//    private String taxNumber;
//
//    /**
//     * 增值税发票号码
//     */
//    @TableField("vat_id_number")
//    private String vatIdNumber;
//
//    /**
//     * 时区
//     */
//    @TableField("property_time_zone")
//    private String propertyTimeZone;
//
//    /**
//     * 银行代码
//     */
//    @TableField("bic")
//    private String bic;
//
//    /**
//     * 银行帐户号码
//     */
//    @TableField("iban")
//    private String iban;
//
//    /**
//     * 装修日期
//     */
//    @TableField("soft_open_date")
//    private String softOpenDate;
//
//    /**
//     * 开放日期
//     */
//    @TableField("open_date")
//    private String openDate;
//}