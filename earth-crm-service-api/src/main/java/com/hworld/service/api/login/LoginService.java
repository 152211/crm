package com.hworld.service.api.login;

import com.hworld.base.BaseResponse;
import com.hworld.vo.req.LoginReqVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    /**
     * 登录
     *
     * @param reqVO
     * @return
     */
    BaseResponse doLogin(HttpServletRequest request, HttpServletResponse response, LoginReqVO reqVO);


    /**
     * 登出
     *
     * @param
     * @return
     */
    BaseResponse doLoginOut(HttpServletRequest request, HttpServletResponse response);
}
