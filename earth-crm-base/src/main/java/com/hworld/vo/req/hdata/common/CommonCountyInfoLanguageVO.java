package com.hworld.vo.req.hdata.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 中国区县多语言
 */
@Data
@Accessors(chain = true)
public class CommonCountyInfoLanguageVO {

    private String languageCode;

    private String countyNo;

    private String countyName;

    private String shortName;

    private Boolean isDelete;


}