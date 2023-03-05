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

import javax.validation.constraints.*;

/**
 * 用户所属酒店关系DTO
 *
 * @author caoyang
 * @date 2022-07-21 16:43:06
 */
@ApiModel("用户所属酒店关系")
@Getter
@Setter
@ToString
public class SysUserHotelReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class, SelectOne.class})
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "userId Can not be empty", groups = {Create.class})
    private Long userId;

    /**
     * 酒店ID
     */
    @ApiModelProperty("酒店ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long hotelId;

    /**
     * 酒店编号于hdata保持一致
     */
    @ApiModelProperty("酒店编号于hdata保持一致")
    @Length(min = 0, max = 50, message = "hotelNo length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String hotelNo;

    /**
     * 是否是管理员0:不是 1:是
     */
    @ApiModelProperty("是否是管理员0:不是 1:是")
    private Boolean isManager;
}
