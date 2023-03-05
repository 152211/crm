package com.hworld.enums;

public enum ESMsgTypeEnum {
    ADD("ADD", "新增"),
    UPDATE("UPDATE", "修改"),
    UPDATE_DELETEDFLAG("UPDATE_DELETEDFLAG", "伪删除"),
    DELETE("DELETE", "物理删除"),
    ;

    ESMsgTypeEnum(String msgType, String msgName) {
        this.msgType = msgType;
        this.msgName = msgName;
    }

    public String getMsgType() {
        return msgType;
    }

    public String getMsgName() {
        return msgName;
    }

    /**
     * 主题
     */
    private String msgType;

    /**
     * 主题名称
     */
    private String msgName;
}
