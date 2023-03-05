package com.hworld.vo.req.hdata.common;

import lombok.Data;

import java.util.List;

/**
 * 中国城市信息
 */
@Data
public class CommonCityInfoVO {

    private Long id;

    private String source;

    private String provinceNo;

    private String cityNo;

    private String cityName;

    private String cityNameEn;

    private String allPinyin;

    private String shortPinyin;

    private String shortName;

    private String cityChildLevel;

    private String cityFirstLevel;

    private String shortNameEn;

    private Byte status;

    private Boolean isDelete;

    private List<CommonCityInfoLanguageVO> commonCityInfoLanguageVOList;

}