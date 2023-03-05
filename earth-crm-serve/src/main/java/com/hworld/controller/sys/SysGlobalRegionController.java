package com.hworld.controller.sys;

import com.hworld.annotation.Create;
import com.hworld.annotation.Update;
import com.hworld.base.BaseResponse;
import com.hworld.service.api.external.HDataService;
import com.hworld.service.api.sys.SysGlobalRegionService;
import com.hworld.vo.req.hdata.HDataReqVO;
import com.hworld.vo.req.hdata.region.HDataRegionReqVO;
import com.hworld.vo.resp.hdata.HDataBaseRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 国际城市区域信息 Controller
 *
 * @author caoyang
 * @date 2022-02-08 16:00:53
 */
@Api(value = "国际城市区域信息", tags = "国际城市区域信息")
@RestController
@RequestMapping("/sys/region")
@Slf4j
@Validated
public class SysGlobalRegionController {

    @Autowired
    private SysGlobalRegionService sysGlobalRegionService;


    @PostMapping("/saveHDataRegionAll")
    @ApiModelProperty("保存所有HData行政区域信息")
    @ResponseBody
    public BaseResponse saveHDataRegionAll(@RequestBody @Validated(value = {Update.class, Create.class}) HDataRegionReqVO reqVO) {
        return sysGlobalRegionService.saveHDataRegionAll(reqVO);
    }

    @PostMapping("/getHDataRegionAll")
    @ApiModelProperty("查询HData行政区域信息")
    @ResponseBody
    public BaseResponse getHDataRegionAll(@RequestBody @Validated(value = {Update.class, Create.class}) HDataRegionReqVO reqVO) {
        return sysGlobalRegionService.getHDataRegionAll(reqVO);
    }
}
