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
 * 系统配置DTO
 *
 * @author caoyang
 * @date 2022-02-08 16:00:49
 */
@ApiModel("系统配置")
@Getter
@Setter
@ToString
public class SysConfigReqVO extends BaseReqVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "id Can not be empty", groups = {Update.class, Delelt.class, SelectOne.class})
    private Long id;
    /**
     * 配置编码
     */
    @ApiModelProperty("配置编码")
    @Length(min = 0, max = 64, message = "configCode length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "configCode Can not be empty", groups = {Create.class, Update.class})
    private String configCode;
    /**
     * 配置值
     */
    @ApiModelProperty("配置值")
    @Length(min = 0, max = 64, message = "configValue length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "configValue Can not be empty", groups = {Create.class, Update.class})
    private String configValue;
    /**
     * 英文名称
     */
    @ApiModelProperty("英文名称")
    @Length(min = 0, max = 255, message = "configNameEn length must in [{min},{max}]", groups = {Create.class, Update.class})
    @NotNull(message = "configNameEn Can not be empty", groups = {Create.class, Update.class})
    private String configNameEn;
    /**
     * 中文名称
     */
    @ApiModelProperty("中文名称")
    @Length(min = 0, max = 255, message = "configNameCn length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String configNameCn;
    /**
     * 描述
     */
    @ApiModelProperty("描述")
    @Length(min = 0, max = 255, message = "description length must in [{min},{max}]", groups = {Create.class, Update.class})
    private String description;
}
