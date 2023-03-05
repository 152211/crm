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
 * 系统字典选项DTO
 *
 * @author caoyang
 * @date 2022-02-08 16:00:52
 */
@ApiModel("系统字典选项")
@Getter
@Setter
@ToString
public class SysDictItemReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class, SelectOne.class})
    private Long id;
    /**
     * 分组ID
     */
    @ApiModelProperty("分组ID")
    @Length(min = 0, max = 64, message = "groupId length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "groupId Can not be empty", groups = {Create.class})
    @JsonSerialize(using = ToStringSerializer.class)
    private Long groupId;
    /**
     * 分组编码
     */
    @ApiModelProperty("分组编码")
    @Length(min = 0, max = 64, message = "groupCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "groupCode Can not be empty", groups = {Create.class})
    private String groupCode;
    /**
     * 选项编码
     */
    @ApiModelProperty("选项编码")
    @Length(min = 0, max = 64, message = "itemCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "itemCode Can not be empty", groups = {Create.class})
    private String itemCode;
    /**
     * 选项值
     */
    @ApiModelProperty("选项值")
    @Length(min = 0, max = 64, message = "itemValue length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "itemValue Can not be empty", groups = {Create.class})
    private String itemValue;
    /**
     * 英文名称
     */
    @ApiModelProperty("英文名称")
    @Length(min = 0, max = 255, message = "itemNameEn length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "itemNameEn Can not be empty", groups = {Create.class})
    private String itemNameEn;
    /**
     * 中文名称
     */
    @ApiModelProperty("中文名称")
    @Length(min = 0, max = 255, message = "itemNameCn length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String itemNameCn;
    /**
     * 序号
     */
    @ApiModelProperty("序号")
    @Max(value = 11, message = "orderNum length must less than {value}", groups = {Create.class, Update.class})
    @NotNull(message = "orderNum Can not be empty", groups = {Create.class})
    private Integer orderNum;
    /**
     * 描述
     */
    @ApiModelProperty("描述")
    @Length(min = 0, max = 255, message = "description length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String description;

}
