package com.hworld.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ApiModel("LoginReqVO")
@Data
@ToString
public class LoginReqVO extends BaseReqVO {

    @ApiModelProperty("工号/登录账号")
    private String employeeNo;

    @ApiModelProperty("密码")
    private String passWord;

    @ApiModelProperty("渠道:crm,saml")
    private String channel;

    public LoginReqVO(String employeeNo, String passWord, String channel) {
        this.employeeNo = employeeNo;
        this.passWord = passWord;
        this.channel = channel;
    }

    public LoginReqVO() {
    }
}
