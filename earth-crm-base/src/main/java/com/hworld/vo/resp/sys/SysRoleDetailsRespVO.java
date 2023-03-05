package com.hworld.vo.resp.sys;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.utils.LongListFormatSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 员工管理DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:12
 */
@ApiModel("角色详情")
@Getter
@Setter
@ToString
public class SysRoleDetailsRespVO extends SysRoleRespVO {


    @ApiModelProperty("权限列表")
    private List<SysPermissionRespVO> permissionList;

    @JsonSerialize(using = LongListFormatSerializer.class)
    private List<Long> permissionIdList;
}
