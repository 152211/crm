package com.hworld.exception;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientException;

@ApiModel("http异常")
@Getter
@Setter
@ToString
@Slf4j
public class HttpException extends RestClientException {
    private String url;
    private int code;
    private String responseContent;

    public HttpException(String msg) {
        super(msg);
    }

    public HttpException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public HttpException(String url, String msg, int code, String responseContent) {
        super(msg);
        this.url = url;
        this.code = code;
        this.responseContent = responseContent;
    }
}