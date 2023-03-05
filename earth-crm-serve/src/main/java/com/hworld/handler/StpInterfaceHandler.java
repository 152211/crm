package com.hworld.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.hworld.entity.sys.SysUserPermissionDO;
import com.hworld.entity.sys.SysUserRoleDO;
import com.hworld.service.api.sys.*;
import com.hworld.utils.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.dev33.satoken.stp.StpInterface;
import org.springframework.util.CollectionUtils;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
public class StpInterfaceHandler implements StpInterface {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserPermissionService sysUserPermissionService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object userId, String userType) {
        return sysUserPermissionService.selectUserPermissionCodeByUserId(MyStringUtils.stringToLong(String.valueOf(userId)));
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object userId, String userType) {

        //1.0:根据loginId查询用户角色信息
        return sysUserRoleService.selectRoleCodeByUserId(MyStringUtils.stringToLong(String.valueOf(userId)));
    }

}