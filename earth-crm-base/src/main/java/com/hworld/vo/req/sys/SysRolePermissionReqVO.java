package com.hworld.vo.req.sys;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 角色权限对应关系DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:10
 */
@ApiModel("角色权限对应关系")
@Getter
@Setter
@ToString
public class SysRolePermissionReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class})
    private Long id;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "roleId Can not be empty", groups = {Create.class, Update.class})
    private Long roleId;

    /**
     * 角色code
     */
    @ApiModelProperty("角色code")
    @NotNull(message = "roleId Can not be empty", groups = {Create.class, Update.class})
    private String roleCode;

    /**
     * 权限id
     */
    @ApiModelProperty("权限id")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "permissionId Can not be empty", groups = {Create.class, Update.class})
    private Long permissionId;

    /**
     * 权限code
     */
    @ApiModelProperty("权限code")
    @Length(min = 0, max = 50, message = "permissionCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "permissionCode Can not be empty", groups = {Create.class, Update.class})
    private String permissionCode;

    /**
     * A允许 D禁止
     */
    @ApiModelProperty("A允许 D禁止")
    @Length(min = 0, max = 1, message = "authType length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "authType Can not be empty", groups = {Create.class, Update.class})
    private String authType;
}
