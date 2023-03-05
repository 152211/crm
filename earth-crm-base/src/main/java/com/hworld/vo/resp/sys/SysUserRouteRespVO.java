package com.hworld.vo.resp.sys;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统权限DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:07
 */
@ApiModel("用户路由")
@Getter
@Setter
@ToString
public class SysUserRouteRespVO implements Serializable {

    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty("父节点id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pId;

    @ApiModelProperty("名称")
    private String title = "";

    @ApiModelProperty("组件")
    private String component = "";

    @ApiModelProperty("路径")
    private String path = "";

    @ApiModelProperty("编码")
    private String code = "";

    @ApiModelProperty("权限类型 G:菜单组 M：菜单权限，F：功能权限")
    private String type = "";

    @ApiModelProperty("是否是多级目录，1=是 0=否")
    private Boolean isMultiLevel;

    @ApiModelProperty("是否显示")
    private Boolean alwaysShow;

    @ApiModelProperty("重定向参数")
    private String redirect;

    @ApiModelProperty("菜单信息")
    private Meta meta = new Meta();

    @ApiModelProperty("子节点")
    private List<SysUserRouteRespVO> children = new ArrayList<>();

    @ApiModel("系统权限树结构")
    @Getter
    @Setter
    @ToString
    public static class Meta implements Serializable {
        private String title = "";
        private String icon = "";
        private List<String> roles = new ArrayList<>();
    }
}
