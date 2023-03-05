package com.hworld.base.es;

import com.hworld.bo.es.EsBaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.io.Serializable;

/**
 * 角色维护 Controller
 *
 * @author caoyang
 * @date 2022-01-18 12:31:10
 */
@Data
@ToString
@ApiModel("ES分页请求参数")
public class BaseEsReqPage extends EsBaseBO {

    @ApiModelProperty(value = "页码")
    private int page = 1;

    @ApiModelProperty(value = "行数")
    private int rows = 20;

    @ApiModelProperty(value = "ES查询条件")
    private BoolQueryBuilder queryBuilder;

    public BaseEsReqPage() {
    }

    public BaseEsReqPage(int page, int rows, BoolQueryBuilder queryBuilder) {
        this.page = page;
        this.rows = rows;
        this.queryBuilder = queryBuilder;
    }
}
