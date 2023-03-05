package com.hworld.constants;

//定义个常量类
public class RedisConstants {

    //定义redis过期时间
    public static final int REDIS_EXPIRE = 600;

    //定义redis过期时间
    public static final int REDIS_EXPIRE_ONE_HOUR = 3600;

    //定义redis刷新时间
    public static final int REDIS_REFRESH = 600;

    //定义sso AuthenticationInfo前缀
    public static final String SSO_AUTH_TOKEN_PREFIX = "sso_auth_token_prefix:";

    //定义用户key前缀
    public static final String CRM_USER = "user:";

    //定义用户权限code信息前缀
    public static final String CRM_USER_PERMISSION = "user:permission:";

    //定义用户权限code信息前缀
    public static final String CRM_USER_PERMISSION_TREE = "user:permission_tree:";

    //定义用户权限code信息前缀
    public static final String CRM_USER_ROUTE = "user:route:";

    //定义用户角色code前缀
    public static final String CRM_USER_ROLE = "user:role:";

    //定义用户酒店缓存key前缀
    public static final String CRM_USER_HOTEL = "user:hotel:";

    //定义用户角色code前缀
    public static final String CRM_USER_ROLE_INFO = "user:role_info:";

    //定义所有权限ket
    public static final String CRM_PERMISSION_ALL = "permission_all";
}
