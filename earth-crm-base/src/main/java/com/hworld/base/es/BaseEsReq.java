package com.hworld.base.es;

import com.hworld.bo.es.EsBaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * 角色维护 Controller
 *
 * @author caoyang
 * @date 2022-01-18 12:31:10
 */
@Data
@ToString
@ApiModel("ES请求参数")
public class BaseEsReq extends EsBaseBO {
    @ApiModelProperty(value = "ES查询条件")
    private BoolQueryBuilder queryBuilder;

    public BaseEsReq() {
    }
}
