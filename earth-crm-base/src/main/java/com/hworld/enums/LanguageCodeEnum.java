package com.hworld.enums;

public enum LanguageCodeEnum {
    EN_US("en-us", "英语");

    LanguageCodeEnum(String languageCode, String languageName) {
        LanguageCode = languageCode;
        LanguageName = languageName;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public String getLanguageName() {
        return LanguageName;
    }

    /**
     * 语言编号
     */
    private String LanguageCode;

    /**
     * 语言名称
     */
    private String LanguageName;
}
