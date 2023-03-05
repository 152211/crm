package com.hworld.controller.member;

import com.hworld.service.api.member.MemberProgramHotelService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 会员计划于酒店关联关系 Controller
 *
 * @author caoyang
 * @date 2022-02-15 17:26:23
 */
@Api(value = "会员计划于酒店关联关系", tags = "会员计划于酒店关联关系")
@RestController
@RequestMapping("/member/programStores")
@Slf4j
@Validated
public class MemberProgramStoreController {

    @Autowired
    private MemberProgramHotelService memberProgramStoreService;

}
