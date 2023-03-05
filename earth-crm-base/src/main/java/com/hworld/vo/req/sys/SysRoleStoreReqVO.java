package com.hworld.vo.req.sys;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Create;
import com.hworld.annotation.Delelt;
import com.hworld.annotation.SelectOne;
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
 * 角色酒店对应关系DTO
 *
 * @author caoyang
 * @date 2022-02-08 16:00:53
 */
@ApiModel("角色酒店对应关系")
@Getter
@Setter
@ToString
public class SysRoleStoreReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class, SelectOne.class})
    private Long id;
    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    @NotNull(message = "roleId Can not be empty",  groups = {Create.class, Update.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;
    /**
     * 角色code
     */
    @ApiModelProperty("角色code")
    @Length(min = 0, max = 50, message = "roleCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "roleCode Can not be empty",  groups = {Create.class, Update.class})
    private String roleCode;
    /**
     * 酒店ID
     */
    @ApiModelProperty("酒店ID")
    @NotNull(message = "storeId Can not be empty",  groups = {Create.class, Update.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long storeId;
    /**
     * 酒店编号
     */
    @ApiModelProperty("酒店编号")
    @Length(min = 0, max = 50, message = "storeNo length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "storeNo Can not be empty",  groups = {Create.class, Update.class})
    private String storeNo;
    /**
     * R-根节点,G-总部部门,A/B-品牌,S-门店
     */
    @ApiModelProperty("R-根节点,G-总部部门,A/B-品牌,S-门店")
    @Length(min = 0, max = 10, message = "storeType length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "storeType Can not be empty",  groups = {Create.class, Update.class})
    private String storeType;
    /**
     * 酒店名称
     */
    @ApiModelProperty("酒店名称")
    @Length(min = 0, max = 200, message = "storeName length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "storeName Can not be empty",  groups = {Create.class, Update.class})
    private String storeName;
}
