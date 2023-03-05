//package com.hworld.serivice.login;
//
//import cn.dev33.satoken.stp.SaLoginModel;
//import cn.dev33.satoken.stp.StpUtil;
//import com.alibaba.fastjson.JSONObject;
//import com.hworld.base.BaseResponse;
//import com.hworld.constants.RedisConstants;
//import com.hworld.entity.sys.SysUserDO;
//import com.hworld.enums.ErrorEnum;
//import com.hworld.utils.MyStringUtils;
//import com.hworld.utils.RedisUtil;
//import com.hworld.vo.req.LoginReqVO;
//import com.hzgroup.hzframework.cas.client.bean.AuthenticationInfo;
//import com.hzgroup.hzframework.cas.client.utility.SAML2Helper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Slf4j
//@Service
//public class SamlLoginServiceImpl extends LoginServiceImpl {
//
//    @Autowired
//    private SAML2Helper saml2Helper;
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//    @Override
//    public BaseResponse setLoginReqVO(HttpServletRequest request, HttpServletResponse response, LoginReqVO reqVO) {
//        try {
//            AuthenticationInfo info = saml2Helper.authentication(request, response);
//
//            if (info == null || MyStringUtils.isNullParam(info.getUserName())) {
//                log.error("SamlLoginServiceImpl.setLoginReqVO.error:Crm 用户不存在");
//                return BaseResponse.error("redirect:/404");
//            }
//            Long timOut = new Long(60 * 60 * 24 * 10000);
//            redisUtil.doOpsForValue(RedisConstants.SSO_AUTH_TOKEN_PREFIX + StpUtil.getTokenValue(), JSONObject.toJSONString(info), timOut);
//            reqVO = reqVO == null ? new LoginReqVO() : reqVO;
//            reqVO.setEmployeeNo(info.getUserName());
//            return BaseResponse.success(reqVO);
//        } catch (Exception e) {
//            log.error("SamlLoginServiceImpl.setLoginReqVO.error:系统异常:{}",e);
//            return BaseResponse.error("redirect:/404");
//        }
//    }
//
//    /**
//     * 校验请求参数
//     *
//     * @return
//     */
//    @Override
//    public BaseResponse checkReqData(LoginReqVO reqVO) {
//        return BaseResponse.success();
//    }
//
//    /**
//     * 校验用户信息
//     *
//     * @param sysUserDO   用户信息
//     * @param passWordReq 用户输入的密码
//     * @return
//     */
//    @Override
//    public BaseResponse checkUser(SysUserDO sysUserDO, String passWordReq) {
//        if (sysUserDO == null) {
//            return BaseResponse.error(ErrorEnum.USER_NOT_EXIST_ERROR.getCode(), ErrorEnum.USER_NOT_EXIST_ERROR.getMsgEn());
//        }
//        return BaseResponse.success();
//    }
//
//    @Override
//    public BaseResponse getSsoAuth(String token) {
//        try {
//            String val = redisUtil.getString(RedisConstants.SSO_AUTH_TOKEN_PREFIX + StpUtil.getTokenValue());
//            AuthenticationInfo info = JSONObject.parseObject(val, AuthenticationInfo.class);
//            return BaseResponse.success(info);
//        } catch (Exception e) {
//            log.error("出现异常：{}",e);
//        }
//        return BaseResponse.success(null);
//    }
//}
