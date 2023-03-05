package com.hworld.vo.resp;

import com.hworld.vo.req.BaseReqVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ApiModel("LoginRespVO")
@Data
@ToString
public class LoginRespVO implements Serializable {

    @ApiModelProperty("satoken")
    private String satoken;
}
