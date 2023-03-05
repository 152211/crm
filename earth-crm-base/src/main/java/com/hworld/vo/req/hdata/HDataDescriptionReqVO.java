package com.hworld.vo.req.hdata;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hworld.annotation.Delelt;
import com.hworld.annotation.Update;
import com.hworld.vo.req.hdata.page.PageInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ApiModel("Hdata推送数据描述")
@Getter
@Setter
@ToString
public class HDataDescriptionReqVO implements Serializable {

    @ApiModelProperty("集团code HuaZhu")
    private String groupCode;

    @ApiModelProperty("系统code pms2020")
    private String sysCode;

    @ApiModelProperty("主数据所属源集团code")
    private String masterDataGroupCode;

    @ApiModelProperty("主数据Code 表名")
    private String MasterDataCode;

    @ApiModelProperty("分页信息对象")
    private PageInfoVO pageInfo;
}
