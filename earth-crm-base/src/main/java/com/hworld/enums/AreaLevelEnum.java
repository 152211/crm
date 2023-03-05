package com.hworld.enums;

public enum AreaLevelEnum {
    CONTINENT(1, "大陆/洲", "continent", "Continent"),
    COUNTRY(2, "国家", "country", "Country"),
    PROVINCE(3, "省/州", "province", "State"),
    CITY(4, "城市", "city", "City");


    AreaLevelEnum(Integer areaLevel, String areaLevelNameCn, String areaLevelNameEn, String areaLevelCode) {
        this.areaLevel = areaLevel;
        this.areaLevelNameCn = areaLevelNameCn;
        this.areaLevelNameEn = areaLevelNameEn;
        this.areaLevelCode = areaLevelCode;
    }

    public Integer getAreaLevel() {
        return areaLevel;
    }

    public String getAreaLevelNameCn() {
        return areaLevelNameCn;
    }

    public String getAreaLevelNameEn() {
        return areaLevelNameEn;
    }

    public String getAreaLevelCode() {
        return areaLevelCode;
    }

    /**
     * 地区层级
     */
    private Integer areaLevel;

    /**
     * 地区层级名称
     */
    private String areaLevelNameCn;

    /**
     * 地区层级名称
     */
    private String areaLevelNameEn;

    /**
     * 地区层级名称
     */
    private String areaLevelCode;
}
