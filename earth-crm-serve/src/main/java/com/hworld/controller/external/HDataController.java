package com.hworld.controller.external;

import com.hworld.annotation.Create;
import com.hworld.annotation.Update;
import com.hworld.base.BaseResponse;
import com.hworld.entity.sys.SysHotelDO;
import com.hworld.service.api.external.HDataService;
import com.hworld.service.api.sys.SysHotelService;
import com.hworld.vo.req.hdata.HDataReqVO;
import com.hworld.vo.resp.hdata.HDataBaseRespVO;
import com.hworld.vo.resp.sys.SysHotelRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hdata 推送数据Controller
 *
 * @author caoyang
 * @date 2022-02-08 16:00:53
 */
@Api(value = "Hdata推送数据", tags = "Hdata推送数据")
@RestController
@RequestMapping("/2b/hdata")
@Slf4j
@Validated
public class HDataController {

    @Autowired
    private HDataService hDataService;

    @PostMapping("/pushMasterData")
    @ApiModelProperty("Hdata推送数据")
    @ResponseBody
    public HDataBaseRespVO pushMasterData(@RequestBody @Validated(value = {Update.class, Create.class}) HDataReqVO reqVO) {
        return hDataService.pushMasterData(reqVO);
    }
}
