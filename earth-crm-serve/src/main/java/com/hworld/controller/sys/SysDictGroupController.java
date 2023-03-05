package com.hworld.controller.sys;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统字典分组 Controller
 *
 * @author caoyang
 * @date 2022-02-08 16:00:50
 */
@Api(value = "系统字典分组", tags = "系统字典分组")
@RestController
@RequestMapping("/sys/dictGroup")
@Slf4j
@Validated
public class SysDictGroupController {
}
