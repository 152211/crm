package com.hworld.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.hworld.entity.sys.SysPermissionDO;
import com.hworld.service.api.sys.SysPermissionService;
import com.hworld.utils.MyStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@Slf4j
public class SaTokenConfig implements WebMvcConfigurer {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义认证规则
        registry.addInterceptor(new SaRouteInterceptor((req, res, handler) -> {
            // 登录认证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
            SaRouter.match("/**").notMatch("/login/**", "/saml/**","/2b/**").check(r -> StpUtil.checkLogin());

            // 角色认证 -- 拦截以 admin 开头的路由，必须具备 admin 角色或者 super-admin 角色才可以通过认证
//            SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("admin", "super-admin"));

            // 权限认证 -- 不同模块认证不同权限
            List<SysPermissionDO> sysPermissionDOList = sysPermissionService.getPermissionAllList();
            if (!CollectionUtils.isEmpty(sysPermissionDOList)) {
                for (SysPermissionDO sysPermissionDO : sysPermissionDOList) {
                    if (MyStringUtils.isNotNullParam(sysPermissionDO.getPermissionUrl()) && MyStringUtils.isNotNullParam(sysPermissionDO.getPermissionCode())) {
                        SaRouter.match(sysPermissionDO.getPermissionUrl(), r -> StpUtil.checkPermission(sysPermissionDO.getPermissionCode()));
                    }
                }
            }

        })).addPathPatterns("/**");
    }
}
