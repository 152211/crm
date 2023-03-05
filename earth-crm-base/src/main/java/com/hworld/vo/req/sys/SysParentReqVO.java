package com.hworld.vo.req.sys;

import com.hworld.annotation.Create;
import com.hworld.annotation.Select;
import com.hworld.annotation.Update;
import com.hworld.vo.req.BaseReqVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 系统权限DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:07
 */
@ApiModel("系统权限")
@Getter
@Setter
@ToString
public class SysParentReqVO extends BaseReqVO {
    /**
     * 权限类型 M：菜单权限，F：功能权限
     */
    @ApiModelProperty("权限类型G:菜单目录 M：菜单，F：功能权限")
    @NotNull(message = "permissionType Can not be empty", groups = {Select.class})
    private String permissionType;
}
