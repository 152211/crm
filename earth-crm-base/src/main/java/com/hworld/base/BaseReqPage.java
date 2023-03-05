package com.hworld.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色维护 Controller
 *
 * @author caoyang
 * @date 2022-01-18 12:31:10
 */
@Data
@ToString
@ApiModel("分页请求参数")
public class BaseReqPage<T> implements Serializable {

    @ApiModelProperty(value = "页码")
    private int page = 1;

    @ApiModelProperty(value = "行数")
    private int rows = 10;

    @ApiModelProperty(value = "请求参数")
    private T params;

    public BaseReqPage() {
    }

    public BaseReqPage(int page, int rows, T params) {
        this.page = page;
        this.rows = rows;
        this.params = params;
    }
}
