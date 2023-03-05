package com.hworld.vo.req.hdata.hotel;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
/**
 * 事业部信息
 */
@Data
@Accessors(chain = true)
public class HotelAreaVO {
    private Long id;
    private String areaNumber;

    private String groupCode;

    private String groupName;

    private Byte status;

    private Boolean isDelete;

    private List<HotelAreaLanguageVO> hotelAreaLanguageList;
}

