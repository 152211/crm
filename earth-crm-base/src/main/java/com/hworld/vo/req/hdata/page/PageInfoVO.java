package com.hworld.vo.req.hdata.page;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ApiModel("Hdata数据分页")
@Getter
@Setter
@ToString
public class PageInfoVO implements Serializable {

    //类型：1-全量 2-增量
    private Integer type;

    //分页数量
    private Integer pageSize;

    //分页起始下标
    private Long lastIndex;
}
