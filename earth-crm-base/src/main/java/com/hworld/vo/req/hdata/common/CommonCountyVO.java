package com.hworld.vo.req.hdata.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Geewill
 * @version 1.0.0
 * @ClassName CommonCountyVO.java
 * @PackageName com.hzgroup.xxlJobClient.common.vo
 * @Description TODO
 * @createTime 2021年08月31日
 */
@Data
@Accessors(chain = true)
public class CommonCountyVO {

    private Long id;

    private String source;

    private String cityNo;

    private String countyNo;

    private String allPinyin;

    private String shortPinyin;

    private String countyChildLevel;

    private String countyFirstLevel;

    private String developCategory;

    private String developSubCategory;

    private String countyCity;

    private String countyCityType;

    private Byte status;

    private Boolean isDelete;

    private List<CommonCountyInfoLanguageVO> commonCountyInfoLanguageVOList;
}
