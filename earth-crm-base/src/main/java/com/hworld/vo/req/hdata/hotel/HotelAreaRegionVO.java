package com.hworld.vo.req.hdata.hotel;

import lombok.Data;

import java.util.List;

/**
 * 大区信息
 */
@Data
public class HotelAreaRegionVO {
    private Long id;
    private String regionNumber;

    private String areaNumber;

    private String areaName;

    private Byte status;

    private Boolean isDelete;

    private List<HotelAreaRegionLanguageVO> hotelAreaRegionLanguageVOList;

}
