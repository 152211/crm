package com.hworld.enums.sys;

public enum IconEnum {
    star("el-icon-star-on"),
    menu("el-icon-menu"),
    warning("el-icon-warning");


    /**
     * 权限类型
     */
    private String tcon;

    IconEnum(String tcon) {
        this.tcon = tcon;
    }

    public String getTcon() {
        return tcon;
    }
}
