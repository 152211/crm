package com.hworld.vo.req.hdata.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ApiModel("酒店联系信息")
@Getter
@Setter
@ToString
public class HotelBasicInfoContactVO implements Serializable {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("酒店编号")
    private String hotelNo;

    @ApiModelProperty("通用邮箱")
    private String email;

    @ApiModelProperty("通用电话国家号")
    private String phoneCountryCode;

    @ApiModelProperty("通用电话区号")
    private String phoneCode;

    @ApiModelProperty("通用电话号码")
    private String telephone;

    @ApiModelProperty("总经理姓名")
    private String managerName;

    @ApiModelProperty("总经理邮箱")
    private String managerMail;

    @ApiModelProperty("预定邮箱")
    private String reservationEmail;

    @ApiModelProperty("预定电话国家号")
    private String reservationPhoneCountryCode;

    @ApiModelProperty("预定电话区号")
    private String reservationPhoneCode;

    @ApiModelProperty("预定电话号码")
    private String reservationPhone;

    @ApiModelProperty("预订部经理姓名")
    private String reservationManagerName;

    @ApiModelProperty("预订部经理邮箱")
    private String reservationManagerMail;

    @ApiModelProperty("财务邮箱")
    private String financeEmail;

    @ApiModelProperty("财务电话国家号")
    private String financePhoneCountryCode;

    @ApiModelProperty("财务电话区号")
    private String financePhoneCode;

    @ApiModelProperty("财务电话号码")
    private String financePhone;

    @ApiModelProperty("财务经理姓名")
    private String financeManagerName;

    @ApiModelProperty("财务经理邮箱")
    private String financeManagerMail;

    @ApiModelProperty("销售/会务邮箱")
    private String salesEmail;

    @ApiModelProperty("销售/会务电话国家号")
    private String salesPhoneCountryCode;

    @ApiModelProperty("销售/会务电话区号")
    private String salesPhoneCode;

    @ApiModelProperty("销售/会务电话号码")
    private String salesPhone;

    @ApiModelProperty("销售/会务电话姓名")
    private String salesManagerName;

    @ApiModelProperty("销售/会务电话邮箱")
    private String salesManagerMail;

    @ApiModelProperty("手机号码")
    private String mobilePhone;

    @ApiModelProperty("传真国家号")
    private String faxCountryCode;

    @ApiModelProperty("传真区号")
    private String faxAreaCode;

    @ApiModelProperty("传真号码")
    private String fax;

    @ApiModelProperty("酒店网页链接")
    private String url;

    @ApiModelProperty("是否删除")
    private Boolean isDelete;
}
