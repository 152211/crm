package com.hworld.controller.sys;

import com.alibaba.fastjson.JSON;
import com.hworld.annotation.*;
import com.hworld.base.BasePagedResponse;
import com.hworld.base.BaseReqPage;
import com.hworld.base.BaseResponse;
import com.hworld.http.WebContext;
import com.hworld.service.api.sys.SysUserService;
import com.hworld.vo.req.sys.*;
import com.hworld.vo.resp.sys.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工管理 Controller
 *
 * @author caoyang
 * @date 2022-01-18 12:31:12
 */
@Api(value = "员工管理", tags = "员工管理")
@RestController
@RequestMapping("/sys/user")
@Slf4j
@Validated
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/addUser")
    @ApiModelProperty("新增用户")
    @ResponseBody
    public BaseResponse<SysUserRespVO> addUser(@RequestBody @Validated(value = {Create.class}) SysUserChangeReqVO reqVO) {
        return sysUserService.addUser(reqVO);
    }

    @PostMapping("/deleltUser")
    @ApiModelProperty("删除用户")
    @ResponseBody
    public BaseResponse<SysUserRespVO> deleltUser(@RequestBody @Validated(value = {Delelt.class}) SysUserReqVO reqVO) {
        return sysUserService.deleltUser(reqVO);
    }

    @PostMapping("/updateUser")
    @ApiModelProperty("更新用户")
    @ResponseBody
    public BaseResponse<SysUserRespVO> updateUser(@RequestBody @Validated(value = {Update.class}) SysUserChangeReqVO reqVO) {
        return sysUserService.updateUser(reqVO);
    }

    @PostMapping("/getUserList")
    @ApiModelProperty("查询用户列表(不分页)")
    @ResponseBody
    public BaseResponse<SysUserRespVO> getUserList(@RequestBody @Validated(value = {Select.class}) SysUserReqVO reqVO) {
        return sysUserService.getUserList(reqVO);
    }

    @PostMapping("/getUserPage")
    @ApiModelProperty("查询用户列表(分页)")
    @ResponseBody
    public BasePagedResponse<SysUserRespVO> getUserPage(@RequestBody @Validated(value = {Select.class}) BaseReqPage<SysUserReqVO> reqPage) {
        return sysUserService.getUserPage(reqPage);
    }

    @PostMapping("/getUserInfo")
    @ApiModelProperty("查询用户详情")
    @ResponseBody
    public BaseResponse<SysUserDetailsRespVO> getUserInfo(@RequestBody @Validated(value = {SelectOne.class}) SysUserReqVO reqVO) {
        return sysUserService.getUserInfo(reqVO);
    }

    @PostMapping("/getUserInfoCurrent")
    @ApiModelProperty("查询当前用户详情")
    @ResponseBody
    public BaseResponse<SysUserDetailsRespVO> getUserInfoCurrent() {
        SysUserReqVO reqVO = new SysUserReqVO();
        reqVO.setId(WebContext.getUserId());
        return sysUserService.getUserInfo(reqVO);
    }

    @PostMapping("/getUserPermissionTree")
    @ApiModelProperty("查询当前用户权限信息")
    @ResponseBody
    public BaseResponse<List<SysPermissionTreeRespVO>> getUserPermissionTree() {
        SysUserReqVO reqVO = new SysUserReqVO();
        reqVO.setId(WebContext.getUserId());
        return sysUserService.getUserPermissionTree(reqVO);
    }

    @PostMapping("/getRouter")
    @ApiModelProperty("查询当前用户权限信息")
    @ResponseBody
    public BaseResponse<List<SysUserRouteRespVO>> getRouter() {
        return sysUserService.getRouter(WebContext.getUserId());
    }

    @PostMapping("/getUserPermissionCode")
    @ApiModelProperty("查询当前用户权限code列表")
    @ResponseBody
    public BaseResponse<List<String>> getUserPermissionCode() {
        return sysUserService.getUserPermissionCode(WebContext.getUserId());
    }

    @PostMapping("/getUserHote")
    @ApiModelProperty("查询当前用户酒店列表")
    @ResponseBody
    public BaseResponse<List<SysUserHotelRespVO>> getUserHote() {
        return sysUserService.getUserHotelByUserId(WebContext.getUserId());
    }
}
