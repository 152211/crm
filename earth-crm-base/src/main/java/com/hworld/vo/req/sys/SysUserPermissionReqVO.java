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
 * 用户权限对应关系DTO
 *
 * @author caoyang
 * @date 2022-01-18 12:31:13
 */
@ApiModel("用户权限对应关系")
@Getter
@Setter
@ToString
public class SysUserPermissionReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class})
    private Long id;
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "userId Can not be empty", groups = {Create.class, Update.class})
    private Long userId;

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
}
