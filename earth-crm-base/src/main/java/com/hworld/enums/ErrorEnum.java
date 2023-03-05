package com.hworld.enums;

public enum ErrorEnum {
    SUCCESS(200, "success!", "成功!"),
    REQUESR_ERROR(400, " requesr error!", "请求异常!"),
    UNAUTHORIZED(401, " unauthorized!", "未授权!"),
    REQUEST_PARAM_ERROR(402, " request param error!", "请求参数异常!"),
    REJECT_REQUEST(403, " reject request!", "拒绝请求!"),
    SERVICE_ERROR(500, " Service internal exception!", "服务内部异常!"),
    PERMISSION_ERROR(501, " Permission exception!", "权限异常!"),
    SYSTEM_ERROR(502, " System error!", "系统异常!"),
    NOT_EXIST_ERROR(503, " is not exist!", "不存在!"),
    USER_NOT_EXIST_ERROR(504, " user is not exist!", "用户不存在!"),
    PASSWORD_ERROR(505, " Incorrect password!", "密码不正确!"),
    EXIST_ERROR(506, " exist!", "已存在!"),
    EMPTY_ERROR(507, " cannot be empty!", "不能为空!"),
    UPDATED_ERROR(508, " cannot be updated!", "不能被更新!"),
    DELETE_SUB_ERROR(509, " Please delete the sub permission first!", "请先删除子节点!"),
    MESSAGE_STATUS_ERROR(510, " message status error!", "消息状态不对!"),
    JSON_ERROR(511, " not in json format!", "不是JSON格式!"),
    ES_SAVE_ERROR(512, " es save error!", "ES 保存数据失败!"),
    PERMISSION_TYPE_ERROR(513, " permission type error!", "权限类型不正确!");

    ErrorEnum() {

    }

    ErrorEnum(Integer code, String msgEn, String msgCn) {
        this.code = code;
        this.msgEn = msgEn;
        this.msgCn = msgCn;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsgEn() {
        return msgEn;
    }

    public void setMsgEn(String msgEn) {
        this.msgEn = msgEn;
    }

    public String getMsgCn() {
        return msgCn;
    }

    public void setMsgCn(String msgCn) {
        this.msgCn = msgCn;
    }

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误描述
     */
    private String msgEn;

    /**
     * 错误描述
     */
    private String msgCn;

}
