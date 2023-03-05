package com.hworld.controller.member;

import com.hworld.service.api.member.MemberProgramService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 会员计划 Controller
 *
 * @author caoyang
 * @date 2022-02-15 17:26:20
 */
@Api(value = "会员计划", tags = "会员计划")
@RestController
@RequestMapping("/member/programs")
@Slf4j
@Validated
public class MemberProgramController {

    @Autowired
    private MemberProgramService memberProgramService;

}
