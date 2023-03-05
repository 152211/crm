package com.hworld.vo.req.hdata.international;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *洲 ，国家 ，州 ，国际城市
 */
@Data
public class InternationalRegionsVO implements Serializable {

    private Long id;

    private String source;

    private String itemId;

    private String parentId;

    private String itemCode;

    private String itemLevelCode;

    private String itemLongCode;

    private String itemName;

    private String itemFullName;

    private String itemEnName;

    private String itemEnFullName;

    private String countryArea;

    private Byte status;

    private Boolean isDelete;

    private List<InternationalRegionsLanguageVO> internationalRegionsLanguageVOList;

}