package com.hworld.controller.sys;

import com.hworld.annotation.*;
import com.hworld.base.BasePagedResponse;
import com.hworld.base.BaseReqPage;
import com.hworld.base.BaseResponse;
import com.hworld.service.api.sys.SysHotelService;
import com.hworld.vo.req.sys.SysHotelReqVO;
import com.hworld.vo.resp.sys.SysHotelRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 酒店管理维护 Controller
 *
 * @author caoyang
 * @date 2022-02-08 16:00:53
 */
@Api(value = "酒店管理维护", tags = "酒店管理维护")
@RestController
@RequestMapping("/sys/hotel")
@Slf4j
@Validated
public class SysHotelController {

    @Autowired
    private SysHotelService sysHotelService;

    @PostMapping("/addHotel")
    @ApiModelProperty("新增酒店")
    @ResponseBody
    public BaseResponse<SysHotelRespVO> addHotel(@RequestBody @Validated(value = {Create.class}) SysHotelReqVO reqVO) {
        return sysHotelService.addHotel(reqVO);
    }

    @PostMapping("/deleltHotel")
    @ApiModelProperty("删除酒店")
    @ResponseBody
    public BaseResponse<SysHotelRespVO> deleltHotel(@RequestBody @Validated(value = {Delelt.class}) SysHotelReqVO reqVO) {
        return sysHotelService.deleltHotel(reqVO);
    }

    @PostMapping("/updateHotel")
    @ApiModelProperty("更新酒店")
    @ResponseBody
    public BaseResponse<SysHotelRespVO> updateHotel(@RequestBody @Validated(value = {Update.class}) SysHotelReqVO reqVO) {
        return sysHotelService.updateHotel(reqVO);
    }

    @PostMapping("/getHotelList")
    @ApiModelProperty("获取酒店列表(不分页)")
    @ResponseBody
    public BaseResponse<SysHotelRespVO> getHotelList(@RequestBody SysHotelReqVO reqVO) {
        return sysHotelService.getHotelList(reqVO);
    }

    @PostMapping("/getHotelTree")
    @ApiModelProperty("获取酒店树结构")
    @ResponseBody
    public BaseResponse<SysHotelRespVO> getHotelTree(@RequestBody SysHotelReqVO reqVO) {
        return sysHotelService.getHotelTree(reqVO);
    }

    @PostMapping("/getHotelPage")
    @ApiModelProperty("查询酒店列表(分页)")
    @ResponseBody
    public BasePagedResponse<SysHotelRespVO> getHotelPage(@RequestBody @Validated(value = {Select.class}) BaseReqPage<SysHotelReqVO> reqPage) {
        return sysHotelService.getHotelPage(reqPage);
    }

    @PostMapping("/getHotelOne")
    @ApiModelProperty("获取酒店详情")
    @ResponseBody
    public BaseResponse<SysHotelRespVO> getHotelOne(@RequestBody @Validated(value = {SelectOne.class}) SysHotelReqVO reqVO) {
        return sysHotelService.getHotelOne(reqVO);
    }
}
