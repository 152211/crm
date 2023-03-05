package com.hworld.controller.prof;

import com.earth.diana.bo.apol.ext.IndividualVo;
import com.hworld.annotation.Create;
import com.hworld.base.BaseResponse;
import com.hworld.service.api.prof.ProfileService;
import com.hworld.vo.req.sys.SysUserChangeReqVO;
import com.hworld.vo.resp.sys.SysUserRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 客例 Controller
 *
 * @author caoyang
 * @date 2022-02-15 17:26:20
 */
@Api(value = "客例", tags = "客例")
@RestController
//@RequestMapping("/profile")
@Slf4j
@Validated
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/profile/saveIndividual")
    @ApiModelProperty("新增个人客例")
    @ResponseBody
    public BaseResponse<SysUserRespVO> saveIndividualProf(@RequestBody @Validated(value = {Create.class}) IndividualVo reqVO) {
        return profileService.saveIndividualProf(reqVO);
    }
}
