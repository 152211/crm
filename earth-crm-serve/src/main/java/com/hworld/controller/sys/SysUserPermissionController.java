package com.hworld.controller.sys;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户权限对应关系 Controller
 * @author caoyang
 * @date 2022-01-18 12:31:13
 */
@Api(value = "用户权限对应关系", tags = "用户权限对应关系")
@RestController
@RequestMapping("/sys/userPermission")
@Slf4j
@Validated
public class SysUserPermissionController {

}
