package com.hworld.enums;

public enum HDataMasterDataCodeEnum {
    HOTEL_BASIC_INFO("HotelBasicInfo","酒店基础数据"),
    HOTEL_BRAND("HotelBrand","酒店品牌数据");


    HDataMasterDataCodeEnum(String masterDataCode, String masterDataName) {
        this.masterDataCode = masterDataCode;
        this.masterDataName = masterDataName;
    }

    public String getMasterDataCode() {
        return masterDataCode;
    }

    public String getMasterDataName() {
        return masterDataName;
    }

    /**
     * 主数据Code 表名
     */
    private String masterDataCode;

    /**
     * 主数据中文含义
     */
    private String masterDataName;
}
