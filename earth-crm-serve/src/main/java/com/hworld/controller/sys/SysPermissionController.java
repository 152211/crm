package com.hworld.controller.sys;

import com.hworld.annotation.*;
import com.hworld.base.BasePagedResponse;
import com.hworld.base.BaseReqPage;
import com.hworld.base.BaseResponse;
import com.hworld.service.api.sys.SysPermissionService;
import com.hworld.vo.req.sys.SysParentReqVO;
import com.hworld.vo.req.sys.SysPermissionReqVO;
import com.hworld.vo.req.sys.SysUserReqVO;
import com.hworld.vo.resp.sys.SysPermissionRespVO;
import com.hworld.vo.resp.sys.SysPermissionTreeRespVO;
import com.hworld.vo.resp.sys.SysUserRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统权限 Controller
 *
 * @author caoyang
 * @date 2022-01-18 12:31:07
 */
@Api(value = "系统权限", tags = "系统权限")
@RestController
@RequestMapping("/sys/permission")
@Slf4j
@Validated
public class SysPermissionController {

    @Autowired
    private SysPermissionService permissionService;

    @PostMapping("/addPermission")
    @ApiModelProperty("新增系统权限")
    @ResponseBody
    public BaseResponse<SysPermissionRespVO> addPermission(@RequestBody @Validated(value = {Create.class}) SysPermissionReqVO reqVO) {
        return permissionService.addPermission(reqVO);
    }

    @PostMapping("/deleltPermission")
    @ApiModelProperty("删除系统权限")
    @ResponseBody
    public BaseResponse<SysPermissionRespVO> deleltPermission(@RequestBody @Validated(value = {Delelt.class}) SysPermissionReqVO reqVO) {
        return permissionService.deleltPermission(reqVO);
    }

    @PostMapping("/updatePermission")
    @ApiModelProperty("更新系统权限")
    @ResponseBody
    public BaseResponse<SysPermissionRespVO> updatePermission(@RequestBody @Validated(value = {Update.class}) SysPermissionReqVO reqVO) {
        return permissionService.updatePermission(reqVO);
    }

    @PostMapping("/getPermissionList")
    @ApiModelProperty("获取权限列表(不分页)")
    @ResponseBody
    public BaseResponse<SysPermissionRespVO> getPermissionList(@RequestBody SysPermissionReqVO reqVO) {
        return permissionService.getPermissionList(reqVO);
    }

    @PostMapping("/getPermissionTree")
    @ApiModelProperty("获取权限树结构")
    @ResponseBody
    public BaseResponse<SysPermissionTreeRespVO> getPermissionTree(@RequestBody SysPermissionReqVO reqVO) {
        return permissionService.getPermissionTree(reqVO);
    }

    @PostMapping("/getPermissionPage")
    @ApiModelProperty("查询权限列表(分页)")
    @ResponseBody
    public BasePagedResponse<SysPermissionRespVO> getPermissionPage(@RequestBody @Validated(value = {Select.class}) BaseReqPage<SysPermissionReqVO> reqPage) {
        return permissionService.getPermissionPage(reqPage);
    }

    @PostMapping("/getPermissionOne")
    @ApiModelProperty("获取权限详情")
    @ResponseBody
    public BaseResponse<SysPermissionRespVO> getPermissionOne(@RequestBody @Validated(value = {SelectOne.class}) SysPermissionReqVO reqVO) {
        return permissionService.getPermissionOne(reqVO);
    }

    @PostMapping("/getParentList")
    @ApiModelProperty("获取权限列表(不分页)")
    @ResponseBody
    public BaseResponse<SysPermissionRespVO> getParentList(@RequestBody SysParentReqVO reqVO) {
        return permissionService.getParentList(reqVO);
    }
}
