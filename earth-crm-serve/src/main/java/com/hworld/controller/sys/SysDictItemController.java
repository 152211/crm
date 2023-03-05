package com.hworld.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统字典选项 Controller
 *
 * @author caoyang
 * @date 2022-02-08 16:00:52
 */
@Api(value = "系统字典选项", tags = "系统字典选项")
@RestController
@RequestMapping("/sys/dictItem")
@Slf4j
@Validated
public class SysDictItemController {
}
