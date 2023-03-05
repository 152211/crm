package com.hworld.vo.req.hdata.common;

import lombok.Data;

import java.util.List;

/**
 * 中国省份
 */
@Data
public class CommonProvinceInfoVO {

    private Long id;

    private String source;

    private String nationNo;

    private String provinceNo;

    private String provinceName;

    private String provinceNameEn;

    private String allPinyin;

    private String shortPinyin;

    private String shortName;

    private Integer geoAreaId;

    private String shortNameEn;

    private Byte status;

    private Byte countryArea;

    private Boolean isDelete;

    private List<CommonProvinceInfoLanguageVO> commonProvinceInfoLanguageVOList;

}