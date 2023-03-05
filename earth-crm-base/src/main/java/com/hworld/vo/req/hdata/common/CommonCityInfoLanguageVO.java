package com.hworld.vo.req.hdata.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 中国城市信息多语言
 */
@Data
@Accessors(chain = true)
public class CommonCityInfoLanguageVO {

    private String languageCode;

    private String cityNo;

    private String cityName;

    private String shortName;

    private Boolean isDelete;


}