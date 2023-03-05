package com.hworld.enums.sys;

/**
 * sys_store.type:酒店类型枚举
 */
public enum HotelTypeEnum {
    ROOT("R", "根节点"),
    AREA("A", "区域"),
    BRAND("B", "品牌"),
    STORE("S", "门店");

    /**
     * 权限类型
     */
    private String hotelType;

    /**
     * 权限类型名称
     */
    private String hotelName;

    HotelTypeEnum(String hotelType, String hotelName) {
        this.hotelType = hotelType;
        this.hotelName = hotelName;
    }

    public String getHotelType() {
        return hotelType;
    }

    public String getHotelName() {
        return hotelName;
    }
}
