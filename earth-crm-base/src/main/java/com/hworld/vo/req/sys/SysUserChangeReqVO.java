package com.hworld.vo.req.sys;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hworld.utils.LongListFormatSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 用户变更
 *
 * @author caoyang
 * @date 2022-01-18 12:31:10
 */
@ApiModel("用户变更")
@Getter
@Setter
@ToString
public class SysUserChangeReqVO extends SysUserReqVO {

    /**
     * 角色ID集合
     */
    @ApiModelProperty("角色ID集合")
    @JsonSerialize(using = LongListFormatSerializer.class)
    private List<Long> roleIdList;
}
