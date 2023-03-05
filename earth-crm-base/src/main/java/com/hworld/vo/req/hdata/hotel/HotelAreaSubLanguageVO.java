package com.hworld.vo.req.hdata.hotel;

import lombok.Data;

/**
 * 分区信息多语言
 */
@Data
public class HotelAreaSubLanguageVO {
    private String languageCode;

    private String subAreaNumber;

    private String subAreaName;

    private Boolean isDelete;

}

