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
 * 系统字典分组DTO
 *
 * @author caoyang
 * @date 2022-02-08 16:00:50
 */
@ApiModel("系统字典分组")
@Getter
@Setter
@ToString
public class SysDictGroupReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class, SelectOne.class})
    private Long id;
    /**
     * 分组编码
     */
    @ApiModelProperty("分组编码")
    @Length(min = 0, max = 64, message = "groupCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "groupCode Can not be empty", groups = {Create.class, Update.class})
    private String groupCode;
    /**
     * 分组英文名称
     */
    @ApiModelProperty("分组英文名称")
    @Length(min = 0, max = 255, message = "groupNameEn length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "groupNameEn Can not be empty", groups = {Create.class, Update.class})
    private String groupNameEn;
    /**
     * 分组中文名称
     */
    @ApiModelProperty("分组中文名称")
    @Length(min = 0, max = 255, message = "groupNameCn length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String groupNameCn;
    /**
     * 描述
     */
    @ApiModelProperty("描述")
    @Length(min = 0, max = 255, message = "description length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String description;

}
