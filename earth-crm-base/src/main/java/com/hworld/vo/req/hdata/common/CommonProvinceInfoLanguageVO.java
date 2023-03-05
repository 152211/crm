package com.hworld.vo.req.hdata.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 中国省份多语言
 */
@Data
@Accessors(chain = true)
public class CommonProvinceInfoLanguageVO {

    private String languageCode;

    private String provinceNo;

    private String provinceName;

    private String shortName;

    private Boolean isDelete;


}