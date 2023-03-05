package com.hworld.bo.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 全球地区表
 *
 * @author caoyang
 * @date 2022-01-18 12:31:10
 */
@ApiModel("全球地区父节点")
@Getter
@Setter
@ToString
public class SysGlobalRegionParentBO implements Serializable {

    @ApiModelProperty("父ID")
    private Long parentId;

    @ApiModelProperty("父code")
    private String parentCode;

    @ApiModelProperty("路径")
    private String parentCodePath;

    @ApiModelProperty("code路径")
    private String parentPath;

    public SysGlobalRegionParentBO() {
    }

    public SysGlobalRegionParentBO(Long parentId, String parentCode, String parentCodePath, String parentPath) {
        this.parentId = parentId;
        this.parentCode = parentCode;
        this.parentCodePath = parentCodePath;
        this.parentPath = parentPath;
    }
}
