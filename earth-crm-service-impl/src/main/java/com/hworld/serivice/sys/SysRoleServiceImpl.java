package com.hworld.serivice.sys;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.base.BasePagedResponse;
import com.hworld.base.BaseReqPage;
import com.hworld.base.BaseResponse;
import com.hworld.constants.RedisConstants;
import com.hworld.entity.sys.SysPermissionDO;
import com.hworld.entity.sys.SysRoleDO;
import com.hworld.entity.sys.SysUserRoleDO;
import com.hworld.enums.ErrorEnum;
import com.hworld.mapper.sys.SysRoleMapper;
import com.hworld.service.api.sys.*;
import com.hworld.utils.MyBeanUtils;
import com.hworld.utils.MyStringUtils;
import com.hworld.utils.WrapperUtiles;
import com.hworld.vo.req.sys.SysRoleChangeReqVO;
import com.hworld.vo.req.sys.SysRoleReqVO;
import com.hworld.vo.resp.sys.SysPermissionRespVO;
import com.hworld.vo.resp.sys.SysRoleDetailsRespVO;
import com.hworld.vo.resp.sys.SysRoleRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色维护业务实现
 *
 * @author caoyang
 * @date 2021-11-30 11:22:09
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleDO> implements SysRoleService {

    @Autowired
    public SysRoleMapper sysRoleMapper;

    @Autowired
    public SysUserRoleService sysUserRoleService;

    @Autowired
    public SysRolePermissionService sysRolePermissionService;

    @Autowired
    public SysUserPermissionService sysUserPermissionService;

    @Autowired
    public SysPermissionService sysPermissionService;
    //TODO 作废SysRoleStore表 酒店数据不应该跟角色权限相关联，应该将酒店看作部门 caoyang 2022-07-06
//    @Autowired
//    public SysRoleStoreService sysRoleStoreService;

    /**
     * 添加角色信息
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse addRole(SysRoleChangeReqVO reqVO) {
        //1.0.检验参数
        BaseResponse response = checkRoleReqVO(reqVO);
        if (!ErrorEnum.SUCCESS.getCode().equals(response.getCode())) {
            return response;
        }

        //1.1.添加角色
        SysRoleDO sysRoleDO = MyBeanUtils.dtoToDo(reqVO, SysRoleDO.class);
        this.save(sysRoleDO);

        //1.2.添加角色权限信息
        if (CollectionUtils.isEmpty(reqVO.getPermissionIdList())) {
            return response;
        }
        sysRolePermissionService.saveRolePermissionBatch(sysRoleDO, reqVO.getPermissionIdList());

        //TODO 作废SysRoleStore表 酒店数据不应该跟角色权限相关联，应该将酒店看作部门 caoyang 2022-07-06
//        //1.3.添加角色酒店信息
//        if (CollectionUtils.isEmpty(reqVO.getStoreIdList())) {
//            return response;
//        }
//        sysRoleStoreService.saveRoleStoreBatch(sysRoleDO, reqVO.getStoreIdList());

        return BaseResponse.success(MyBeanUtils.doToVo(sysRoleDO, SysRoleRespVO.class));
    }

    /**
     * 删除角色信息
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse deleltRole(SysRoleReqVO reqVO) {
        //1.0.删除用户权限表中数据
        sysUserPermissionService.removeByRoleId(reqVO.getId());

        //1.1.删除RolePermission表中数据
        sysRolePermissionService.removeByRoleId(reqVO.getId());

        //1.2.删除UserRole表中的该信息
        sysUserRoleService.removeByRoleId(reqVO.getId());

        //TODO 作废SysRoleStore表 酒店数据不应该跟角色权限相关联，应该将酒店看作部门 caoyang 2022-07-06
//        //1.3.删除角色酒店表中的该酒店
//        sysRoleStoreService.removeByRoleId(reqVO.getId());

        //1.4.删除角色信息
        removeById(reqVO.getId());

        return BaseResponse.success();
    }

    /**
     * 更新角色信息
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse updateRole(SysRoleChangeReqVO reqVO) {
        //1.0.检验参数
        BaseResponse response = checkRoleReqVO(reqVO);
        if (!ErrorEnum.SUCCESS.getCode().equals(response.getCode())) {
            return response;
        }

        //1.1.修改角色
        SysRoleDO sysRoleDO = MyBeanUtils.dtoToDo(reqVO, SysRoleDO.class);
        this.updateById(sysRoleDO);

        //1.2.更新角色权限信息
        if (!CollectionUtils.isEmpty(reqVO.getPermissionIdList())) {
            //需要添加的权限
            Set<Long> addPermissionIdSet = new HashSet<>();
            //需要删除的权限
            Set<Long> removePermissionIdSet = new HashSet<>();

            sysRolePermissionService.renewRolePermissionBatch(sysRoleDO, reqVO.getPermissionIdList(), addPermissionIdSet, removePermissionIdSet);

            //1.3.更新用户权限数据
            sysUserPermissionService.renewUserPermissionBatch(sysRoleDO, addPermissionIdSet, removePermissionIdSet);
        }

        //TODO 作废SysRoleStore表 酒店数据不应该跟角色权限相关联，应该将酒店看作部门 caoyang 2022-07-06
//        //1.4.更新角色酒店信息
//        if (!CollectionUtils.isEmpty(reqVO.getStoreIdList())) {
//            //需要添加的权限
//            Set<Long> addStoreIdSet = new HashSet<>();
//            //需要删除的权限
//            Set<Long> removeStoreIdIdSet = new HashSet<>();
//
//            sysRoleStoreService.renewRoleStoreBatch(sysRoleDO, reqVO.getStoreIdList(), addStoreIdSet, removeStoreIdIdSet);
//        }

        return BaseResponse.success(MyBeanUtils.doToVo(sysRoleDO, SysRoleRespVO.class));
    }

    /**
     * 查询角色列表(不分页)
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse getRoleList(SysRoleReqVO reqVO) {
        List<SysRoleRespVO> resultList = new ArrayList<>();

        SysRoleDO queryDO = MyBeanUtils.dtoToDo(reqVO, SysRoleDO.class);
        QueryWrapper<SysRoleDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        List<SysRoleDO> list = sysRoleMapper.selectList(queryWrapper);

        for (SysRoleDO sysRoleDO : list) {
            resultList.add(MyBeanUtils.doToVo(sysRoleDO, SysRoleRespVO.class));
        }

        return BaseResponse.success(resultList);
    }

    /**
     * 查询角色列表(分页)
     *
     * @param reqPage
     * @return
     */
    @Override
    public BasePagedResponse getRolePage(BaseReqPage<SysRoleReqVO> reqPage) {
        SysRoleReqVO reqVO = reqPage.getParams() == null ? new SysRoleReqVO() : reqPage.getParams();
        SysRoleDO queryDO = MyBeanUtils.voTodo(reqVO, SysRoleDO.class);
        QueryWrapper<SysRoleDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        queryWrapper.eq("deleted_flag", 0);
        queryWrapper.orderByDesc("modified_on");
        queryWrapper.orderByDesc("id");

        Page<SysRoleDO> page = new Page<>(reqPage.getPage(), reqPage.getRows());
        IPage<SysRoleDO> iPage = sysRoleMapper.selectPage(page, queryWrapper);

        List<SysRoleRespVO> resultList = new ArrayList<>();
        if (CollectionUtils.isEmpty(iPage.getRecords())) {
            return BasePagedResponse.success(iPage.getTotal(), resultList);
        }
        for (SysRoleDO sysRoleDO : iPage.getRecords()) {
            resultList.add(MyBeanUtils.doToVo(sysRoleDO, SysRoleRespVO.class));
        }

        return BasePagedResponse.success(iPage.getTotal(), resultList);
    }

    /**
     * 查询角色详情
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse getRoleOne(SysRoleReqVO reqVO) {
        //1.0.查询角色信息
        SysRoleDO role = getById(reqVO.getId());
        if (role == null) {
            return BaseResponse.success();
        }

        SysRoleDetailsRespVO resultVO = MyBeanUtils.doToVo(role, SysRoleDetailsRespVO.class);

        //1.1.根据角色查询权限信息
        List<SysPermissionDO> permissionList = sysPermissionService.getPermissionListByRoleList(reqVO.getId());

        if (CollectionUtils.isEmpty(permissionList)) {
            return BaseResponse.success(resultVO);
        }

        List<SysPermissionRespVO> permissionRespVOList = new ArrayList<>();
        for (SysPermissionDO permissionDO : permissionList) {
            permissionRespVOList.add(MyBeanUtils.doToVo(permissionDO, SysPermissionRespVO.class));
        }

        Set<Long> permissionIdSet = permissionList.stream().map(SysPermissionDO::getId).collect(Collectors.toSet());
        resultVO.setPermissionList(permissionRespVOList);
        resultVO.setPermissionIdList(CollectionUtils.isEmpty(permissionIdSet) ? new ArrayList<>() : new ArrayList<>(permissionIdSet));
        return BaseResponse.success(resultVO);
    }

    /**
     * 获取超级管理员角色列表
     *
     * @return
     */
    @Override
    public List<SysRoleDO> getAdminRoleList() {
        SysRoleDO queryDO = new SysRoleDO();
        queryDO.setIsAdmin(true);
        queryDO.setDeletedFlag(false);
        QueryWrapper<SysRoleDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        return sysRoleMapper.selectList(queryWrapper);
    }

    /**
     * 根据roleCode查询roleList
     *
     * @param roleCode
     * @return
     */
    @Override
    public List<SysRoleDO> getRoleListByRoleCode(String roleCode) {
        SysRoleDO queryDO = new SysRoleDO();
        queryDO.setRoleCode(roleCode);
        queryDO.setDeletedFlag(false);
        QueryWrapper<SysRoleDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        return sysRoleMapper.selectList(queryWrapper);
    }

    /**
     * 根据userId 查询用户拥有的角色信息
     *
     * @param userId
     * @return
     */
    @Override
    @Cached(area = "default", name = RedisConstants.CRM_USER_ROLE_INFO, key = "#userId", expire = RedisConstants.REDIS_EXPIRE, cacheType = CacheType.REMOTE)
    public List<SysRoleDO> getListByUserId(Long userId) {
        if (userId == null) {
            return null;
        }

        //1.0.查询用户角色关联信息
        List<SysUserRoleDO> userRoleList = sysUserRoleService.selectListByUserId(userId);

        if (CollectionUtils.isEmpty(userRoleList)) {
            return null;
        }
        Set<Long> roleIdSet = userRoleList.stream().map(SysUserRoleDO::getRoleId).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(roleIdSet)) {
            return null;
        }
        return listByIds(roleIdSet);
    }

    /**
     * 校验请求参数
     *
     * @param reqVO
     * @return
     */
    private BaseResponse checkRoleReqVO(SysRoleReqVO reqVO) {
        if (reqVO == null) {
            return BaseResponse.error(ErrorEnum.EMPTY_ERROR.getCode(), "reqVO:" + ErrorEnum.EMPTY_ERROR.getMsgEn());
        }
        //根据roleCode查询角色信息
        List<SysRoleDO> roleList = getRoleListByRoleCode(reqVO.getRoleCode());

        if (!CollectionUtils.isEmpty(roleList)) {
            if (reqVO.getId() == null) {
                log.info("SysRoleService.checkRoleReqVO:roleCode已存在,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.EXIST_ERROR.getCode(), "roleCode:" + ErrorEnum.EXIST_ERROR.getMsgEn());
            } else {
                for (SysRoleDO role : roleList) {
                    if (role.getId().longValue() != reqVO.getId().longValue()) {
                        log.info("SysRoleService.checkRoleReqVO:roleCode已存在,reqVO={}", reqVO);
                        return BaseResponse.error(ErrorEnum.EXIST_ERROR.getCode(), "roleCode:" + ErrorEnum.EXIST_ERROR.getMsgEn());
                    }
                }
            }
        }

        if (reqVO.getId() != null) {
            SysRoleDO sysRoleDO = getById(reqVO.getId());
            if (sysRoleDO == null) {
                log.info("SysRoleService.checkRoleReqVO:sysRoleDO不存在,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.NOT_EXIST_ERROR.getCode(), "roleId:" + ErrorEnum.NOT_EXIST_ERROR.getMsgEn());
            }

            if (MyStringUtils.isNotNullParam(reqVO.getRoleCode()) && !reqVO.getRoleCode().equalsIgnoreCase(sysRoleDO.getRoleCode())) {
                log.info("SysRoleService.checkRoleReqVO:roleCode不能更新,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.UPDATED_ERROR.getCode(), "roleCode:" + ErrorEnum.UPDATED_ERROR.getMsgEn());
            }
        }

        return BaseResponse.success();
    }
}
