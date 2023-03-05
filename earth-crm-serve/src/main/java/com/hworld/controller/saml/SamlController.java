//package com.hworld.controller.saml;
//
//import com.hworld.base.BaseResponse;
//import com.hworld.entity.sys.SysUserDO;
//import com.hworld.serivice.login.SamlLoginServiceImpl;
//import com.hworld.service.api.sys.SysUserService;
//import com.hworld.utils.MyStringUtils;
//import com.hworld.vo.req.LoginReqVO;
//import com.hworld.vo.resp.LoginRespVO;
//import com.hzgroup.hzframework.cas.client.bean.AuthenticationInfo;
//import com.hzgroup.hzframework.cas.client.security.exception.AuthenticationException;
//import com.hzgroup.hzframework.cas.client.utility.SAML2Helper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
//
//@RestController
//@Slf4j
//@RequestMapping("/saml")
//public class SamlController {
//
//    @Autowired
//    private SAML2Helper saml2Helper;
//
//    @Autowired
//    private SysUserService sysUserService;
//
//    @Autowired
//    public SamlLoginServiceImpl samlLoginService;
//
//    @Value("${base.site.vue}")
//    private Boolean vue;
//
//    @Value("${base.site.index}")
//    private String index;
//
//    @Value("${base.site.login}")
//    private String login;
//
//    /**
//     * 登陆
//     *
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    @GetMapping("/login")
//    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取自定义参数
//        if (StringUtils.isNotBlank(request.getParameter("path"))) {
//            String path = URLEncoder.encode(request.getParameter("path"), "UTF-8");
//            saml2Helper.login(request, response, path);
//        }
//        saml2Helper.login(request, response);
//    }
//
//    /* 客户端源数据接口
//     * */
//    @GetMapping("/metadata")
//    public void displayMetadata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        saml2Helper.displayMetadata(request, response);
//    }
//
//    /**
//     * 登陆成功后的回跳地址
//     *
//     * @param request
//     * @param response
//     * @return
//     * @throws ServletException
//     * @throws IOException
//     */
//    @PostMapping("/loginprocess")
//    public String procss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AuthenticationException {
//
//        AuthenticationInfo info = saml2Helper.authentication(request, response);
//        String employeeNo = info.getEmployeeNo();
//
//        //调用CRM登录接口
//        BaseResponse baseResponse = samlLoginService.doLogin(request, response, new LoginReqVO(employeeNo, null, "saml"));
//        if (baseResponse.getCode().intValue() != 200) {
//            return "redirect:/401";
//        }
//        //获取登录反馈参数
//        LoginRespVO respVO = (LoginRespVO) baseResponse.getData();
//        if (respVO == null || MyStringUtils.isNullParam(respVO.getSatoken())) {
//            return "redirect:/401";
//        }
//        String accessToken = respVO.getSatoken();
//
//        //获取自定义参数
//        String path = null;
//        if (MyStringUtils.isNotNullParam(request.getParameter("RelayState"))) {
//            path = URLDecoder.decode(request.getParameter("RelayState"), "UTF-8");
//        }
//        if (respVO != null) {
//            String url = null;
//            if (vue) {
//                if (StringUtils.isNotBlank(path)) {
//                    url = index + "?Authentication=" + accessToken + "&path=" + path;
//                } else {
//                    url = index + "?Authentication=" + accessToken;
//                }
//                return "redirect:" + url;
//            } else {
//                return "redirect:/sso?userToken=" + accessToken;
//            }
//        }
//        //返回401页面
//        return "redirect:/404?Authentication=" + accessToken;
//    }
//
//    /**
//     * 退出登陆
//     */
//    @GetMapping("/logout")
//    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        log.info("===================SamlController.logout.start=======================");
//        Long time = System.currentTimeMillis();
//
//        BaseResponse baseResponse = samlLoginService.doLoginOut(request, response);
//        AuthenticationInfo info = (AuthenticationInfo) baseResponse.getData();
//        if (info != null) {
//            saml2Helper.logout(request, response, info, URLEncoder.encode("test", "UTF-8"));
//        }
//        log.info("===================SamlController.logout.end time={}毫秒=======================", System.currentTimeMillis() - time);
//    }
//
//    /**
//     * 退出登陆 回跳地址
//     */
//    @GetMapping("/logoutprocess")
//    public void logoutProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        log.info("===================SamlController.logoutProcess.start=======================");
//        Long time = System.currentTimeMillis();
//        saml2Helper.login(request, response);
//        log.info("===================SamlController.logoutProcess.end time={}毫秒=======================", System.currentTimeMillis() - time);
//    }
//}
