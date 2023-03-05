package com.hworld.utils;

import com.hworld.bo.log.SysRequestLogBO;
import com.hworld.exception.HttpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * RestTemplate 远程调用工具类
 *
 * @author wangzhy
 * @createDate 2019-11-28
 */
@Service
@Slf4j
public class RestTemplateUtil {

    @Autowired
    private RestTemplate restTemplate;

    // ----------------------------------GET-------------------------------------------------------

    /**
     * GET请求调用方式
     *
     * @param url          请求URL
     * @param responseType 返回对象类型
     * @param params       URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> get(String url, Class<T> responseType, Map<String, ?> params) {
        try {
            if (CollectionUtils.isEmpty(params)) {
                return restTemplate.getForEntity(url, responseType);
            }
            url = getUrl(url, params);
            ResponseEntity<T> response = restTemplate.getForEntity(url, responseType, params);
            if (200 != response.getStatusCodeValue()) {
                throw new HttpException(url, "接口调用失败", response.getStatusCodeValue(), response.toString());
            }
            return response;
        } catch (RestClientResponseException e) {
            throw new HttpException(url, "接口调用失败", e.getRawStatusCode(), e.getResponseBodyAsString());
        }
    }

    /**
     * 带请求头的GET请求调用方式
     *
     * @param url          请求URL
     * @param headers      请求头参数
     * @param responseType 返回对象类型
     * @param params       URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> get(String url, Map<String, String> headers, Class<T> responseType, Map<String, ?> params) {
        try {
            url = getUrl(url, params);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAll(headers);

            HttpEntity<?> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType, params);

            if (200 != response.getStatusCodeValue()) {
                throw new HttpException(url, "接口调用失败", response.getStatusCodeValue(), response.toString());
            }
            return response;
        } catch (RestClientResponseException e) {
            throw new HttpException(url, "接口调用失败", e.getRawStatusCode(), e.getResponseBodyAsString());
        }
    }


// ----------------------------------POST-------------------------------------------------------

    /**
     * POST请求调用方式
     *
     * @param url          请求URL
     * @param responseType 返回对象类型
     * @return
     */
    public <T> ResponseEntity<T> post(String url, Class<T> responseType) {
        try {
            return restTemplate.postForEntity(url, HttpEntity.EMPTY, responseType);
        } catch (RestClientResponseException e) {
            throw new HttpException(url, "接口调用失败", e.getRawStatusCode(), e.getResponseBodyAsString());
        }
    }

    /**
     * POST请求调用方式
     *
     * @param url          请求URL
     * @param requestBody  请求参数体
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType) {
        try {
            return restTemplate.postForEntity(url, requestBody, responseType);
        } catch (RestClientResponseException e) {
            throw new HttpException(url, "接口调用失败", e.getRawStatusCode(), e.getResponseBodyAsString());
        }
    }

    /**
     * POST请求调用方式
     *
     * @param url          请求URL
     * @param requestBody  请求参数体
     * @param responseType 返回对象类型
     * @param params       URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType, Map<String, ?> params) {
        try {
            return restTemplate.postForEntity(url, requestBody, responseType, params);
        } catch (RestClientResponseException e) {
            throw new HttpException(url, "接口调用失败", e.getRawStatusCode(), e.getResponseBodyAsString());
        }
    }

    /**
     * 带请求头的POST请求调用方式
     *
     * @param url          请求URL
     * @param headers      请求头参数
     * @param requestBody  请求参数体
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> post(String url, Map<String, String> headers, Object requestBody, Class<T> responseType) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAll(headers);

            HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, httpHeaders);
            return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
        } catch (RestClientResponseException e) {
            throw new HttpException(url, "接口调用失败", e.getRawStatusCode(), e.getResponseBodyAsString());
        }
    }


    /**
     * 带请求头的POST请求调用方式
     *
     * @param url          请求URL
     * @param headers      请求头参数
     * @param requestBody  请求参数体
     * @param responseType 返回对象类型
     * @param params       URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> post(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Map<String, ?> params) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAll(headers);

            HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, httpHeaders);
            return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType, params);
        } catch (RestClientResponseException e) {
            throw new HttpException(url, "接口调用失败", e.getRawStatusCode(), e.getResponseBodyAsString());
        }
    }

    // ----------------------------------通用方法-------------------------------------------------------
    public String getUrl(String url, Map<String, ?> params) {
        if (CollectionUtils.isEmpty(params)) {
            return url;
        }
        url = url.concat("?");
        for (String key : params.keySet()) {
            if (params.get(key) == null || MyStringUtils.isNullParam(String.valueOf(params.get(key)))) {
                continue;
            }
            url = url.concat(key).concat("={").concat(key).concat("}&");
        }
        url = url.substring(0, url.length() - 1);
        return url;
    }
}