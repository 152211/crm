package com.hworld.vo.req.hdata.international;

import lombok.Data;

/**
 *洲 ，国家 ，州 ，国际城市 多语言
 */
@Data
public class InternationalRegionsLanguageVO {

    private String languageCode;

    private String itemId;

    private String itemName;

    private String itemFullName;

    private Boolean isDelete;
}