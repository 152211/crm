package com.hworld.constants;

public interface Constants {
    final Integer ZERO = 0;

    final Integer ONE = 1;

    final Integer TWO = 2;

    final Integer THREE = 3;

    final Integer four = 4;

    final Integer FIVE = 5;

    // 响应请求成功
    final String HTTP_RES_CODE_200_VALUE = "success";
    // 系统错误
    final String HTTP_RES_CODE_500_VALUE = "fail";
    // 响应请求成功code
    final Integer HTTP_RES_CODE_200 = 200;
    // 系统错误
    final Integer HTTP_RES_CODE_500 = 500;

    /**
     * redis token key
     */
    final String REDIS_KEY_DING_TALK_TOKEN = "DING_TALK_TOKEN_";

    /**
     * redis token key
     */
    final String REDIS_KEY_DING_TALK_CORP_TOKEN = "DING_TALK_CORP_TOKEN_";

    /**
     * redis suiteToken
     */
    final String REDIS_KEY_DING_TALK_SUITE_TOKEN = "REDIS_KEY_DING_TALK_SUITE_TOKEN_";

    /**
     * redis token key
     */
    final String REDIS_KEY_SUITE_KEY_TICKET = "REDIS_KEY_SUITE_KEY_TICKET_";

    /**
     * redis token key
     */
    final String REDIS_KEY_AUTH_CODE_SUITE_KEY = "REDIS_KEY_AUTH_CODE_SUITE_KEY_";

    /**
     * redis suiteToken
     */
    final String REDIS_KEY_DING_TALK_PERMANENT_CODE = "REDIS_KEY_DING_TALK_PERMANENT_CODE_";

    /**
     * redis suiteToken
     */
    final String REDIS_KEY_DING_TALK_TICKET = "REDIS_KEY_DING_TALK_TICKET_";

    /**
     * redis suiteToken
     */
    final String REDIS_KEY_DING_TALK_SSO_TOKEN = "REDIS_KEY_DING_TALK_SSO_TOKEN_";

    //第三方企业的授权信息
    final String REDIS_KEY_DING_TALK_CORP_AUTH_MSG = "redis_key_ding_talk_corp_auth_msg";

    final String CRM_WORLD_LOG = "crm-world-log";

    final String CRM_WORLD_MEMBER = "crm-world-member-test1";

    final String CRM_REQUEST_LOG = "crm-request-log";
}
