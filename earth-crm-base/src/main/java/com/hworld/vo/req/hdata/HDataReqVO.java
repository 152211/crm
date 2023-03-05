package com.hworld.vo.req.hdata;

import com.hworld.annotation.Create;
import com.hworld.annotation.Delelt;
import com.hworld.annotation.SelectOne;
import com.hworld.annotation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("Hdata推送数据内容")
@Getter
@Setter
@ToString
public class HDataReqVO implements Serializable {

    @ApiModelProperty("数据描述， HdataDescriptionReqVO对象")
    @NotNull(message = "dataDescription Can not be empty", groups = {Create.class, Update.class})
    private String dataDescription;

    @ApiModelProperty("数据内容, Json格式的字符串")
    @NotNull(message = "dataContent Can not be empty", groups = {Create.class, Update.class})
    private String dataContent;
}
