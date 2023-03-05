package com.hworld.vo.req.member;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Create;
import com.hworld.annotation.Update;
import com.hworld.vo.req.BaseReqVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * 会员计划于酒店关联关系DTO
 *
 * @author caoyang
 * @date 2022-02-15 17:26:23
 */
@ApiModel("会员计划于酒店关联关系")
@Getter
@Setter
@ToString
public class MemberProgramHotelReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "会员等级名称不能为空", groups = {Update.class})
    private Long id;
    /**
     * 会员计划ID
     */
    @ApiModelProperty("会员计划ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long memberProgramId;
    /**
     * 酒店ID
     */
    @ApiModelProperty("酒店ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long hotelId;

    /**
     * 酒店code
     */
    @ApiModelProperty("酒店code")
    private String hotelCode;
}
