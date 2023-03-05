package com.hworld.controller.member;

import com.hworld.base.BaseResponse;
import com.hworld.service.api.member.MemberProfileService;
import com.hworld.vo.req.member.MemberProfileReqVO;
import com.hworld.vo.resp.member.MemberProfileRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 会员 Controller
 *
 * @author caoyang
 * @date 2022-02-15 17:26:20
 */
@Api(value = "会员", tags = "会员")
@RestController
@RequestMapping("/member/profiles")
@Slf4j
@Validated
public class MemberProfileController {

    @Autowired
    private MemberProfileService memberProfileService;

    @PostMapping("/memberRegister")
    @ApiModelProperty("会员注册")
    @ResponseBody
    public BaseResponse<MemberProfileRespVO> memberRegister(@RequestBody MemberProfileReqVO reqVO) {
        return memberProfileService.memberRegister(reqVO);
    }

    @PostMapping("/updateMember")
    @ApiModelProperty("会员修改")
    @ResponseBody
    public BaseResponse<MemberProfileRespVO> updateMember(@RequestBody MemberProfileReqVO reqVO) {
        return memberProfileService.updateMember(reqVO);
    }
}
