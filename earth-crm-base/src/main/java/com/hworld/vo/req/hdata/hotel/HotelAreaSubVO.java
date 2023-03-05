package com.hworld.vo.req.hdata.hotel;

import lombok.Data;

import java.util.List;
/**
 * 分区信息
 */
@Data
public class HotelAreaSubVO {
    private Long id;
    private String subAreaNumber;

    private String regionNumber;

    private String regionName;

    private Byte status;

    private Boolean isDelete;

    private List<HotelAreaSubLanguageVO> hotelAreaSubLanguageVOList;
}
