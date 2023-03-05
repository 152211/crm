package com.hworld.enums.sys;

import io.swagger.annotations.ApiModelProperty;

/**
 * sys_permission.permission_type:权限类型枚举
 */
public enum PermissionTypeEnum {
    GROUP("G", "根目录"),
    CHILD("C", "子目录"),
    MENU("M", "菜单页面权限"),
    FEATURES("F", "功能权限");

    /**
     * 权限类型
     */
    private String permissionType;

    /**
     * 权限类型名称
     */
    private String permissionName;

    PermissionTypeEnum(String permissionType, String permissionName) {
        this.permissionType = permissionType;
        this.permissionName = permissionName;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public String getPermissionName() {
        return permissionName;
    }
}
