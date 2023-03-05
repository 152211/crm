package com.hworld.vo.resp.sys;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Create;
import com.hworld.annotation.Update;
import com.hworld.utils.LongListFormatSerializer;
import com.hworld.vo.resp.BaseRespVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * 员工管理DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:12
 */
@ApiModel("用户详情")
@Getter
@Setter
@ToString
public class SysUserDetailsRespVO extends SysUserRespVO {

    @ApiModelProperty("是否是超级管理员")
    private Boolean isAdmin;

    @ApiModelProperty("角色列表")
    private List<SysRoleRespVO> roleList;

    @ApiModelProperty("用户酒店列表")
    private List<SysUserHotelRespVO> hotelList;

    @ApiModelProperty("权限树结构列表")
    private List<SysPermissionTreeRespVO> permissionList;

    @ApiModelProperty("角色ID列表")
    @JsonSerialize(using = LongListFormatSerializer.class)
    private List<Long> roleIdList;

    @ApiModelProperty("用户酒店ID列表")
    @JsonSerialize(using = LongListFormatSerializer.class)
    private List<Long> hotelIdList;

    @ApiModelProperty("用户酒店code列表")
    private List<String> hotelNoList;
}
