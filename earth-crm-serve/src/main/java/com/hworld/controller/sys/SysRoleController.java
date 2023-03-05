package com.hworld.controller.sys;

import com.hworld.annotation.*;
import com.hworld.base.BasePagedResponse;
import com.hworld.base.BaseReqPage;
import com.hworld.base.BaseResponse;
import com.hworld.service.api.sys.SysRoleService;
import com.hworld.vo.req.sys.SysPermissionReqVO;
import com.hworld.vo.req.sys.SysRoleChangeReqVO;
import com.hworld.vo.req.sys.SysRoleReqVO;
import com.hworld.vo.resp.sys.SysPermissionRespVO;
import com.hworld.vo.resp.sys.SysRoleDetailsRespVO;
import com.hworld.vo.resp.sys.SysRoleRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 角色维护 Controller
 *
 * @author caoyang
 * @date 2022-01-18 12:31:10
 */
@Api(value = "角色维护", tags = "角色维护")
@RestController
@RequestMapping("/sys/role")
@Slf4j
@Validated
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/addRole")
    @ApiModelProperty("新增系统角色")
    @ResponseBody
    public BaseResponse<SysRoleRespVO> addRole(@RequestBody @Validated(value = {Create.class}) SysRoleChangeReqVO reqVO) {
        return sysRoleService.addRole(reqVO);
    }

    @PostMapping("/deleltRole")
    @ApiModelProperty("删除系统角色")
    @ResponseBody
    public BaseResponse<SysRoleRespVO> deleltRole(@RequestBody @Validated(value = {Delelt.class}) SysRoleReqVO reqVO) {
        return sysRoleService.deleltRole(reqVO);
    }

    @PostMapping("/updateRole")
    @ApiModelProperty("更新系统角色")
    @ResponseBody
    public BaseResponse<SysRoleRespVO> updateRole(@RequestBody @Validated(value = {Update.class}) SysRoleChangeReqVO reqVO) {
        return sysRoleService.updateRole(reqVO);
    }

    @PostMapping("/getRoleList")
    @ApiModelProperty("查询角色列表(不分页)")
    @ResponseBody
    public BaseResponse<SysRoleRespVO> getRoleList(@RequestBody SysRoleReqVO reqVO) {
        return sysRoleService.getRoleList(reqVO);
    }

    @PostMapping("/getRolePage")
    @ApiModelProperty("查询角色列表(分页)")
    @ResponseBody
    public BasePagedResponse<SysRoleRespVO> getRolePage(@RequestBody @Validated(value = {Select.class}) BaseReqPage<SysRoleReqVO> reqPage) {
        return sysRoleService.getRolePage(reqPage);
    }

    @PostMapping("/getRoleOne")
    @ApiModelProperty("查询角色详情")
    @ResponseBody
    public BaseResponse<SysRoleDetailsRespVO> getRoleOne(@RequestBody @Validated(value = {SelectOne.class}) SysRoleReqVO reqVO) {
        return sysRoleService.getRoleOne(reqVO);
    }
}
