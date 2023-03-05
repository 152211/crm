package com.hworld.serivice.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.base.BasePagedResponse;
import com.hworld.base.BaseReqPage;
import com.hworld.base.BaseResponse;
import com.hworld.constants.Constants;
import com.hworld.entity.sys.SysHotelDO;
import com.hworld.enums.ErrorEnum;
import com.hworld.enums.sys.HotelTypeEnum;
import com.hworld.mapper.sys.SysHotelMapper;
import com.hworld.service.api.sys.SysRoleService;
import com.hworld.service.api.sys.SysHotelService;
import com.hworld.service.api.sys.SysUserHotelService;
import com.hworld.service.api.sys.SysUserRoleService;
import com.hworld.utils.MyBeanUtils;
import com.hworld.utils.MyStringUtils;
import com.hworld.utils.WrapperUtiles;
import com.hworld.vo.req.sys.SysHotelReqVO;
import com.hworld.vo.resp.sys.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 酒店管理维护业务实现
 *
 * @author caoyang
 * @date 2022-02-08 16:00:53
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysHotelServiceImpl extends ServiceImpl<SysHotelMapper, SysHotelDO> implements SysHotelService {

    @Autowired
    public SysHotelMapper sysHotelMapper;

    @Autowired
    public SysRoleService sysRoleService;

    @Autowired
    public SysUserRoleService sysUserRoleService;

    @Autowired
    @Lazy
    public SysUserHotelService sysUserHotelService;

    //TODO 作废SysRoleHote表 酒店数据不应该跟角色权限相关联，应该将酒店看作部门 caoyang 2022-07-06
//    @Autowired
//    public SysRoleHoteService sysRoleHoteService;

    /**
     * 添加酒店信息
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse addHotel(SysHotelReqVO reqVO) {
        //1.0.检验参数
        BaseResponse response = checkHoteReqVO(reqVO);
        if (!ErrorEnum.SUCCESS.getCode().equals(response.getCode())) {
            return response;
        }

        //1.1.添加酒店
        SysHotelDO hoteDO = MyBeanUtils.voTodo(reqVO, SysHotelDO.class);
        response = saveHotel(hoteDO);
        if (!ErrorEnum.SUCCESS.getCode().equals(response.getCode())) {
            return response;
        }
        //1.2.将酒店与addHoteUsetIdSet关联
        sysUserHotelService.saveManagerUserHotelBatch(hoteDO);

        //TODO 作废SysRoleHote表 酒店数据不应该跟角色权限相关联，应该将酒店看作部门 caoyang 2022-07-06
//        //1.3.将新增权限添加到超级管理员角色下
//        sysRoleHoteService.saveAdminRoleHoteBatch(HoteDO, adminRoleList);

        return BaseResponse.success(MyBeanUtils.doToVo(hoteDO, SysRoleRespVO.class));
    }

    /**
     * 批量添加酒店信息
     *
     * @param
     * @return
     */
    @Override
    public BaseResponse addHotelBatch(List<SysHotelDO> addHotelList) {
        if (CollectionUtils.isEmpty(addHotelList)) {
            return BaseResponse.success();
        }
        BaseResponse response = saveHotelBatch(addHotelList);
        if (!ErrorEnum.SUCCESS.getCode().equals(response.getCode())) {
            return response;
        }

        //1.2.将酒店与addHoteUsetIdSet关联
        for (SysHotelDO hoteDO : addHotelList) {
            sysUserHotelService.saveManagerUserHotelBatch(hoteDO);
        }
        return BaseResponse.success();
    }

    /**
     * 伪删除酒店信息
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse deleltHotel(SysHotelReqVO reqVO) {
        //1.0.验证是否拥有子酒店 如果有则不让删除
        List<SysHotelDO> children = getHoteListByParentId(reqVO.getId());
        if (!CollectionUtils.isEmpty(children)) {
            log.error("deleltHote:请先删除子节点!reqVO={}", reqVO);
            return BaseResponse.error(ErrorEnum.DELETE_SUB_ERROR.getCode(), ErrorEnum.DELETE_SUB_ERROR.getMsgEn());
        }

        //1.1.删除用户酒店关联表中的该酒店
        Set<Long> deleltHoteIdSet = new HashSet<>();
        deleltHoteIdSet.add(reqVO.getId());
        sysUserHotelService.deleltByHoteIds(deleltHoteIdSet);

        //1.2.伪删除酒店数据
        SysHotelDO newHote = new SysHotelDO();
        newHote.setId(reqVO.getId());
        newHote.setDeletedFlag(true);
        updateById(newHote);

        return BaseResponse.success();
    }

    /**
     * 批量删除酒店信息
     *
     * @param hotelCodeSet
     * @return
     */
    @Override
    public BaseResponse deleltHotelBatch(Set<String> hotelCodeSet) {
        //1.0.查询所有酒店
        List<SysHotelDO> sysHotelListAll = getHoteListByDeletedFlag(false);
        if (CollectionUtils.isEmpty(sysHotelListAll)) {
            return BaseResponse.success();
        }
        Map<String, SysHotelDO> hotelMap = sysHotelListAll.stream().collect(Collectors.toMap(item -> item.getHotelCode(), item -> item));
        if (CollectionUtils.isEmpty(hotelMap)) {
            return BaseResponse.success();
        }
        //1.1.过滤需要删除的酒店/品牌
        Set<String> deleltHotelIdSet = new HashSet<>();
        for (String hotelCode : hotelCodeSet) {
            SysHotelDO deleltHotelDO = hotelMap.get(hotelCode);
            if (deleltHotelDO == null || deleltHotelDO.getId() == null) {
                continue;
            }
            deleltHotelIdSet.add(String.valueOf(deleltHotelDO.getId()));
        }

        //1.2.过滤需要删除的子节点酒店/品牌
        for (String hotelCode : hotelMap.keySet()) {
            SysHotelDO deleltHotelDO = hotelMap.get(hotelCode);
            if (deleltHotelDO == null || MyStringUtils.isNullParam(deleltHotelDO.getHotelPath())) {
                continue;
            }
            String[] hotelIdArray = deleltHotelDO.getHotelPath().split(",");
            if (hotelIdArray == null || hotelIdArray.length <= 0) {
                continue;
            }
            List<String> parentIdSet = Arrays.asList(hotelIdArray);
            if (CollectionUtils.isEmpty(parentIdSet)) {
                continue;
            }
            //求childrenHotelIdSet和deleltHotelIdSet的交集
            Set<String> sameSet = new HashSet<>();
            sameSet.addAll(deleltHotelIdSet);
            sameSet.retainAll(parentIdSet);
            if (!CollectionUtils.isEmpty(sameSet)) {
                deleltHotelIdSet.add(String.valueOf(deleltHotelDO.getId()));
            }
        }

        if (CollectionUtils.isEmpty(deleltHotelIdSet)) {
            return BaseResponse.success();
        }

        //1.3.整理需要删除的酒店数据
        Set<Long> deleltHoteSet = new HashSet<>();
        List<SysHotelDO> deleltHoteList = new ArrayList<>();
        for (String hotelIdStr : deleltHotelIdSet) {
            Long hotelId = MyStringUtils.stringToLong(hotelIdStr);
            if (hotelId != null) {
                SysHotelDO deleltHotelDO = new SysHotelDO();
                deleltHotelDO.setId(hotelId);
                deleltHotelDO.setDeletedFlag(true);
                deleltHoteList.add(deleltHotelDO);
                deleltHoteSet.add(hotelId);
            }
        }

        //1.4.删除用户酒店关联表中的该酒店
        if (!CollectionUtils.isEmpty(deleltHoteSet)) {
            sysUserHotelService.deleltByHoteIds(deleltHoteSet);
        }

        //1.5.伪删除酒店数据
        if (!CollectionUtils.isEmpty(deleltHoteList)) {
            updateBatchById(deleltHoteList, 200);
        }
        return BaseResponse.success();
    }

    /**
     * 更新酒店信息
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse updateHotel(SysHotelReqVO reqVO) {
        //1.0.检验参数
        BaseResponse response = checkHoteReqVO(reqVO);
        if (!ErrorEnum.SUCCESS.getCode().equals(response.getCode())) {
            return response;
        }

        //1.1.更新酒店表
        SysHotelDO hoteDO = MyBeanUtils.dtoToDo(reqVO, SysHotelDO.class);
        this.updateById(hoteDO);

        //TODO 作废SysRoleHote表 酒店数据不应该跟角色权限相关联，应该将酒店看作部门 caoyang 2022-07-06
//        //1.2.更新角色酒店关联表
//        sysRoleHoteService.updateRoleHote(HoteDO);
        return BaseResponse.success(MyBeanUtils.doToVo(hoteDO, SysHotelRespVO.class));
    }

    /**
     * 批量更新酒店信息
     *
     * @param list
     * @return
     */
    @Override
    public BaseResponse updateHotelBatch(List<SysHotelDO> list) {

        List<SysHotelDO> addManagerUserHoteList = new ArrayList<>();

        for (SysHotelDO newHotelDO : list) {
            List<SysHotelDO> oldHotelDOList = getHoteListByHotelCode(newHotelDO.getHotelCode());
            if (CollectionUtils.isEmpty(oldHotelDOList)) {
                continue;
            }
            //如果数据库中数据是删除状态则
            if (oldHotelDOList.get(0).getDeletedFlag()) {
                addManagerUserHoteList.add(newHotelDO);
            }
            newHotelDO.setId(oldHotelDOList.get(0).getId());
        }
        updateBatchById(list);
        //1.2.将酒店与addHoteUsetIdSet关联
        for (SysHotelDO hoteDO : addManagerUserHoteList) {
            sysUserHotelService.saveManagerUserHotelBatch(hoteDO);
        }
        return BaseResponse.success();
    }

    /**
     * 获取酒店列表(不分页)
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse getHotelList(SysHotelReqVO reqVO) {
        List<SysHotelRespVO> resultList = new ArrayList<>();

        SysHotelDO queryDO = MyBeanUtils.dtoToDo(reqVO, SysHotelDO.class);
        QueryWrapper<SysHotelDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        List<SysHotelDO> list = sysHotelMapper.selectList(queryWrapper);

        for (SysHotelDO HoteDO : list) {
            resultList.add(MyBeanUtils.doToVo(HoteDO, SysHotelRespVO.class));
        }

        return BaseResponse.success(resultList);
    }

    /**
     * 获取酒店树结构
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse getHotelTree(SysHotelReqVO reqVO) {
        SysHotelDO queryDO = MyBeanUtils.dtoToDo(reqVO, SysHotelDO.class);
        QueryWrapper<SysHotelDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        queryWrapper.orderByAsc("parent_id");
        List<SysHotelDO> HoteList = sysHotelMapper.selectList(queryWrapper);

        List<SysHotelTreeRespVO> HoteTreeRespVO = getHoteTreeRespVO(HoteList);
        return BaseResponse.success(HoteTreeRespVO);
    }

    /**
     * 查询酒店列表(分页)
     *
     * @param reqPage
     * @return
     */
    @Override
    public BasePagedResponse getHotelPage(BaseReqPage<SysHotelReqVO> reqPage) {
        SysHotelReqVO reqVO = reqPage.getParams() == null ? new SysHotelReqVO() : reqPage.getParams();
        SysHotelDO queryDO = MyBeanUtils.voTodo(reqVO, SysHotelDO.class);
        QueryWrapper<SysHotelDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        queryWrapper.eq("deleted_flag", 0);

        Page<SysHotelDO> page = new Page<>(reqPage.getPage(), reqPage.getRows());
        IPage<SysHotelDO> iPage = sysHotelMapper.selectPage(page, queryWrapper);

        List<SysHotelRespVO> resultList = new ArrayList<>();
        if (CollectionUtils.isEmpty(iPage.getRecords())) {
            return BasePagedResponse.success(iPage.getTotal(), resultList);
        }
        for (SysHotelDO Hote : iPage.getRecords()) {
            resultList.add(MyBeanUtils.doToVo(Hote, SysHotelRespVO.class));
        }

        return BasePagedResponse.success(iPage.getTotal(), resultList);
    }

    /**
     * 获取酒店详情
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse getHotelOne(SysHotelReqVO reqVO) {
        //1.0.查询权限信息
        SysHotelDO Hote = getById(reqVO.getId());
        if (Hote == null) {
            return BaseResponse.success();
        }

        return BaseResponse.success(MyBeanUtils.doToVo(Hote, SysHotelRespVO.class));
    }

    /**
     * 保存酒店数据
     *
     * @param sysHotelDO
     * @return
     */
    @Override
    public BaseResponse saveHotel(SysHotelDO sysHotelDO) {
        StringBuilder hotelPath = new StringBuilder();
        //如果是根节点,pid设置为0,path设置为","
        if (HotelTypeEnum.ROOT.getHotelType().equalsIgnoreCase(sysHotelDO.getHotelType())) {
            sysHotelDO.setParentId(Constants.ZERO.longValue());
            sysHotelDO.setHotelLevel(1);
            sysHotelDO.setHotelPath("");
        } else {
            //不是根节点查询该酒店的父节点
            SysHotelDO parentHoteDO = getById(sysHotelDO.getParentId());
            if (parentHoteDO == null) {
                return BaseResponse.error(ErrorEnum.NOT_EXIST_ERROR.getCode(), "parentId:" + ErrorEnum.NOT_EXIST_ERROR.getMsgEn());
            }
            sysHotelDO.setHotelPath(parentHoteDO.getHotelPath());
            sysHotelDO.setHotelLevel(parentHoteDO.getHotelLevel() + 1);
            hotelPath.append(parentHoteDO.getHotelPath());
        }
        sysHotelMapper.insert(sysHotelDO);

        //修改酒店路径
        hotelPath.append(sysHotelDO.getId()).append(",");
        sysHotelDO.setHotelPath(hotelPath.toString());
        sysHotelMapper.updateById(sysHotelDO);

        return BaseResponse.success();
    }

    /**
     * 批量保存酒店数据
     *
     * @param sysHotelDOList
     * @return
     */
    @Override
    public BaseResponse saveHotelBatch(List<SysHotelDO> sysHotelDOList) {
        if (CollectionUtils.isEmpty(sysHotelDOList)) {
            return BaseResponse.success();
        }

        Map<String, String> hotelPathMap = new HashMap<>();

        for (SysHotelDO sysHotelDO : sysHotelDOList) {
            StringBuilder hotelPath = new StringBuilder();
            //如果是根节点,pid设置为0,path设置为","
            if (HotelTypeEnum.ROOT.getHotelType().equalsIgnoreCase(sysHotelDO.getHotelType())) {
                sysHotelDO.setParentId(Constants.ZERO.longValue());
                sysHotelDO.setHotelLevel(1);
                sysHotelDO.setHotelPath("");
            } else {
                //不是根节点查询该酒店的父节点
                SysHotelDO parentHoteDO = getById(sysHotelDO.getParentId());
                if (parentHoteDO == null) {
                    return BaseResponse.error(ErrorEnum.NOT_EXIST_ERROR.getCode(), "parentId:" + ErrorEnum.NOT_EXIST_ERROR.getMsgEn());
                }
                sysHotelDO.setHotelPath(parentHoteDO.getHotelPath());
                sysHotelDO.setHotelLevel(parentHoteDO.getHotelLevel() + 1);
                hotelPath.append(parentHoteDO.getHotelPath());
                hotelPathMap.put(sysHotelDO.getHotelCode(), String.valueOf(hotelPath));
            }
        }
        //批量新增
        saveBatch(sysHotelDOList, 500);
        for (SysHotelDO sysHotelDO : sysHotelDOList) {
            if (CollectionUtils.isEmpty(hotelPathMap) || MyStringUtils.isNullParam(sysHotelDO.getHotelCode())) {
                continue;
            }
            StringBuilder hotelPath = new StringBuilder();
            if (MyStringUtils.isNullParam(hotelPathMap.get(sysHotelDO.getHotelCode()))) {
                continue;
            }
            hotelPath.append(hotelPathMap.get(sysHotelDO.getHotelCode())).append(sysHotelDO.getId()).append(",");
            sysHotelDO.setHotelPath(String.valueOf(hotelPath));
        }
        //修改酒店路径
        updateBatchById(sysHotelDOList, 500);
        return BaseResponse.success();
    }

    /**
     * 根据HoteNo查询酒店列表
     *
     * @param hotelCode
     * @return
     */
    @Override
    public List<SysHotelDO> getHoteListByHotelCode(String hotelCode) {
        if (MyStringUtils.isNullParam(hotelCode)) {
            return null;
        }
        SysHotelDO queryDO = new SysHotelDO();
        queryDO.setHotelCode(hotelCode);
        queryDO.setDeletedFlag(false);
        QueryWrapper<SysHotelDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        queryWrapper.orderByAsc("hotel_level");

        return sysHotelMapper.selectList(queryWrapper);
    }

    /**
     * 根据HoteNo查询酒店列表
     *
     * @param hotelCode
     * @return
     */
    @Override
    public SysHotelDO getHoteByHotelCode(String hotelCode, String hotelType) {
        if (MyStringUtils.isNullParam(hotelCode)) {
            return null;
        }
        SysHotelDO queryDO = new SysHotelDO();
        queryDO.setHotelCode(hotelCode);
        queryDO.setDeletedFlag(false);
        queryDO.setHotelType(hotelType);
        QueryWrapper<SysHotelDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        queryWrapper.orderByAsc("hotel_level");

        List<SysHotelDO> list = sysHotelMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }


    /**
     * 根据parentId查询子酒店列表
     *
     * @param parentId
     * @return
     */
    @Override
    public List<SysHotelDO> getHoteListByParentId(Long parentId) {
        SysHotelDO queryDO = new SysHotelDO();
        queryDO.setParentId(parentId);
        queryDO.setDeletedFlag(false);
        QueryWrapper<SysHotelDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);

        return sysHotelMapper.selectList(queryWrapper);
    }

    /**
     * 查询hoteDO酒店所有父节点列表
     *
     * @param hoteDO
     * @return
     */
    @Override
    public List<SysHotelDO> getParentHoteListByDO(SysHotelDO hoteDO) {
        List<SysHotelDO> resultList = new ArrayList<>();
        String hotelPath = hoteDO.getHotelPath();
        if (MyStringUtils.isNullParam(hotelPath)) {
            return resultList;
        }
        String[] pIdArray = hotelPath.split(",");
        if (pIdArray == null || pIdArray.length <= 0) {
            return resultList;
        }
        List<String> pIdList = Arrays.asList(pIdArray);
        if (hoteDO.getId() != null) {
            String hoteIdStr = String.valueOf(hoteDO.getId());
            pIdList = pIdList.stream().filter(f -> !f.contains(hoteIdStr)).collect(Collectors.toList());
        }
        if (CollectionUtils.isEmpty(pIdList)) {
            return resultList;
        }
        return this.listByIds(pIdList);
    }

    /**
     * 根据hotelCodeSet查询酒店列表
     *
     * @param hotelCodeSet
     * @return
     */
    @Override
    public List<SysHotelDO> getHoteListByHotelCodeSet(Set<String> hotelCodeSet, String hotelType) {
        if (CollectionUtils.isEmpty(hotelCodeSet)) {
            return null;
        }
        QueryWrapper<SysHotelDO> queryWrapper = new QueryWrapper<>();
        if (MyStringUtils.isNotNullParam(hotelType)) {
            queryWrapper.eq("hotel_type", hotelType);
        }
        queryWrapper.in("hotel_code", hotelCodeSet);
        return sysHotelMapper.selectList(queryWrapper);
    }

    @Override
    public List<SysHotelDO> getHoteListByDeletedFlag(Boolean deletedFlag) {
        if (deletedFlag == null) {
            return list();
        }
        SysHotelDO queryDO = new SysHotelDO();
        queryDO.setDeletedFlag(deletedFlag);
        QueryWrapper<SysHotelDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        queryWrapper.orderByAsc("parent_id");
        return sysHotelMapper.selectList(queryWrapper);
    }

    /**
     * 获取根节点
     *
     * @return
     */
    @Override
    public SysHotelDO getRootHotel() {
        QueryWrapper<SysHotelDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hotel_type", HotelTypeEnum.ROOT.getHotelType());

        List<SysHotelDO> list = sysHotelMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Async
    @Override
    public Future<BaseResponse> addHotelBatchAsync(List<SysHotelDO> addHotelList) {
        return new AsyncResult<>(addHotelBatch(addHotelList));
    }

    /**
     * 获取根节点
     *
     * @return
     */
    @Async
    @Override
    public Future<SysHotelDO> getRootHotelAsync() {
        return new AsyncResult<>(getRootHotel());
    }

    /**
     * 批量异步删除酒店信息
     *
     * @param hotelCodeSet
     * @return
     */
    @Async
    @Override
    public Future<BaseResponse> deleltHotelBatchAsync(Set<String> hotelCodeSet) {
        BaseResponse response = deleltHotelBatch(hotelCodeSet);
        return new AsyncResult<>(response);
    }

    /**
     * 异步批量更新酒店信息
     *
     * @param list
     * @return
     */
    @Async
    @Override
    public Future<BaseResponse> updateHotelBatchAsync(List<SysHotelDO> list) {
        return new AsyncResult<>(updateHotelBatch(list));
    }

    @Async
    @Override
    public Future<SysHotelDO> getHoteByHotelCodeByAsync(String hotelCode) {
        List<SysHotelDO> list = getHoteListByHotelCode(hotelCode);
        if (MyStringUtils.isNullParam(hotelCode)) {
            return new AsyncResult<>(null);
        }
        return new AsyncResult<>(list.get(0));
    }

    /**
     * 校验请求参数
     *
     * @param reqVO
     * @return
     */
    private BaseResponse checkHoteReqVO(SysHotelReqVO reqVO) {
        if (reqVO == null) {
            return BaseResponse.error(ErrorEnum.EMPTY_ERROR.getCode(), "reqVO:" + ErrorEnum.EMPTY_ERROR.getMsgEn());
        }
        //根据HoteNo查询
        List<SysHotelDO> SysHotelDOList = getHoteListByHotelCode(reqVO.getHotelCode());
        if (!CollectionUtils.isEmpty(SysHotelDOList)) {
            if (reqVO.getId() == null) {
                log.info("SysHoteService.checkHoteReqVO:HoteNo已存在,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.EXIST_ERROR.getCode(), "hoteNo:" + ErrorEnum.EXIST_ERROR.getMsgEn());
            } else {
                for (SysHotelDO sysHotelDO : SysHotelDOList) {
                    if (sysHotelDO.getId().longValue() != reqVO.getId().longValue()) {
                        log.info("SysHoteService.checkHoteReqVO:hoteNo已存在,reqVO={}", reqVO);
                        return BaseResponse.error(ErrorEnum.EXIST_ERROR.getCode(), "hoteNo:" + ErrorEnum.EXIST_ERROR.getMsgEn());
                    }
                }
            }
        }
        if (reqVO.getId() != null) {
            SysHotelDO sysHotelDO = getById(reqVO.getId());
            if (sysHotelDO == null) {
                log.info("SysHoteService.checkHoteReqVO:SysHotelDO不存在,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.NOT_EXIST_ERROR.getCode(), "sysHotelDO:" + ErrorEnum.NOT_EXIST_ERROR.getMsgEn());
            }
            if (MyStringUtils.isNotNullParam(reqVO.getHotelType()) && reqVO.getHotelType().equalsIgnoreCase(sysHotelDO.getHotelType())) {
                log.info("SysHoteService.checkHoteReqVO:hotelType不能被更新,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.UPDATED_ERROR.getCode(), "hotelType:" + ErrorEnum.UPDATED_ERROR.getMsgEn());
            }
            if (MyStringUtils.isNotNullParam(reqVO.getHotelCode()) && reqVO.getHotelCode().equalsIgnoreCase(sysHotelDO.getHotelCode())) {
                log.info("SysHoteService.checkHoteReqVO:hotelCode不能被更新,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.UPDATED_ERROR.getCode(), "hotelCode:" + ErrorEnum.UPDATED_ERROR.getMsgEn());
            }
            if (reqVO.getParentId() != null && reqVO.getParentId().longValue() != sysHotelDO.getParentId()) {
                log.info("SysHoteService.checkHoteReqVO:parentId不能被更新,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.UPDATED_ERROR.getCode(), "parentId:" + ErrorEnum.UPDATED_ERROR.getMsgEn());
            }
        }

        if (!HotelTypeEnum.ROOT.getHotelType().equalsIgnoreCase(reqVO.getHotelType())) {
            if (reqVO.getParentId() == null) {
                return BaseResponse.error(ErrorEnum.EMPTY_ERROR.getCode(), "parentId:" + ErrorEnum.EMPTY_ERROR.getMsgEn());
            }
            SysHotelDO parentHoteDO = getById(reqVO.getParentId());
            if (parentHoteDO == null || parentHoteDO.getId() == null) {
                log.info("SysHoteService.checkHoteReqVO:parentId不存在,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.NOT_EXIST_ERROR.getCode(), "parentId:" + ErrorEnum.NOT_EXIST_ERROR.getMsgEn());
            }
        }

        return BaseResponse.success();
    }

    /**
     * 获取最终反馈的树结构酒店列表
     *
     * @param HoteList 所有酒店集合
     */
    public List<SysHotelTreeRespVO> getHoteTreeRespVO(List<SysHotelDO> HoteList) {
        if (CollectionUtils.isEmpty(HoteList)) {
            return new ArrayList<>();
        }

        //权限类型为:R的菜单组集合 用于最终反馈
        List<SysHotelTreeRespVO> hoteGroupList = new ArrayList<>();

        //所有子节点的权限集合 key=parentId,val=children
        Map<Long, List<SysHotelTreeRespVO>> parentMap = new HashMap<>();
        for (SysHotelDO SysHotelDO : HoteList) {
            //list转map 并将DO转化为VO
            SysHotelTreeRespVO hoteTreeRespVO = MyBeanUtils.dtoToDo(SysHotelDO, SysHotelTreeRespVO.class);

            if (SysHotelDO.getParentId() == null || SysHotelDO.getParentId().longValue() == 0) {
                hoteGroupList.add(hoteTreeRespVO);
                continue;
            }

            //list转map 将DO转化为VO 并根据parentId分组
            List<SysHotelTreeRespVO> children = parentMap.get(SysHotelDO.getParentId()) == null ? new ArrayList<>() : parentMap.get(SysHotelDO.getParentId());
            children.add(hoteTreeRespVO);
            parentMap.put(SysHotelDO.getParentId(), children);
        }

        //设置最终树结构权限
        setSysHoteTreeRespVO(hoteGroupList, parentMap);

        return hoteGroupList;
    }

    /**
     * 设置最终树结构权限
     *
     * @param parentVOList
     * @param parentMap
     */
    public void setSysHoteTreeRespVO(List<SysHotelTreeRespVO> parentVOList, Map<Long, List<SysHotelTreeRespVO>> parentMap) {
        if (CollectionUtils.isEmpty(parentVOList)) {
            return;
        }

        for (SysHotelTreeRespVO HoteTreeRespVO : parentVOList) {
            List<SysHotelTreeRespVO> children = parentMap.get(HoteTreeRespVO.getId());
            if (CollectionUtils.isEmpty(children)) {
                continue;
            }
            HoteTreeRespVO.setChildren(children);

            //递归
            setSysHoteTreeRespVO(children, parentMap);
        }
    }
}
