package com.hworld.hotel;

import com.hworld.entity.sys.SysHotelDO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Hdata酒店品牌变化数据
 */
@ApiModel("Hdata酒店品牌变化数据")
@Getter
@Setter
@ToString
public class HotelUpdateBO {
    /**
     * 需要新增的酒店品牌数据
     */
    private Map<String, SysHotelDO> newHotelMap;

    /**
     * 需要删除的酒店品牌数据
     */
    private Set<String> deleteHotelNoSet;

    /**
     * 酒店code为空的异常数据
     */
    private Set<Long> hotelNoIsNullErrorSet;

    /**
     * 酒店名称为空的异常数据
     */
    private Set<Long> hotelNameIsNullErrorSet;

    /**
     * 酒店品牌为空的异常数据
     */
    private Set<Long> hotelBrandIsNullErrorSet;

    /**
     * 需要添加的酒店数据
     */
    private List<SysHotelDO> addHotelList;

    /**
     * 需要修改的酒店数据
     */
    private List<SysHotelDO> updateHotelList;
}
