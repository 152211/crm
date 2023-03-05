package com.hworld.http;

import com.hworld.utils.MyStringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class WebContext {
    private static ThreadLocal<WebContextInfo> contextInfo = new ThreadLocal<>();

    /**
     * 设置参数
     *
     * @param userId
     * @param userName
     */
    public static void set(Long userId, String userName) {
        WebContextInfo info = new WebContextInfo();
        info.setUserId(userId);
        info.setUserName(userName);
        contextInfo.set(info);
    }

    public static void remove() {
        contextInfo.remove();
    }

    /**
     * @return
     */
    public static Long getUserId() {
        return Objects.isNull(contextInfo.get()) ? 0L : contextInfo.get().getUserId() == null ? 0L : contextInfo.get().getUserId();
    }

    /**
     * @return
     */
    public static String getUserName() {
        return Objects.isNull(contextInfo.get()) ? null : MyStringUtils.isNullParam(contextInfo.get().getUserName()) ? null : contextInfo.get().getUserName();
    }

    @Setter
    @Getter
    @ApiModel("WebContextInfo")
    @ToString
    public static class WebContextInfo implements Serializable {

        @ApiModelProperty("用户iD")
        private Long userId;

        @ApiModelProperty("用户姓名")
        private String userName;
    }
}
