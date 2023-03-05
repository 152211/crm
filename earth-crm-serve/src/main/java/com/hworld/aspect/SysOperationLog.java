package com.hworld.aspect;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * @Description:
 * @Date 2021/9/10
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Data
public class SysOperationLog {

    private Integer id;

    //操作方法
    private String operationMethod;

    //请求url
    private String url;

    //操作简称
    private String operationDesc;

    //参数
    private String parameter;

    //ip
    private String ip;

    //耗时
    private Integer timeConsuming;

    //操作时间
    private String operationTime;

    //日志类型 1:正常操作日志 2:错误日志
    private byte logType;

    //错误日志msg
    private String errorLogMsg;

    //操作类型
    private String operationType;
    
}