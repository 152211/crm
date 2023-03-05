package com.hworld.vo.req.hdata.hotel;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class HotelRoomBedVO {

    private Long id;

    private String hotelNo;

    private String typeCode;

    private String bedTypeCode;

    private String bedSpecNo;

    private Integer bedNum;

    private Boolean main;

    private Boolean isDelete;

    private Date createTime;

    private Date modifyTime;

    private List<HotelRoomBedLanguageVO> languageList;

}
