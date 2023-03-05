package com.hworld.serivice.login;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.hworld.base.BaseResponse;
import com.hworld.entity.sys.SysUserDO;
import com.hworld.service.api.login.LoginService;
import com.hworld.service.api.sys.SysUserService;
import com.hworld.utils.MyStringUtils;
import com.hworld.vo.req.LoginReqVO;
import com.hworld.vo.resp.LoginRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Slf4j
@Service
public abstract class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public BaseResponse doLogin(HttpServletRequest request, HttpServletResponse response, LoginReqVO reqVO) {

        //1.0.设置reqVO参数
        BaseResponse baseResponse = setLoginReqVO(request, response, reqVO);
        if (baseResponse.getCode().intValue() != 200) {
            return baseResponse;
        }

        //1.1.校验请求参数
        baseResponse = checkReqData(reqVO);
        if (baseResponse.getCode().intValue() != 200) {
            return baseResponse;
        }

        //1.2.查询用户信息
        SysUserDO sysUserDO = getUserInfo(reqVO.getEmployeeNo());

        //1.3.校验用户信息
        baseResponse = checkUser(sysUserDO, reqVO.getPassWord());
        if (baseResponse.getCode().intValue() != 200) {
            return baseResponse;
        }
        String token = getUserToken(sysUserDO.getId());
        //1.5.设置token
        LoginRespVO respVO = new LoginRespVO();
        respVO.setSatoken(token);

        //1.6.记录登录时间
        SysUserDO newSysUserDo = new SysUserDO();
        newSysUserDo.setLastLoginTime(LocalDateTime.now());
        newSysUserDo.setId(sysUserDO.getId());
        sysUserService.updateById(newSysUserDo);

        return BaseResponse.success(respVO);
    }


    @Override
    public BaseResponse doLoginOut(HttpServletRequest request, HttpServletResponse response) {
        //1.0.获取token
        String token = request.getHeader("satoken");

        //1.1.CRM登出
        StpUtil.logout();

        //1.2.获取SSO Auth信息
        return getSsoAuth(token);
    }

    /**
     * 获取用户token
     *
     * @param userId
     * @return
     */
    public String getUserToken(Long userId) {
        String token = StpUtil.getTokenValueByLoginId(userId);
        if (MyStringUtils.isNotNullParam(token)) {
            return token;
        }
        //用户已登录
        if (StpUtil.isLogin()) {
            return StpUtil.getTokenValueByLoginId(userId);
        }
        //执行crm登录
        StpUtil.logout(userId);
        StpUtil.login(userId);

        return StpUtil.getTokenValueByLoginId(userId);
    }

    /**
     * 获取用户信息
     *
     * @param employeeNo
     * @return
     */
    public SysUserDO getUserInfo(String employeeNo) {
        return sysUserService.getOneByEmployeeNo(employeeNo);
    }


    /**
     * 解析请求
     *
     * @return
     */
    abstract BaseResponse setLoginReqVO(HttpServletRequest request, HttpServletResponse response, LoginReqVO reqVO);

    /**
     * 校验请求参数
     *
     * @return
     */
    abstract BaseResponse checkReqData(LoginReqVO reqVO);

    /**
     * 校验用户信息
     *
     * @return
     */
    abstract BaseResponse checkUser(SysUserDO sysUserDO, String passWordReq);

    /**
     * @param token
     * @return
     */
    abstract BaseResponse getSsoAuth(String token);
}
