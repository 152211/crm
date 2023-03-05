package com.hworld.constants;

//定义个常量类
public class RabbitConstants {

    //定义交换机
    public static final String EXCHANGE_TCLOUD_DINGTALK_NAME = "earth_crm_ex";
    //队列名称
    public static final String TCLOUD_DINGTALK_LOG_QUEUE = "earth_crm_queue";


    //死信队列
    public static final String DEAD_QUEUE = "earth_crm_dead_queue";
    public static final String DEAD_EXCHANGE = "earth_crm_dead_exchange";
    public static final String DEAD_ROUTE_KEY = "earth_crm_dead_route_key";
}