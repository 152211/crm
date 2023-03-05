package com.hworld.vo.req.sys;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Create;
import com.hworld.annotation.Delelt;
import com.hworld.annotation.Update;
import com.hworld.vo.req.BaseReqVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import javax.validation.constraints.*;

/**
 * 用户角色对应关系DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:13
 */
@ApiModel("用户角色对应关系")
@Getter
@Setter
@ToString
public class SysUserRoleReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class})
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    @NotNull(message = "userId Can not be empty", groups = {Create.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 角色id
     */
    @ApiModelProperty("角色id")
    @NotNull(message = "roleId Can not be empty", groups = {Create.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    /**
     * 角色code
     */
    @ApiModelProperty("角色code")
    @Length(min = 0, max = 50, message = "roleCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "roleCode Can not be empty", groups = {Create.class})
    private String roleCode;
}
