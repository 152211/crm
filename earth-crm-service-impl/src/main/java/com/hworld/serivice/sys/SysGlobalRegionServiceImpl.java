package com.hworld.serivice.sys;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.base.BaseResponse;
import com.hworld.bo.sys.SysGlobalRegionParentBO;
import com.hworld.entity.sys.SysGlobalRegionDO;
import com.hworld.enums.LanguageCodeEnum;
import com.hworld.mapper.sys.SysGlobalRegionMapper;
import com.hworld.service.api.sys.SysGlobalRegionService;
import com.hworld.utils.MyStringUtils;
import com.hworld.utils.RedisUtil;
import com.hworld.utils.RestTemplateUtil;
import com.hworld.vo.req.hdata.region.HDataRegionReqVO;
import com.hworld.vo.resp.hdata.HDataBaseRespVO;
import com.hworld.vo.resp.hdata.HDataRegionResqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 全球地区业务实现
 *
 * @author caoyang
 * @date 2022-07-21 16:43:04
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysGlobalRegionServiceImpl extends ServiceImpl<SysGlobalRegionMapper, SysGlobalRegionDO> implements SysGlobalRegionService {

    @Autowired
    private RestTemplateUtil restTemplateUtils;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${base.site.hdata-url}")
    private String hDataUrl;

    /**
     * 保存所有HData行政区域信息
     *
     * @return
     */
    @Override
    public BaseResponse saveHDataRegionAll(HDataRegionReqVO reqVO) {
        //1.0.查询Hdata行政区域数据
        Map<String, Object> params = new HashMap<>();
        params.put("languageCode", LanguageCodeEnum.EN_US.getLanguageCode());
        params.put("isInternational", true);
        params.put("includeDisabled", false);
        if (reqVO != null) {
            if (!MyStringUtils.isNullParam(reqVO.getLanguageCode())) {
                params.put("languageCode", reqVO.getLanguageCode());
            }
        }

        //1.1.查询Hdata行政区域数据
        ResponseEntity<HDataBaseRespVO> response = restTemplateUtils.get(hDataUrl + "/api/region/all", HDataBaseRespVO.class, params);
        HDataBaseRespVO<HDataBaseRespVO> hDataRespVO = response.getBody();
        List<Object> regionList = (List<Object>) hDataRespVO.getContent();
        if (CollectionUtils.isEmpty(regionList)) {
            return BaseResponse.success();
        }
        List<SysGlobalRegionDO> sysGlobalRegionDOList = new ArrayList<>();
        SysGlobalRegionParentBO parentBO = new SysGlobalRegionParentBO(0L, null, null, null);
        parseHDataRegion(regionList, sysGlobalRegionDOList, parentBO);
        saveBatch(sysGlobalRegionDOList);

        return BaseResponse.success();
    }

    /**
     * 查询HData行政区域信息
     *
     * @return
     */
    @Override
    public BaseResponse getHDataRegionAll(HDataRegionReqVO reqVO) {
        //1.0.查询Hdata行政区域数据
        Map<String, Object> params = new HashMap<>();
        params.put("languageCode", LanguageCodeEnum.EN_US.getLanguageCode());
        params.put("isInternational", true);
        params.put("includeDisabled", false);
        if (reqVO != null) {
            if (!MyStringUtils.isNullParam(reqVO.getLanguageCode())) {
                params.put("languageCode", reqVO.getLanguageCode());
            }
        }

        //1.1.查询Hdata行政区域数据
        ResponseEntity<HDataBaseRespVO> response = restTemplateUtils.get(hDataUrl + "/api/region/all", HDataBaseRespVO.class, params);
        HDataBaseRespVO<HDataBaseRespVO> hDataRespVO = response.getBody();
        List<Object> regionList = (List<Object>) hDataRespVO.getContent();
        if (CollectionUtils.isEmpty(regionList)) {
            return BaseResponse.success();
        }
        return BaseResponse.success(regionList);
    }

    /**
     * 递归解析Hdata全球行政地区数据
     *
     * @param regionList
     * @return
     */
    public void parseHDataRegion(List<Object> regionList, List<SysGlobalRegionDO> sysGlobalRegionDOList, SysGlobalRegionParentBO parentBO) {

        if (CollectionUtils.isEmpty(regionList)) {
            return;
        }
        for (Object object : regionList) {
            HDataRegionResqVO region = JSON.parseObject(JSON.toJSONString(object), HDataRegionResqVO.class);
            if (MyStringUtils.isNullParam(region.getItemCode()) || MyStringUtils.isNullParam(region.getItemName()) ||
                    MyStringUtils.isNullParam(region.getItemLevelCode())) {
                continue;
            }

            Long id = redisUtil.getAndIncrement("sys_global_region");

            StringBuilder pathSb = new StringBuilder();
            if (MyStringUtils.isNotNullParam(parentBO.getParentPath())) {
                pathSb.append(parentBO.getParentPath());
            }
            pathSb.append(id).append(",");

            StringBuilder codePathSb = new StringBuilder();
            if (MyStringUtils.isNotNullParam(parentBO.getParentCodePath())) {
                codePathSb.append(parentBO.getParentCodePath());
            }
            codePathSb.append(region.getItemCode()).append(",");

            SysGlobalRegionDO sysGlobalRegionDO = new SysGlobalRegionDO();
            sysGlobalRegionDO.setId(id);
            sysGlobalRegionDO.setAreaCode(region.getItemCode());
            sysGlobalRegionDO.setNameEn(region.getItemName());
            sysGlobalRegionDO.setAreaLevelCode(region.getItemLevelCode());
            sysGlobalRegionDO.setNamePinyinAll(region.getAllPinyin());
            sysGlobalRegionDO.setNamePinyinShort(region.getShortPinyin());
            sysGlobalRegionDO.setStatus(region.getStatus());

            sysGlobalRegionDO.setParentId(parentBO.getParentId() == null ? 0L : parentBO.getParentId());
            sysGlobalRegionDO.setParentCode(MyStringUtils.isNullParam(parentBO.getParentCode()) ? "" : parentBO.getParentCode());

            sysGlobalRegionDO.setAreaPath(String.valueOf(pathSb));
            sysGlobalRegionDO.setAreaCodePath(String.valueOf(codePathSb));
            sysGlobalRegionDOList.add(sysGlobalRegionDO);

            if (CollectionUtils.isEmpty(region.getSubList())) {
                continue;
            }
            SysGlobalRegionParentBO newParentBO = new SysGlobalRegionParentBO(sysGlobalRegionDO.getId(),
                    sysGlobalRegionDO.getAreaCode(), sysGlobalRegionDO.getAreaCodePath(), sysGlobalRegionDO.getAreaPath());

            parseHDataRegion(region.getSubList(), sysGlobalRegionDOList, newParentBO);
        }
    }
}
