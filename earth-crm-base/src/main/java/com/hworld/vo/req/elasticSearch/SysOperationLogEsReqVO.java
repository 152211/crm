package com.hworld.vo.req.elasticSearch;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hworld.vo.req.BaseReqVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * es 日志数据
 *
 * @author caoyang
 * @date 2022-02-08 16:00:49
 */
@ApiModel("es 日志数据")
@Getter
@Setter
@ToString
public class SysOperationLogEsReqVO  extends BaseReqVO {

    @ApiModelProperty("请求url")
    private String reqUrl;

    @ApiModelProperty("请求方式")
    private String reqMethod;

    @ApiModelProperty("请求方法路径")
    private String reqMethodPath;

    @ApiModelProperty("请求参数")
    private String reqParams;

    @ApiModelProperty("请求头信息")
    private String reqHeader;

    @ApiModelProperty("请求IP信息")
    private String reqIpAddres;

    @ApiModelProperty("请求时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reqTime;

    @ApiModelProperty("返回参数")
    private String respParams;

    @ApiModelProperty("响应时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = defaultTimeZone)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime respTime;

    @ApiModelProperty("操作耗时")
    private Long operationTime;

    @ApiModelProperty("日志类型")
    private String logType;

    @ApiModelProperty("异常信息")
    private String errorMsg;
}
