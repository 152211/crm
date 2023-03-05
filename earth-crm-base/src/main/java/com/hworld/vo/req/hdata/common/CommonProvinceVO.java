package com.hworld.vo.req.hdata.common;

import lombok.Data;

import java.util.List;

/**
 * @author Geewill
 * @version 1.0.0
 * @ClassName CommonProvinceVO.java
 * @PackageName com.hzgroup.xxlJobClient.common.vo
 * @Description TODO
 * @createTime 2021年08月30日
 */
@Data
public class CommonProvinceVO {
    private Long id;

    private String source;

    private String nationNo;

    private String provinceNo;

    private String allPinyin;

    private String shortPinyin;

    private Integer geoAreaId;

    private Byte status;

    private Byte countryArea;

    private Boolean isDelete;

    private List<CommonProvinceInfoLanguageVO> commonProvinceInfoLanguageVOList;
}
