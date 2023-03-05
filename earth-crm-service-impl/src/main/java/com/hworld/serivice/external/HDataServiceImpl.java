package com.hworld.serivice.external;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.hworld.base.BaseResponse;
import com.hworld.constants.DatePatternConstant;
import com.hworld.entity.sys.SysHotelDO;
import com.hworld.enums.ErrorEnum;
import com.hworld.enums.HDataMasterDataCodeEnum;
import com.hworld.enums.LanguageCodeEnum;
import com.hworld.enums.sys.HotelTypeEnum;
import com.hworld.hotel.HotelBrandUpdateBO;
import com.hworld.hotel.HotelUpdateBO;
import com.hworld.service.api.external.HDataService;
import com.hworld.service.api.sys.SysHotelService;
import com.hworld.utils.DateUtil;
import com.hworld.utils.LocalDateTImeUtil;
import com.hworld.utils.MyBeanUtils;
import com.hworld.utils.MyStringUtils;
import com.hworld.vo.req.hdata.HDataDescriptionReqVO;
import com.hworld.vo.req.hdata.HDataReqVO;
import com.hworld.vo.req.hdata.hotel.*;
import com.hworld.vo.resp.hdata.HDataBaseRespVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * HData业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2022-07-25 16:26:20
 **/
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class HDataServiceImpl implements HDataService {

    @Autowired
    private SysHotelService sysHotelService;

    /**
     * Hdata推送数据处理
     *
     * @param reqVO
     * @return
     */
    @Override
    public HDataBaseRespVO pushMasterData(HDataReqVO reqVO) {
        HDataBaseRespVO response = checkHDataReqVO(reqVO);
        if (!ErrorEnum.SUCCESS.getCode().equals(response.getCode())) {
            return response;
        }
        String dataDescription = reqVO.getDataDescription();
        String dataContent = reqVO.getDataContent();

        HDataDescriptionReqVO description = JSON.parseObject(dataDescription, HDataDescriptionReqVO.class);
        String masterDataCode = description.getMasterDataCode();
        if (HDataMasterDataCodeEnum.HOTEL_BRAND.getMasterDataCode().equalsIgnoreCase(masterDataCode)) {
            //酒店品牌
            List<HotelBrandVO> hotelBrandList = JSON.parseArray(dataContent, HotelBrandVO.class);
            //保存酒店品牌数据
            response = updateOrInsertHotelBrand(hotelBrandList);
            return new HDataBaseRespVO(response.getCode(), response.getMessage(), description, response.getResponseDes());
        } else if (HDataMasterDataCodeEnum.HOTEL_BASIC_INFO.getMasterDataCode().equalsIgnoreCase(masterDataCode)) {
            //酒店基本信息
            List<HotelBasicInfoVO> hotelList = JSON.parseArray(dataContent, HotelBasicInfoVO.class);
            response = updateOrInsertHotel(hotelList);
            return new HDataBaseRespVO(response.getCode(), response.getMessage(), description, response.getResponseDes());
        } else {
            return HDataBaseRespVO.success(description);
        }
    }

    /**
     * 更新酒店数据
     *
     * @param
     * @return
     */
    public HDataBaseRespVO updateOrInsertHotel(List<HotelBasicInfoVO> hotelList) {
        if (CollectionUtils.isEmpty(hotelList)) {
            log.error("HDataService.checkUserReqVO.error message={}:", "hotelBrand:" + ErrorEnum.EMPTY_ERROR.getMsgCn());
            return HDataBaseRespVO.error(ErrorEnum.EMPTY_ERROR.getCode(), "hotelBrand:" + ErrorEnum.EMPTY_ERROR.getMsgEn());
        }

        HotelUpdateBO hotelUpdateBO = new HotelUpdateBO();
        //1.0.设置变更数据
        setUpdeteHDataHotel(hotelList, hotelUpdateBO);

        Map<String, SysHotelDO> newHotelMap = hotelUpdateBO.getNewHotelMap();
        Set<Long> hotelNoIsNullErrorSet = hotelUpdateBO.getHotelNoIsNullErrorSet();
        Set<Long> hotelNameIsNullErrorSet = hotelUpdateBO.getHotelNameIsNullErrorSet();
        Set<Long> hotelBrandIsNullErrorSet = hotelUpdateBO.getHotelBrandIsNullErrorSet();

        //设置反馈提示
        StringBuilder responseDes = new StringBuilder();
        if (!CollectionUtils.isEmpty(hotelNoIsNullErrorSet)) {
            responseDes.append("hotelNoIsNullErrorSet:酒店编号为空的数据:");
            for (Long id : hotelNoIsNullErrorSet) {
                responseDes.append(id).append(",");
            }
        }

        if (!CollectionUtils.isEmpty(hotelNameIsNullErrorSet)) {
            responseDes.append("hotelNameIsNullErrorSet:酒店英文名称为空的数据:");
            for (Long id : hotelNameIsNullErrorSet) {
                responseDes.append(id).append(",");
            }
        }
        if (!CollectionUtils.isEmpty(hotelBrandIsNullErrorSet)) {
            responseDes.append("hotelBrandIsNullErrorSet:酒店品牌为空或不存在的数据:");
            for (Long id : hotelBrandIsNullErrorSet) {
                responseDes.append(id).append(",");
            }
        }

        //1.1.异步执行删除
        Future<BaseResponse> deleltFuture = sysHotelService.deleltHotelBatchAsync(hotelUpdateBO.getDeleteHotelNoSet());

        if (CollectionUtils.isEmpty(newHotelMap)) {
            return HDataBaseRespVO.success(null, String.valueOf(responseDes));
        }

        //1.2.设置需要更新到数据库的酒店数据
        setDatabaseUpdeteHotel(hotelUpdateBO);
        List<SysHotelDO> addHotelList = hotelUpdateBO.getAddHotelList();
        List<SysHotelDO> updateHotelList = hotelUpdateBO.getUpdateHotelList();

        //1.3.异步添加酒店数据
        sysHotelService.addHotelBatchAsync(addHotelList);

        //1.4.异步修改酒店数据
        sysHotelService.updateHotelBatchAsync(updateHotelList);

        return HDataBaseRespVO.success(null, String.valueOf(responseDes));
    }


    /**
     * 更新酒店品牌数据
     *
     * @param hotelBrandList
     * @return
     */
    public HDataBaseRespVO updateOrInsertHotelBrand(List<HotelBrandVO> hotelBrandList) {
        if (CollectionUtils.isEmpty(hotelBrandList)) {
            log.error("HDataService.checkUserReqVO.error message={}:", "hotelBrand:" + ErrorEnum.EMPTY_ERROR.getMsgCn());
            return HDataBaseRespVO.error(ErrorEnum.EMPTY_ERROR.getCode(), "hotelBrand:" + ErrorEnum.EMPTY_ERROR.getMsgEn());
        }

        HotelBrandUpdateBO hotelBrandUpdateBO = new HotelBrandUpdateBO();
        //1.0.设置变更数据
        setUpdeteHDataHotelBrand(hotelBrandList, hotelBrandUpdateBO);

        Map<String, SysHotelDO> newHotelMap = hotelBrandUpdateBO.getNewHotelMap();
        Set<Long> brandNoIsNullErrorSet = hotelBrandUpdateBO.getBrandNoIsNullErrorSet();
        Set<Long> brandNameIsNullErrorSet = hotelBrandUpdateBO.getBrandNameIsNullErrorSet();

        //设置反馈提示
        StringBuilder responseDes = new StringBuilder();
        if (!CollectionUtils.isEmpty(brandNoIsNullErrorSet)) {
            responseDes.append("brandNoIsNullError:品牌编号为空的数据:");
            for (Long id : brandNoIsNullErrorSet) {
                responseDes.append(id).append(",");
            }
        }

        if (!CollectionUtils.isEmpty(brandNameIsNullErrorSet)) {
            responseDes.append("brandUsNameIsNullError:品牌英文名称为空的数据:");
            for (Long id : brandNameIsNullErrorSet) {
                responseDes.append(id).append(",");
            }
        }

        //1.1.异步执行删除
        Future<BaseResponse> deleltFuture = sysHotelService.deleltHotelBatchAsync(hotelBrandUpdateBO.getDeleteBrandNoSet());

        if (CollectionUtils.isEmpty(newHotelMap)) {
            return HDataBaseRespVO.success(null, String.valueOf(responseDes));
        }

        //1.2.设置需要更新到数据库的酒店数据
        setDatabaseUpdeteHotelBrand(hotelBrandUpdateBO);
        List<SysHotelDO> addHotelList = hotelBrandUpdateBO.getAddHotelList();
        List<SysHotelDO> updateHotelList = hotelBrandUpdateBO.getUpdateHotelList();

        //1.3.异步添加酒店数据
        sysHotelService.addHotelBatchAsync(addHotelList);

        //1.4.异步修改酒店数据
        sysHotelService.updateHotelBatchAsync(updateHotelList);

        return HDataBaseRespVO.success(null, String.valueOf(responseDes));
    }

    /**
     * vo转DO
     *
     * @param brandVO
     * @return
     */
    private SysHotelDO brandVOToHotelDo(HotelBrandVO brandVO, Set<Long> brandNameIsNullSet) {
        Future<SysHotelDO> sysRootHotelFuture = sysHotelService.getRootHotelAsync();
        SysHotelDO sysRootHote = null;
        //检查品牌name数据是否正常
        List<HotelBrandLanguageVO> hotelBrandLanguageVOList = brandVO.getLanguageList();
        if (CollectionUtils.isEmpty(hotelBrandLanguageVOList)) {
            brandNameIsNullSet.add(brandVO.getId());
        }

        SysHotelDO sysHotelDO = new SysHotelDO();

        //检查语言数据是否正常
        for (HotelBrandLanguageVO hotelBrandLanguageVO : hotelBrandLanguageVOList) {
            if (hotelBrandLanguageVO == null) {
                continue;
            }
            if (hotelBrandLanguageVO.getIsDelete()) {
                continue;
            }
            if (LanguageCodeEnum.EN_US.getLanguageCode().equalsIgnoreCase(hotelBrandLanguageVO.getLanguageCode())
                    && MyStringUtils.isNotNullParam(hotelBrandLanguageVO.getBrandName())) {
                sysHotelDO.setHotelName(hotelBrandLanguageVO.getBrandName());
                break;
            }
        }
        if (MyStringUtils.isNullParam(sysHotelDO.getHotelName())) {
            brandNameIsNullSet.add(brandVO.getId());
            return null;
        }
        sysHotelDO.setHotelCode(brandVO.getBrandNo());
        sysHotelDO.setHotelType(HotelTypeEnum.BRAND.getHotelType());
        sysHotelDO.setHotelStatus(brandVO.getStatus());
        sysHotelDO.setDeletedFlag(brandVO.getIsDelete());
        try {
            sysRootHote = sysRootHotelFuture.get();
        } catch (Exception e) {
            log.error("HDataService.brandVOToHotelDo:.error:{}", e);
        }
        if (sysRootHote == null || sysRootHote.getId() == null) {
            sysHotelDO.setParentId(1L);
        } else {
            sysHotelDO.setParentId(sysRootHote.getId());
        }
        return sysHotelDO;
    }

    /**
     * vo转DO
     *
     * @param hotelBasicInfoVO
     * @param hotelUpdateBO
     * @return
     */
    private SysHotelDO hDataHotelVOToHotelDo(HotelBasicInfoVO hotelBasicInfoVO, HotelUpdateBO hotelUpdateBO) {

        Set<Long> hotelNameIsNullErrorSet = CollectionUtils.isEmpty(hotelUpdateBO.getHotelNameIsNullErrorSet()) ?
                new HashSet<>() : hotelUpdateBO.getHotelNameIsNullErrorSet();

        Set<Long> hotelBrandIsNullErrorSet = CollectionUtils.isEmpty(hotelUpdateBO.getHotelBrandIsNullErrorSet()) ?
                new HashSet<>() : hotelUpdateBO.getHotelBrandIsNullErrorSet();

        //验证品牌是否为空
        SysHotelDO sysHotelBrandDO = sysHotelService.getHoteByHotelCode(
                hotelBasicInfoVO.getBrandNo(), HotelTypeEnum.BRAND.getHotelType());
        if (sysHotelBrandDO == null) {
            hotelBrandIsNullErrorSet.add(hotelBasicInfoVO.getId());
            hotelUpdateBO.setHotelBrandIsNullErrorSet(hotelBrandIsNullErrorSet);
            return null;
        }

        //检查name数据是否正常
        List<HotelBasicInfoLanguageVO> hotelLanguageList = hotelBasicInfoVO.getLanguageList();
        if (CollectionUtils.isEmpty(hotelLanguageList)) {
            hotelNameIsNullErrorSet.add(hotelBasicInfoVO.getId());
            hotelUpdateBO.setHotelNameIsNullErrorSet(hotelBrandIsNullErrorSet);
            return null;
        }

        SysHotelDO sysHotelDO = new SysHotelDO();
        //检查语言数据是否正常
        for (HotelBasicInfoLanguageVO hotelLanguage : hotelLanguageList) {
            if (hotelLanguage == null) {
                continue;
            }
            if (hotelLanguage.getIsDelete()) {
                continue;
            }
            if (LanguageCodeEnum.EN_US.getLanguageCode().equalsIgnoreCase(hotelLanguage.getLanguageCode())
                    && MyStringUtils.isNotNullParam(hotelLanguage.getHotelName())) {
                sysHotelDO.setHotelName(hotelLanguage.getHotelName());
                break;
            }
        }
        if (MyStringUtils.isNullParam(sysHotelDO.getHotelName())) {
            hotelNameIsNullErrorSet.add(hotelBasicInfoVO.getId());
            hotelUpdateBO.setHotelNameIsNullErrorSet(hotelBrandIsNullErrorSet);
            return null;
        }

        //酒店联系信息
        HotelBasicInfoContactVO hotelBasicInfoContactVO = hotelBasicInfoVO.getHotelBasicInfoContactVo();
        HotelBasicInfoExtendVO hotelBasicInfoExtendVO = hotelBasicInfoVO.getHotelBasicInfoExtendVo();

        //拷贝基础数据
        MyBeanUtils.copyProperties(hotelBasicInfoContactVO, sysHotelDO);
        MyBeanUtils.copyProperties(hotelBasicInfoExtendVO, sysHotelDO);
        MyBeanUtils.copyProperties(hotelBasicInfoVO, sysHotelDO);

        sysHotelDO.setId(null);
        sysHotelDO.setHotelType(HotelTypeEnum.STORE.getHotelType());
        sysHotelDO.setParentId(sysHotelBrandDO.getId());
        sysHotelDO.setHotelCode(hotelBasicInfoVO.getHotelNo());
        sysHotelDO.setWhenBuilt(LocalDateTImeUtil.dateToLocalDateTime(DateUtil.parse(hotelBasicInfoVO.getWhenBuilt(), DatePatternConstant.YYYY_MM)));
        sysHotelDO.setFirstOpeningDate(LocalDateTImeUtil.dateToLocalDateTime(hotelBasicInfoVO.getFirstOpeningDate()));
        sysHotelDO.setTrialOpeningDate(LocalDateTImeUtil.dateToLocalDateTime(hotelBasicInfoVO.getTrialOpeningDate()));
        sysHotelDO.setOpeningDate(LocalDateTImeUtil.dateToLocalDateTime(hotelBasicInfoVO.getOpeningDate()));
        sysHotelDO.setReopeningDate(LocalDateTImeUtil.dateToLocalDateTime(hotelBasicInfoVO.getReopeningDate()));
        sysHotelDO.setOverhaulTime(LocalDateTImeUtil.dateToLocalDateTime(hotelBasicInfoVO.getOverhaulTime()));
        sysHotelDO.setClosureDate(LocalDateTImeUtil.dateToLocalDateTime(hotelBasicInfoVO.getClosureDate()));


        return sysHotelDO;
    }

    /**
     * 设置变更数据
     *
     * @param hotelList
     * @param hotelUpdateBO
     */
    private void setUpdeteHDataHotel(List<HotelBasicInfoVO> hotelList, HotelUpdateBO hotelUpdateBO) {
        if (hotelUpdateBO == null) {
            return;
        }
        Map<String, SysHotelDO> newHotelMap = CollectionUtils.isEmpty(hotelUpdateBO.getNewHotelMap()) ?
                new HashMap<>() : hotelUpdateBO.getNewHotelMap();
        Set<String> deleteHotelNoSet = CollectionUtils.isEmpty(hotelUpdateBO.getDeleteHotelNoSet()) ?
                new HashSet<>() : hotelUpdateBO.getDeleteHotelNoSet();
        Set<Long> hotelNoIsNullErrorSet = CollectionUtils.isEmpty(hotelUpdateBO.getHotelNoIsNullErrorSet()) ?
                new HashSet<>() : hotelUpdateBO.getHotelNoIsNullErrorSet();
        Set<Long> hotelBrandIsNullErrorSet = CollectionUtils.isEmpty(hotelUpdateBO.getHotelBrandIsNullErrorSet()) ?
                new HashSet<>() : hotelUpdateBO.getHotelBrandIsNullErrorSet();

        //1.0.过滤有问题的数据保留正常数据
        for (HotelBasicInfoVO hotelBasicInfoVO : hotelList) {
            //检查brandNo是否正常
            if (hotelBasicInfoVO == null) {
                continue;
            }
            if (MyStringUtils.isNullParam(hotelBasicInfoVO.getHotelNo())) {
                if (hotelBasicInfoVO.getId() != null) {
                    hotelNoIsNullErrorSet.add(hotelBasicInfoVO.getId());
                }
                continue;
            }
            if (MyStringUtils.isNullParam(hotelBasicInfoVO.getBrandNo())) {
                if (hotelBasicInfoVO.getId() != null) {
                    hotelBrandIsNullErrorSet.add(hotelBasicInfoVO.getId());
                }
                continue;
            }
            if (hotelBasicInfoVO.getIsDelete() == null || hotelBasicInfoVO.getIsDelete()) {
                deleteHotelNoSet.add(hotelBasicInfoVO.getHotelNo());
            } else {
                //vo转DO
                SysHotelDO sysHotelDO = hDataHotelVOToHotelDo(hotelBasicInfoVO, hotelUpdateBO);
                if (sysHotelDO != null) {
                    newHotelMap.put(hotelBasicInfoVO.getHotelNo(), sysHotelDO);
                }
            }
        }
        hotelUpdateBO.setNewHotelMap(newHotelMap);
        hotelUpdateBO.setDeleteHotelNoSet(deleteHotelNoSet);
        hotelUpdateBO.setHotelNoIsNullErrorSet(hotelNoIsNullErrorSet);
    }


    /**
     * 设置变更数据
     *
     * @param hotelBrandList
     * @param hotelBrandUpdateBO
     */
    private void setUpdeteHDataHotelBrand(List<HotelBrandVO> hotelBrandList, HotelBrandUpdateBO hotelBrandUpdateBO) {
        if (hotelBrandUpdateBO == null) {
            return;
        }
        Map<String, SysHotelDO> newHotelMap = CollectionUtils.isEmpty(hotelBrandUpdateBO.getNewHotelMap()) ?
                new HashMap<>() : hotelBrandUpdateBO.getNewHotelMap();
        Set<String> deleteBrandNoSet = CollectionUtils.isEmpty(hotelBrandUpdateBO.getDeleteBrandNoSet()) ?
                new HashSet<>() : hotelBrandUpdateBO.getDeleteBrandNoSet();
        Set<Long> brandNoIsNullErrorSet = CollectionUtils.isEmpty(hotelBrandUpdateBO.getBrandNoIsNullErrorSet()) ?
                new HashSet<>() : hotelBrandUpdateBO.getBrandNoIsNullErrorSet();
        Set<Long> brandNameIsNullErrorSet = CollectionUtils.isEmpty(hotelBrandUpdateBO.getBrandNameIsNullErrorSet()) ?
                new HashSet<>() : hotelBrandUpdateBO.getBrandNameIsNullErrorSet();

        //1.0.过滤有问题的数据保留正常数据
        for (HotelBrandVO hotelBrandVO : hotelBrandList) {
            //检查brandNo是否正常
            if (hotelBrandVO == null) {
                continue;
            }
            if (MyStringUtils.isNullParam(hotelBrandVO.getBrandNo())) {
                if (hotelBrandVO.getId() != null) {
                    brandNoIsNullErrorSet.add(hotelBrandVO.getId());
                }
                continue;
            }
            if (hotelBrandVO.getIsDelete() == null || hotelBrandVO.getIsDelete()) {
                deleteBrandNoSet.add(hotelBrandVO.getBrandNo());
            } else {
                //vo转DO
                SysHotelDO sysHotelDO = brandVOToHotelDo(hotelBrandVO, brandNameIsNullErrorSet);
                if (sysHotelDO != null) {
                    newHotelMap.put(hotelBrandVO.getBrandNo(), sysHotelDO);
                }
            }
        }
        hotelBrandUpdateBO.setNewHotelMap(newHotelMap);
        hotelBrandUpdateBO.setDeleteBrandNoSet(deleteBrandNoSet);
        hotelBrandUpdateBO.setBrandNoIsNullErrorSet(brandNoIsNullErrorSet);
        hotelBrandUpdateBO.setBrandNameIsNullErrorSet(brandNameIsNullErrorSet);
    }

    /**
     * 设置数据库需要变更的数据
     *
     * @param hotelBrandUpdateBO
     */
    private void setDatabaseUpdeteHotelBrand(HotelBrandUpdateBO hotelBrandUpdateBO) {
        Map<String, SysHotelDO> newHotelMap = CollectionUtils.isEmpty(hotelBrandUpdateBO.getNewHotelMap()) ?
                new HashMap<>() : hotelBrandUpdateBO.getNewHotelMap();

        if (CollectionUtils.isEmpty(newHotelMap)) {
            return;
        }
        //根据brandNoSet查询数据库中的品牌数据
        List<SysHotelDO> nowHotelDOList = sysHotelService.getHoteListByHotelCodeSet(newHotelMap.keySet(), HotelTypeEnum.BRAND.getHotelType());

        if (CollectionUtils.isEmpty(nowHotelDOList)) {
            hotelBrandUpdateBO.setAddHotelList(new ArrayList(newHotelMap.values()));
        }
        //转map方便求差集
        Map<String, SysHotelDO> nowHotelDOMap = nowHotelDOList.stream().collect(Collectors.toMap(item -> item.getHotelCode(), item -> item));
        MapDifference<String, SysHotelDO> difference = Maps.difference(newHotelMap, nowHotelDOMap);
        // 键相同但是值不同值映射项。返回的Map的值类型为MapDifference.ValueDifference，以表示左右两个不同的值
        Map<String, MapDifference.ValueDifference<SysHotelDO>> entriesDiffering = difference.entriesDiffering();
        List<SysHotelDO> updateHotelList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(entriesDiffering)) {
            for (MapDifference.ValueDifference<SysHotelDO> valueDifference : entriesDiffering.values()) {
                updateHotelList.add(valueDifference.leftValue());
            }
            hotelBrandUpdateBO.setUpdateHotelList(updateHotelList);
        }

        //newHotelMap中存在，nowHotelDOMap中不存在
        Map<String, SysHotelDO> addHoteMap = difference.entriesOnlyOnLeft();
        if (!CollectionUtils.isEmpty(addHoteMap)) {
            hotelBrandUpdateBO.setAddHotelList(new ArrayList(addHoteMap.values()));
        }
    }

    /**
     * 设置数据库需要变更的数据
     *
     * @param hotelUpdateBO
     */
    private void setDatabaseUpdeteHotel(HotelUpdateBO hotelUpdateBO) {
        Map<String, SysHotelDO> newHotelMap = CollectionUtils.isEmpty(hotelUpdateBO.getNewHotelMap()) ?
                new HashMap<>() : hotelUpdateBO.getNewHotelMap();

        if (CollectionUtils.isEmpty(newHotelMap)) {
            return;
        }
        //根据brandNoSet查询数据库中的品牌数据
        List<SysHotelDO> nowHotelDOList = sysHotelService.getHoteListByHotelCodeSet(newHotelMap.keySet(), HotelTypeEnum.BRAND.getHotelType());

        if (CollectionUtils.isEmpty(nowHotelDOList)) {
            hotelUpdateBO.setAddHotelList(new ArrayList(newHotelMap.values()));
        }
        //转map方便求差集
        Map<String, SysHotelDO> nowHotelDOMap = nowHotelDOList.stream().collect(Collectors.toMap(item -> item.getHotelCode(), item -> item));
        MapDifference<String, SysHotelDO> difference = Maps.difference(newHotelMap, nowHotelDOMap);
        // 键相同但是值不同值映射项。返回的Map的值类型为MapDifference.ValueDifference，以表示左右两个不同的值
        Map<String, MapDifference.ValueDifference<SysHotelDO>> entriesDiffering = difference.entriesDiffering();
        List<SysHotelDO> updateHotelList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(entriesDiffering)) {
            for (MapDifference.ValueDifference<SysHotelDO> valueDifference : entriesDiffering.values()) {
                updateHotelList.add(valueDifference.leftValue());
            }
            hotelUpdateBO.setUpdateHotelList(updateHotelList);
        }

        //newHotelMap中存在，nowHotelDOMap中不存在
        Map<String, SysHotelDO> addHoteMap = difference.entriesOnlyOnLeft();
        if (!CollectionUtils.isEmpty(addHoteMap)) {
            hotelUpdateBO.setAddHotelList(new ArrayList(addHoteMap.values()));
        }
    }

    /**
     * 检查参数有没有异常
     *
     * @param reqVO
     * @return
     */
    private HDataBaseRespVO checkHDataReqVO(HDataReqVO reqVO) {
        String dataDescription = reqVO.getDataDescription();
        String dataContent = reqVO.getDataContent();
        //验证是否为JSON格式字符
        HDataDescriptionReqVO description = JSON.parseObject(dataDescription, HDataDescriptionReqVO.class);
        if (StringUtils.isEmpty(description.getMasterDataCode())) {
            log.error("HDataService.checkUserReqVO.error message={}:", "MasterDataCode:" + ErrorEnum.EMPTY_ERROR.getMsgCn());
            return HDataBaseRespVO.error(ErrorEnum.EMPTY_ERROR.getCode(), "MasterDataCode:" + ErrorEnum.EMPTY_ERROR.getMsgEn(), description);
        }
        return HDataBaseRespVO.success();
    }
}
