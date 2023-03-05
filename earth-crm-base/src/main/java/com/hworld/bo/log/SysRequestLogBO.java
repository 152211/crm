package com.hworld.bo.log;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * crm与第三方交互日志Entity
 *
 * @author Hbm-generator
 * @version 1.0
 * @date 2022-08-02 10:22:04
 **/
@Getter
@Setter
@ToString
public class SysRequestLogBO<T> implements Serializable {
    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 请求方式
     */
    private String httpMethod;

    /**
     * 请求方名称
     */
    private String requestName;

    /**
     * 请求URL
     */
    private String requestUrl;

    /**
     * 请求头信息
     */
    private String requestHeaders;

    /**
     * 请求body参数
     */
    private String requestBody;

    /**
     * 请求params参数
     */
    private String requestParams;

    /**
     * 响应时间 单位毫秒
     */
    private String responseTime;

    /**
     * 反馈参数
     */
    private String responseData;

    /**
     * 响应状态码 200:成功
     */
    private Integer responseStatus;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 备注
     */
    private String remark;

    public SysRequestLogBO() {

    }

    public SysRequestLogBO(String requestUrl, String requestHeaders, String requestBody, String requestParams) {
        this.requestUrl = requestUrl;
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
        this.requestParams = requestParams;
    }
}