package com.hworld.vo.resp.hdata;

import com.hworld.base.BaseResponse;
import com.hworld.enums.ErrorEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.HttpServletResponse;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class HDataBaseRespVO<T> {

    @ApiModelProperty(value = "结果码:200成功 其他情况参考码表", example = "200")
    public Integer code;

    @ApiModelProperty("失败时描述信息")
    public String message;

    @ApiModelProperty("若失败时，返回DataDescription")
    public T content;

    @ApiModelProperty("响应描述")
    public String responseDes;

    private HDataBaseRespVO() {
    }

    public HDataBaseRespVO(Integer code, String message, T content, String responseDes) {
        this.code = code;
        this.message = message;
        this.content = content;
        this.responseDes = responseDes;
    }

    public static HDataBaseRespVO error(String message) {
        return new HDataBaseRespVO(ErrorEnum.SERVICE_ERROR.getCode(), message, null, null);
    }

    public static HDataBaseRespVO error(Integer code, String message) {
        return new HDataBaseRespVO(code, message, null, null);
    }

    public static HDataBaseRespVO error(Integer code, String message, Object data) {
        return new HDataBaseRespVO(code, message, data, null);
    }

    public static HDataBaseRespVO success() {
        return new HDataBaseRespVO(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsgEn(), null, null);
    }

    public static HDataBaseRespVO success(Object data, String responseDes) {
        return new HDataBaseRespVO(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsgEn(), data, responseDes);
    }

    public static HDataBaseRespVO success(Object data) {
        return new HDataBaseRespVO(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsgEn(), data, ErrorEnum.SUCCESS.getMsgEn());
    }
}
