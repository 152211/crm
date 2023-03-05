package com.hworld.controller;

import com.hworld.base.BaseResponse;
import com.hworld.serivice.login.CrmLoginServiceImpl;
import com.hworld.vo.req.LoginReqVO;
import com.hworld.vo.resp.LoginRespVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@ApiModel("LoginController")
@Slf4j
public class LoginController {
    @Autowired
    public CrmLoginServiceImpl crmLoginServiceImpl;

    @PostMapping("/login")
    @ApiModelProperty("用户登录接口")
    @ResponseBody
    public BaseResponse<LoginRespVO> doLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginReqVO reqVO) {
        return crmLoginServiceImpl.doLogin(request, response, reqVO);

    }

    @PostMapping("/loginOut")
    @ApiModelProperty("用户登录接口")
    @ResponseBody
    public BaseResponse doLoginOut(HttpServletRequest request, HttpServletResponse response) {
        return crmLoginServiceImpl.doLoginOut(request, response);
    }
}
