package com.hworld.vo.req.sys;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 角色新增DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:10
 */
@ApiModel("角色变更")
@Getter
@Setter
@ToString
public class SysRoleChangeReqVO extends SysRoleReqVO {
    /**
     * 权限ID
     */
    @ApiModelProperty("权限ID集合")
    @JsonSerialize(using = ToStringSerializer.class)
    private List<Long> permissionIdList;

    //TODO 作废SysRoleStore表 酒店数据不应该跟角色权限相关联，应该将酒店看作部门 caoyang 2022-07-06
//    /**
//     * 酒店ID
//     */
//    @ApiModelProperty("酒店ID集合")
//    private List<Long> storeIdList;
}
