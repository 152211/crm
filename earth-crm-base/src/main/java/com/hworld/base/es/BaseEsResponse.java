package com.hworld.base.es;

import com.hworld.enums.ErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @version V1.0
 * @description: 微服务接口统一返回码
 * @author: 曹阳
 * @date: 2021年5月21日 下午3:03:17
 */
@Data
@ApiModel(value = "返回对象", description = "返回对象")
public class BaseEsResponse<T> implements Serializable {

    /**
     * 返回码
     */
    @ApiModelProperty(value = "返回码")
    private Integer code;
    /**
     * 消息
     */
    @ApiModelProperty(value = "返回信息")
    private String message;
    /**
     * 返回
     */
    @ApiModelProperty(value = "返回数据")
    private T data;
    // 分页

    public BaseEsResponse() {

    }

    public BaseEsResponse(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static BaseEsResponse error(String message) {
        return new BaseEsResponse(ErrorEnum.SERVICE_ERROR.getCode(), message, null);
    }

    public static BaseEsResponse error(Integer code, String message) {
        return new BaseEsResponse(code, message, null);
    }

    public static BaseEsResponse error(Integer code, String message, Object data) {
        return new BaseEsResponse(code, message, data);
    }

    public static BaseEsResponse success() {
        return new BaseEsResponse(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsgEn(), null);
    }

    public static BaseEsResponse success(String message, Object data) {
        return new BaseEsResponse(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsgEn(), data);
    }

    public static BaseEsResponse success(Object data) {
        return new BaseEsResponse(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsgEn(), data);
    }
}
