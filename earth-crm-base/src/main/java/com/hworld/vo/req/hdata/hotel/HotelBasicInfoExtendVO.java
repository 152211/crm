package com.hworld.vo.req.hdata.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@ApiModel("酒店管理、财务信息扩展字段 ")
@Getter
@Setter
@ToString
public class HotelBasicInfoExtendVO implements Serializable {

    @ApiModelProperty(" 主键id")
    private Long id;

    @ApiModelProperty(" 酒店编号")
    private String hotelNo;

    @ApiModelProperty(" PMS ID")
    private String pmsId;

    @ApiModelProperty(" 银行代码")
    private String bankCode;

    @ApiModelProperty(" Swift / BIC")
    private String swiftOrBIC;

    @ApiModelProperty(" CVR号")
    private String cvrNumber;

    @ApiModelProperty(" 开户银行")
    private String depositBank;

    @ApiModelProperty(" 开户银行所在国家")
    private String bankCountry;

    @ApiModelProperty(" 开户银行所在州")
    private String bankState;

    @ApiModelProperty(" 开户银行所在城市")
    private String bankCity;

    @ApiModelProperty(" 开户银行所在地邮编")
    private String bankZipCode;

    @ApiModelProperty(" 开票公司")
    private String legalName;

    @ApiModelProperty(" 开票公司所在国家")
    private String legalCompanyCountry;

    @ApiModelProperty(" 开票公司所在州")
    private String legalCompanyState;

    @ApiModelProperty(" 开票公司所在城市")
    private String legalCompanyCity;

    @ApiModelProperty(" 开票公司所在街道与门牌号")
    private String legalCompanyAddr;

    @ApiModelProperty(" 管理公司名称")
    private String managementCompanyName;

    @ApiModelProperty(" 管理公司所在国家")
    private String managementCompanyCountry;

    @ApiModelProperty(" 管理公司所在州")
    private String managementCompanyState;

    @ApiModelProperty(" 管理公司所在城市")
    private String managementCompanyCity;

    @ApiModelProperty(" 管理公司所在街道和门牌号")
    private String managementCompanyAddr;

    @ApiModelProperty(" 酒店产权人姓名")
    private String ownerName;

    @ApiModelProperty(" 董事总经理/董事会成员")
    private String managementDirector;

    @ApiModelProperty(" 监事会成员")
    private String supervisoryBoard;

    @ApiModelProperty(" 商业登记号")
    private String commercialRegistrationId;

    @ApiModelProperty(" 商业登记所在国家")
    private String commercialRegistrationCountry;

    @ApiModelProperty(" 商业登记所在州")
    private String commercialRegistrationState;

    @ApiModelProperty(" 商业登记所在城市")
    private String commercialRegistrationCity;

    @ApiModelProperty(" 商业登记所在街道与门牌号")
    private String commercialRegistrationAddr;

    @ApiModelProperty(" 是否删除")
    private Boolean isDelete;
}
