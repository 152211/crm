package com.hworld.enums;

public enum KafkaMsgStatusEnum {
    NO_SEND(-1, "未发送"),
    SEND_ERROR(0, "发送失败"),
    SEND_SUCCESS(1, "发送成功"),
    CONSUMER_ERROR(2, "消费失败"),
    CONSUMER_SUCCESS(3, "消费成功"),
    MESSAGE_ERROR(4, "无效的错误消息"),
    ;


    KafkaMsgStatusEnum(Integer msgStatus, String msgStatusName) {
        this.msgStatus = msgStatus;
        this.msgStatusName = msgStatusName;
    }

    public Integer getMsgStatus() {
        return msgStatus;
    }

    public String getMsgStatusName() {
        return msgStatusName;
    }

    /**
     * 状态
     */
    private Integer msgStatus;

    /**
     * 状态名称
     */
    private String msgStatusName;
}
