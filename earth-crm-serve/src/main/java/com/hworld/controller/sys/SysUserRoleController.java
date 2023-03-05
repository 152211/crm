package com.hworld.controller.sys;

import com.hworld.service.api.sys.SysUserRoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户角色对应关系 Controller
 * @author caoyang
 * @date 2022-01-18 12:31:13
 */
@Api(value = "用户角色对应关系", tags = "用户角色对应关系")
@RestController
@RequestMapping("/sys/userRole")
@Slf4j
@Validated
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService userRoleService;

}
