package com.hworld.vo.resp.sys;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Create;
import com.hworld.annotation.Update;
import com.hworld.vo.req.BaseReqVO;
import com.hworld.vo.resp.BaseRespVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 用户权限对应关系DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:13
 */
@ApiModel("用户权限对应关系")
@Getter
@Setter
@ToString
public class SysUserPermissionRespVO extends BaseRespVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 权限id
     */
    @ApiModelProperty("权限id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long permissionId;

    /**
     * 权限code
     */
    @ApiModelProperty("权限code")
    private String permissionCode;
}
