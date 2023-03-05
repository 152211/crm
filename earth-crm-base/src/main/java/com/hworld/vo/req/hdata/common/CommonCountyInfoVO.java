package com.hworld.vo.req.hdata.common;

import lombok.Data;

import java.util.List;
/**
 * 中国区县
 */
@Data
public class CommonCountyInfoVO {

    private Long id;

    private String source;

    private String cityNo;

    private String countyNo;

    private String allPinyin;

    private String countyName;

    private String countyNameEn;

    private String shortPinyin;

    private String shortName;

    private String countyChildLevel;

    private String countyFirstLevel;

    private String developCategory;

    private String developSubCategory;

    private String shortNameEn;

    private String countyCity;

    private String countyCityType;

    private Byte status;

    private Boolean isDelete;

    private List<CommonCountyInfoLanguageVO> commonCountyInfoLanguageVOList;

}