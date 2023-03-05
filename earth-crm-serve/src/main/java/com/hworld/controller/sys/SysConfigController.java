package com.hworld.controller.sys;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统配置 Controller
 *
 * @author caoyang
 * @date 2022-02-08 16:00:49
 */
@Api(value = "系统配置", tags = "系统配置")
@RestController
@RequestMapping("/sys/config")
@Slf4j
@Validated
public class SysConfigController {

}
