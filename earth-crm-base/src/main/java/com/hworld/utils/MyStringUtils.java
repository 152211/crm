package com.hworld.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.ml.job.config.DataDescription;

import java.math.BigDecimal;

@Slf4j
public class MyStringUtils {
    /**
     * 判断字符串是否为null
     *
     * @param param
     * @return
     */
    public static boolean isNullParam(String param) {
        if (param == null || param.length() == 0 || "null".equalsIgnoreCase(param) || param.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否不为null
     *
     * @param param
     * @return
     */
    public static boolean isNotNullParam(String param) {
        if (param == null || param.length() == 0 || "null".equalsIgnoreCase(param) || param.trim().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * stringToInteger
     *
     * @param val
     * @return
     */
    public static Integer stringToInteger(String val) {
        try {
            if (isNullParam(val)) {
                return null;
            }

            return Integer.parseInt(val);
        } catch (Exception e) {
            log.error("stringToInteger:error={}", e.getMessage());
            return null;
        }
    }

    /**
     * stringToInteger
     *
     * @param val
     * @return
     */
    public static Long stringToLong(String val) {
        try {
            if (isNullParam(val)) {
                return null;
            }
            return Long.parseLong(val);
        } catch (Exception e) {
            log.error("stringToInteger:error={}", e.getMessage());
            return null;
        }
    }

    /**
     * stringToBigDecimal
     *
     * @param val
     * @return
     */
    public static BigDecimal stringToBigDecimal(String val) {
        try {
            if (isNullParam(val)) {
                return null;
            }
            return new BigDecimal(val);
        } catch (Exception e) {
            log.error("stringToInteger:error={}", e.getMessage());
            return null;
        }
    }

    public static boolean isJsonStr(String str) {
        try {
            JSONObject jsonElement = JSONObject.parseObject(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
