package com.hworld.base.es;

import com.hworld.enums.ErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dlgu on 2016/3/11.
 */
@ApiModel(value = "分页返回对象", description = "该对象用于包装返回数据")
@Data
public class BaseEsPagedResponse<T> implements Serializable {

    @ApiModelProperty(value = "总条数")
    private Long totalCount;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty("返回对象")
    private List<T> data;

    public BaseEsPagedResponse() {

    }

    public BaseEsPagedResponse(Long totalCount, Integer code, String message, List<T> data) {
        this.totalCount = totalCount;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static BaseEsPagedResponse error(String message) {
        return new BaseEsPagedResponse(0L, ErrorEnum.SUCCESS.getCode(), message, null);
    }

    public static BaseEsPagedResponse error(Integer code, String message) {
        return new BaseEsPagedResponse(0L, code, message, null);
    }

    public static BaseEsPagedResponse error(Integer code, String message, List data) {
        return new BaseEsPagedResponse(0L, code, message, data);
    }


    public static BaseEsPagedResponse success(Long totalCount, String message, List data) {
        return new BaseEsPagedResponse(totalCount, ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsgEn(), data);
    }

    public static BaseEsPagedResponse success(Long totalCount, List data) {
        return new BaseEsPagedResponse(totalCount, ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsgEn(), data);
    }
}
