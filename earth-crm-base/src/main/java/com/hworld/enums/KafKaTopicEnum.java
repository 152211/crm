package com.hworld.enums;

import com.hworld.constants.Constants;

public enum KafKaTopicEnum {
    CRM_WORLD_LOG(Constants.CRM_WORLD_LOG, "系统日志"),
    CRM_WORLD_MEMBER(Constants.CRM_WORLD_MEMBER, "会员"),
    CRM_REQUEST_LOG(Constants.CRM_REQUEST_LOG, "请求日志"),
    ;

    KafKaTopicEnum() {

    }

    KafKaTopicEnum(String topic, String topicName) {
        this.topic = topic;
        this.topicName = topicName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    /**
     * 主题
     */
    private String topic;

    /**
     * 主题名称
     */
    private String topicName;
}
