package com.hworld.serivice.sys;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.base.BasePagedResponse;
import com.hworld.base.BaseReqPage;
import com.hworld.base.BaseResponse;
import com.hworld.constants.Constants;
import com.hworld.constants.RedisConstants;
import com.hworld.entity.member.MemberLevelDO;
import com.hworld.entity.sys.*;
import com.hworld.enums.ErrorEnum;
import com.hworld.enums.sys.PermissionTypeEnum;
import com.hworld.http.WebContext;
import com.hworld.mapper.sys.SysPermissionMapper;
import com.hworld.service.api.async.sys.SysRedisAsyncService;
import com.hworld.service.api.sys.*;
import com.hworld.utils.MyBeanUtils;
import com.hworld.utils.MyStringUtils;
import com.hworld.utils.WrapperUtiles;
import com.hworld.vo.req.sys.SysParentReqVO;
import com.hworld.vo.req.sys.SysPermissionReqVO;
import com.hworld.vo.req.sys.SysUserReqVO;
import com.hworld.vo.resp.sys.SysPermissionRespVO;
import com.hworld.vo.resp.sys.SysPermissionTreeRespVO;
import com.hworld.vo.resp.sys.SysUserRespVO;
import com.hworld.vo.resp.sys.SysUserRouteRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 系统权限业务实现
 *
 * @author caoyang
 * @date 2021-11-30 11:22:06
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermissionDO> implements SysPermissionService {

    @Autowired
    public SysPermissionMapper sysPermissionMapper;

    @Autowired
    public SysRoleService sysRoleService;

    @Autowired
    public SysRedisService sysRedisService;

    @Autowired
    public SysRolePermissionService sysRolePermissionService;

    @Autowired
    public SysUserPermissionService sysUserPermissionService;

    /**
     * 添加权限信息
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse addPermission(SysPermissionReqVO reqVO) {
        //1.0.检验参数
        BaseResponse response = checkPermissionReqVO(reqVO);
        if (!ErrorEnum.SUCCESS.getCode().equals(response.getCode())) {
            return response;
        }

        //1.1.添加sys_permission
        SysPermissionDO permissionDO = MyBeanUtils.dtoToDo(reqVO, SysPermissionDO.class);
        this.save(permissionDO);

        //1.2.查询超级管理员角色
        List<SysRoleDO> adminRoleList = sysRoleService.getAdminRoleList();
        if (CollectionUtils.isEmpty(adminRoleList)) {
            return BaseResponse.success(MyBeanUtils.doToVo(permissionDO, SysPermissionRespVO.class));
        }

        //1.3.将新增权限添加到超级管理员角色下
        sysRolePermissionService.saveAdminRolePermissionBatch(permissionDO, adminRoleList);

        //1.4.将新增权限添加到超级管理员用户下
        sysUserPermissionService.saveUserPermissionBatch(permissionDO, adminRoleList);

        //1.5.清空当前用户权限缓存
        sysRedisService.delPermission(WebContext.getUserId());

        return BaseResponse.success(MyBeanUtils.doToVo(permissionDO, SysPermissionRespVO.class));
    }

    /**
     * 删除权限信息
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse deleltPermission(SysPermissionReqVO reqVO) {
        //1.0.验证是否拥有子权限 如果有则不让删除
        List<SysPermissionDO> children = getPermissionListByParentId(reqVO.getId());
        if (!CollectionUtils.isEmpty(children)) {
            log.info("deleltPermission:请先删除子权限!reqVO={}", reqVO);
            return BaseResponse.error(ErrorEnum.DELETE_SUB_ERROR.getCode(), ErrorEnum.DELETE_SUB_ERROR.getMsgEn());
        }

        //1.1.删除用户权限表中的该信息
        sysUserPermissionService.removeByPermissionId(reqVO.getId());

        //1.2.删除角色权限表中的该权限
        sysRolePermissionService.removeByPermissionId(reqVO.getId());

        //1.3.删除权限表中的该权限
        removeById(reqVO.getId());

        //1.4.清空当前用户权限缓存
        sysRedisService.delPermission(WebContext.getUserId());
        return BaseResponse.success();
    }

    /**
     * 更新权限信息
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse updatePermission(SysPermissionReqVO reqVO) {
        //1.0.检验参数
        BaseResponse response = checkPermissionReqVO(reqVO);
        if (!ErrorEnum.SUCCESS.getCode().equals(response.getCode())) {
            return response;
        }
        //更新
        SysPermissionDO permissionDO = MyBeanUtils.dtoToDo(reqVO, SysPermissionDO.class);
        this.updateById(permissionDO);

        //清空当前用户权限缓存
        sysRedisService.delPermission(WebContext.getUserId());
        return BaseResponse.success(MyBeanUtils.doToVo(permissionDO, SysPermissionRespVO.class));
    }

    /**
     * 获取权限列表(不分页)
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse getPermissionList(SysPermissionReqVO reqVO) {
        List<SysPermissionRespVO> resultList = new ArrayList<>();

        SysPermissionDO queryDO = MyBeanUtils.dtoToDo(reqVO, SysPermissionDO.class);
        QueryWrapper<SysPermissionDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        List<SysPermissionDO> list = sysPermissionMapper.selectList(queryWrapper);

        for (SysPermissionDO sysPermissionDO : list) {
            resultList.add(MyBeanUtils.doToVo(sysPermissionDO, SysPermissionRespVO.class));
        }

        return BaseResponse.success(resultList);
    }

    /**
     * 获取权限树结构
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse getPermissionTree(SysPermissionReqVO reqVO) {
        //1.0.查询出所有未删除的权限列表
        List<SysPermissionDO> permissionAllList = getPermissionAllList();
        if (CollectionUtils.isEmpty(permissionAllList)) {
            return BaseResponse.success(new ArrayList<>());
        }

        //1.1.查询满足条件的节点
        SysPermissionDO queryDO = MyBeanUtils.dtoToDo(reqVO, SysPermissionDO.class);
        QueryWrapper<SysPermissionDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        queryWrapper.orderByAsc("parent_id");
        List<SysPermissionDO> sysPermissionDOList = sysPermissionMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(sysPermissionDOList)) {
            return BaseResponse.success(new ArrayList<>());
        }

        //1.2.获取sysPermissionDOList中的根节点
        Set<Long> parentIdSet = getParentIdSet(sysPermissionDOList);
        if (CollectionUtils.isEmpty(parentIdSet)) {
            return BaseResponse.success(new ArrayList<>());
        }

        //1.3.获取最终反馈的树结构权限列表
        List<SysPermissionTreeRespVO> sysPermissionTreeRespVOList = getSysPermissionTreeRespVO(permissionAllList, parentIdSet);
        return BaseResponse.success(sysPermissionTreeRespVOList);
    }

    /**
     * 查询权限列表(分页)
     *
     * @param reqPage
     * @return
     */
    @Override
    public BasePagedResponse getPermissionPage(BaseReqPage<SysPermissionReqVO> reqPage) {

        SysPermissionReqVO reqVO = reqPage.getParams() == null ? new SysPermissionReqVO() : reqPage.getParams();
        SysPermissionDO queryDO = MyBeanUtils.voTodo(reqVO, SysPermissionDO.class);
        QueryWrapper<SysPermissionDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        queryWrapper.eq("deleted_flag", 0);

        Page<SysPermissionDO> page = new Page<>(reqPage.getPage(), reqPage.getRows());
        IPage<SysPermissionDO> iPage = sysPermissionMapper.selectPage(page, queryWrapper);

        List<SysPermissionRespVO> resultList = new ArrayList<>();
        if (CollectionUtils.isEmpty(iPage.getRecords())) {
            return BasePagedResponse.success(iPage.getTotal(), resultList);
        }
        for (SysPermissionDO sysPermissionDO : iPage.getRecords()) {
            resultList.add(MyBeanUtils.doToVo(sysPermissionDO, SysPermissionRespVO.class));
        }

        return BasePagedResponse.success(iPage.getTotal(), resultList);
    }

    /**
     * 获取权限详情
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse getPermissionOne(SysPermissionReqVO reqVO) {
        //1.0.查询权限信息
        SysPermissionDO permission = getById(reqVO.getId());
        if (permission == null) {
            return BaseResponse.success();
        }

        return BaseResponse.success(MyBeanUtils.doToVo(permission, SysPermissionRespVO.class));
    }

    /**
     * 获取父级权限列表
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse getParentList(SysParentReqVO reqVO) {
        List<SysPermissionRespVO> resultList = new ArrayList<>();
        if (PermissionTypeEnum.GROUP.getPermissionType().equalsIgnoreCase(reqVO.getPermissionType())) {
            QueryWrapper<SysPermissionDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("permission_type", PermissionTypeEnum.GROUP.getPermissionType());
            queryWrapper.eq("deleted_flag", Constants.ZERO);
            List<SysPermissionDO> list = sysPermissionMapper.selectList(queryWrapper);
            for (SysPermissionDO sysPermissionDO : list) {
                resultList.add(MyBeanUtils.doToVo(sysPermissionDO, SysPermissionRespVO.class));
            }
            SysPermissionRespVO respVO = new SysPermissionRespVO();
            respVO.setId(Constants.ZERO.longValue());
            respVO.setPermissionNameCn("根节点");
            respVO.setPermissionNameEn("Root");
            resultList.add(respVO);

            return BaseResponse.success(resultList);
        }

        QueryWrapper<SysPermissionDO> queryWrapper = new QueryWrapper<>();

        if (reqVO == null || MyStringUtils.isNullParam(reqVO.getPermissionType())) {

        } else if (PermissionTypeEnum.MENU.getPermissionType().equalsIgnoreCase(reqVO.getPermissionType())) {
            queryWrapper.eq("permission_type", PermissionTypeEnum.GROUP.getPermissionType());
        } else if (PermissionTypeEnum.FEATURES.getPermissionType().equalsIgnoreCase(reqVO.getPermissionType())) {
            queryWrapper.eq("permission_type", PermissionTypeEnum.MENU.getPermissionType());
        } else {
            return BaseResponse.error(ErrorEnum.PERMISSION_TYPE_ERROR.getCode(), ErrorEnum.PERMISSION_TYPE_ERROR.getMsgEn());
        }
        queryWrapper.eq("deleted_flag", Constants.ZERO);
        List<SysPermissionDO> list = sysPermissionMapper.selectList(queryWrapper);

        for (SysPermissionDO sysPermissionDO : list) {
            resultList.add(MyBeanUtils.doToVo(sysPermissionDO, SysPermissionRespVO.class));
        }

        return BaseResponse.success(resultList);
    }

    /**
     * 根据userId获取用户权限树结构
     *
     * @param userId
     * @return
     */
    @Override
//    @Cached(area = "default", name = RedisConstants.CRM_USER_PERMISSION_TREE, key = "#userId", expire = RedisConstants.REDIS_EXPIRE, cacheType = CacheType.REMOTE)
    public List<SysPermissionTreeRespVO> getPermissionTreeByUserId(Long userId) {
        if (userId == null) {
            return null;
        }

        //1.0.查询用户权限信息
        List<SysUserPermissionDO> userPermissionList = sysUserPermissionService.selectUserPermissionByUserId(userId);

        if (CollectionUtils.isEmpty(userPermissionList)) {
            return null;
        }
        Set<Long> permissionIdSet = userPermissionList.stream().map(SysUserPermissionDO::getPermissionId).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(permissionIdSet)) {
            return null;
        }

        //1.1.查询权限详情
        List<SysPermissionDO> permissionList = listByIds(permissionIdSet);
        Set<Long> parentIdSet = new HashSet<>();
        parentIdSet.add(0L);

        //1.2.封装树结构
        return getSysPermissionTreeRespVO(permissionList, parentIdSet);
    }

    /**
     * 根据userId获取用户路由权限
     *
     * @param userId
     * @return
     */
    @Override
    @Cached(area = "default", name = RedisConstants.CRM_USER_ROUTE, key = "#userId", expire = RedisConstants.REDIS_EXPIRE, cacheType = CacheType.REMOTE)
    public List<SysUserRouteRespVO> getUserRouter(Long userId) {
        if (userId == null) {
            return null;
        }

        //1.0.查询用户权限信息
        List<SysUserPermissionDO> userPermissionList = sysUserPermissionService.selectUserPermissionByUserId(userId);

        if (CollectionUtils.isEmpty(userPermissionList)) {
            return null;
        }
        Set<Long> permissionIdSet = userPermissionList.stream().map(SysUserPermissionDO::getPermissionId).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(permissionIdSet)) {
            return null;
        }

        //1.1.查询权限详情
        List<SysPermissionDO> permissionList = listByIds(permissionIdSet);

        //1.2.封装树结构
        return getRouteRespVO(permissionList);
    }

    /**
     * 根据permissionCode查询权限列表
     *
     * @param permissionCode
     * @return
     */
    @Override
    public List<SysPermissionDO> getPermissionList(String permissionCode) {
        if (MyStringUtils.isNullParam(permissionCode)) {
            return null;
        }

        SysPermissionDO queryDO = new SysPermissionDO();
        queryDO.setPermissionCode(permissionCode);
        queryDO.setDeletedFlag(false);
        QueryWrapper<SysPermissionDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);

        return sysPermissionMapper.selectList(queryWrapper);
    }

    /**
     * 根据parent_id查询权限列表
     *
     * @param parentId
     * @return
     */
    @Override
    public List<SysPermissionDO> getPermissionListByParentId(Long parentId) {
        SysPermissionDO queryDO = new SysPermissionDO();
        queryDO.setParentId(parentId);
        queryDO.setDeletedFlag(false);
        QueryWrapper<SysPermissionDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);

        return sysPermissionMapper.selectList(queryWrapper);
    }

    /**
     * 根据roleId查询权限列
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysPermissionDO> getPermissionListByRoleList(Long roleId) {

        //1.0.查询角色权限信息
        List<SysRolePermissionDO> rolePermissionList = sysRolePermissionService.getSysRolePermissionListByRoleId(roleId);
        if (CollectionUtils.isEmpty(rolePermissionList)) {
            return null;
        }
        Set<Long> permissionIdSet = rolePermissionList.stream().map(SysRolePermissionDO::getPermissionId).collect(Collectors.toSet());

        if (CollectionUtils.isEmpty(permissionIdSet)) {
            return null;
        }

        return listByIds(permissionIdSet);
    }

    /**
     * 查询所有权限列表
     *
     * @return
     * @Cached 用法:https://github.com/alibaba/jetcache/blob/master/docs/EN/MethodCache.md
     */
    @Override
    @Cached(area = "default", name = RedisConstants.CRM_PERMISSION_ALL, expire = RedisConstants.REDIS_EXPIRE_ONE_HOUR, cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = RedisConstants.REDIS_REFRESH, timeUnit = TimeUnit.SECONDS)
    public List<SysPermissionDO> getPermissionAllList() {
        QueryWrapper<SysPermissionDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted_flag", 0);
        return sysPermissionMapper.selectList(queryWrapper);
    }

    /**
     * 获取sysPermissionDOList中的根节点
     *
     * @param sysPermissionDOList
     * @return
     */
    public Set<Long> getParentIdSet(List<SysPermissionDO> sysPermissionDOList) {
        //1.0.根据节点类型分组
        Map<String, List<SysPermissionDO>> typeMap = new HashMap<>();
        for (SysPermissionDO permissionDO : sysPermissionDOList) {
            List<SysPermissionDO> typeList = CollectionUtils.isEmpty(typeMap.get(permissionDO.getPermissionType()))
                    ? new ArrayList<>() : typeMap.get(permissionDO.getPermissionType());
            typeList.add(permissionDO);
            typeMap.put(permissionDO.getPermissionType(), typeList);
        }

        //功能菜单分组
        List<SysPermissionDO> groupPermissionList = typeMap.get(PermissionTypeEnum.GROUP.getPermissionType());
        if (!CollectionUtils.isEmpty(groupPermissionList)) {
            return groupPermissionList.stream().map(SysPermissionDO::getParentId).collect(Collectors.toSet());
        }

        //菜单
        List<SysPermissionDO> menuPermissionList = typeMap.get(PermissionTypeEnum.MENU.getPermissionType());
        if (!CollectionUtils.isEmpty(menuPermissionList)) {
            return menuPermissionList.stream().map(SysPermissionDO::getParentId).collect(Collectors.toSet());
        }

        //功能
        List<SysPermissionDO> featuresPermissionList = typeMap.get(PermissionTypeEnum.FEATURES.getPermissionType());
        if (!CollectionUtils.isEmpty(featuresPermissionList)) {
            return featuresPermissionList.stream().map(SysPermissionDO::getParentId).collect(Collectors.toSet());
        }

        return null;
    }

    /**
     * 获取最终反馈的树结构权限列表
     *
     * @param sysPermissionDOList 所有权限集合
     */
    public List<SysPermissionTreeRespVO> getSysPermissionTreeRespVO(List<SysPermissionDO> sysPermissionDOList, Set<Long> parentIdSet) {
        if (CollectionUtils.isEmpty(sysPermissionDOList)) {
            return new ArrayList<>();
        }

        //权限类型为:G的菜单组集合 用于最终反馈
        List<SysPermissionTreeRespVO> permissionGroupList = new ArrayList<>();

        //所有子节点的权限集合 key=parentId,val=children
        Map<Long, List<SysPermissionTreeRespVO>> parentMap = new HashMap<>();
        for (SysPermissionDO sysPermissionDO : sysPermissionDOList) {
            //list转map 并将DO转化为VO
            SysPermissionTreeRespVO permissionTreeRespVO = MyBeanUtils.dtoToDo(sysPermissionDO, SysPermissionTreeRespVO.class);

            if (parentIdSet.contains(sysPermissionDO.getParentId())) {
                permissionGroupList.add(permissionTreeRespVO);
                continue;
            }

            //list转map 将DO转化为VO 并根据parentId分组
            List<SysPermissionTreeRespVO> children = parentMap.get(sysPermissionDO.getParentId()) == null ? new ArrayList<>() : parentMap.get(sysPermissionDO.getParentId());
            children.add(permissionTreeRespVO);
            parentMap.put(sysPermissionDO.getParentId(), children);
        }

        //设置最终树结构权限
        setSysPermissionTreeRespVO(permissionGroupList, parentMap);

        return permissionGroupList;
    }

    /**
     * 设置最终树结构权限
     *
     * @param parentVOList
     * @param parentMap
     */
    public void setSysPermissionTreeRespVO(List<SysPermissionTreeRespVO> parentVOList, Map<Long, List<SysPermissionTreeRespVO>> parentMap) {
        if (CollectionUtils.isEmpty(parentVOList)) {
            return;
        }

        for (SysPermissionTreeRespVO permissionTreeRespVO : parentVOList) {
            List<SysPermissionTreeRespVO> children = parentMap.get(permissionTreeRespVO.getId());
            if (CollectionUtils.isEmpty(children)) {
                continue;
            }
            permissionTreeRespVO.setChildren(children);

            //递归
            setSysPermissionTreeRespVO(children, parentMap);
        }
    }

    /**
     * 获取最终反馈的树结构权限列表
     *
     * @param sysPermissionDOList 所有权限集合
     */
    public List<SysUserRouteRespVO> getRouteRespVO(List<SysPermissionDO> sysPermissionDOList) {
        if (CollectionUtils.isEmpty(sysPermissionDOList)) {
            return new ArrayList<>();
        }
        //权限类型为:G的菜单组集合 用于最终反馈
        List<SysUserRouteRespVO> userRouteList = new ArrayList<>();
        //所有子节点的权限集合 key=parentId,val=children
        Map<Long, List<SysUserRouteRespVO>> parentMap = new HashMap<>();

        for (SysPermissionDO sysPermissionDO : sysPermissionDOList) {
            //list转map 并将DO转化为VO
            SysUserRouteRespVO userRouteRespVO = permissionToRoute(sysPermissionDO);

            if (sysPermissionDO.getParentId() == null || sysPermissionDO.getParentId().longValue() == 0) {
                userRouteList.add(userRouteRespVO);
                continue;
            }

            //list转map 将DO转化为VO 并根据parentId分组
            List<SysUserRouteRespVO> children = parentMap.get(sysPermissionDO.getParentId()) == null ? new ArrayList<>() : parentMap.get(sysPermissionDO.getParentId());
            children.add(userRouteRespVO);
            parentMap.put(sysPermissionDO.getParentId(), children);
        }

        //设置最终树结构权限
        setSysUserRouteRespVO(userRouteList, parentMap);

        return userRouteList;
    }

    /**
     * 设置最终路由权限
     *
     * @param parentVOList
     * @param parentMap
     */
    public void setSysUserRouteRespVO(List<SysUserRouteRespVO> parentVOList, Map<Long, List<SysUserRouteRespVO>> parentMap) {
        if (CollectionUtils.isEmpty(parentVOList)) {
            return;
        }

        for (SysUserRouteRespVO routeRespVO : parentVOList) {
            List<SysUserRouteRespVO> children = parentMap.get(routeRespVO.getId());
            if (CollectionUtils.isEmpty(children)) {
                continue;
            }
            List<SysUserRouteRespVO> childrenRoute = new ArrayList<>();
            Set<String> roles = new HashSet<>();
            for (SysUserRouteRespVO child : children) {
                if (PermissionTypeEnum.FEATURES.getPermissionType().equalsIgnoreCase(child.getType())) {
                    roles.add(child.getCode());
                } else {
                    childrenRoute.add(child);
                }
            }

            if (!CollectionUtils.isEmpty(roles)) {
                routeRespVO.getMeta().setRoles(new ArrayList<>(roles));
            }

            routeRespVO.setChildren(childrenRoute);

            //递归
            setSysUserRouteRespVO(children, parentMap);
        }
    }

    /**
     * 将sysPermissionDO转换为SysUserRouteRespVO
     *
     * @param sysPermissionDO
     * @return
     */
    public SysUserRouteRespVO permissionToRoute(SysPermissionDO sysPermissionDO) {
        if (sysPermissionDO == null) {
            return null;
        }
        SysUserRouteRespVO.Meta meta = new SysUserRouteRespVO.Meta();
        meta.setTitle(MyStringUtils.isNullParam(sysPermissionDO.getPermissionNameCn()) ? "" : sysPermissionDO.getPermissionNameCn());
        meta.setIcon(MyStringUtils.isNullParam(sysPermissionDO.getPermissionIcon()) ? "" : sysPermissionDO.getPermissionIcon());

        SysUserRouteRespVO sysUserRouteRespVO = new SysUserRouteRespVO();
        sysUserRouteRespVO.setId(sysPermissionDO.getId());
        sysUserRouteRespVO.setComponent(MyStringUtils.isNullParam(sysPermissionDO.getPermissionComponent()) ? "" : sysPermissionDO.getPermissionComponent());
        sysUserRouteRespVO.setCode(MyStringUtils.isNullParam(sysPermissionDO.getPermissionCode()) ? "" : sysPermissionDO.getPermissionCode());
        sysUserRouteRespVO.setPath(MyStringUtils.isNullParam(sysPermissionDO.getPermissionUrl()) ? "" : sysPermissionDO.getPermissionUrl());
        sysUserRouteRespVO.setType(MyStringUtils.isNullParam(sysPermissionDO.getPermissionType()) ? "" : sysPermissionDO.getPermissionType());
        sysUserRouteRespVO.setPId(sysPermissionDO.getParentId());
        sysUserRouteRespVO.setMeta(meta);
        sysUserRouteRespVO.setTitle(sysPermissionDO.getPermissionNameCn());

        if (sysPermissionDO.getIsMultiLevel() != null && sysPermissionDO.getIsMultiLevel()) {
            sysUserRouteRespVO.setAlwaysShow(true);
            sysUserRouteRespVO.setRedirect("noRedirect");
        }

        return sysUserRouteRespVO;
    }

    /**
     * 校验请求参数
     *
     * @param reqVO
     * @return
     */
    private BaseResponse checkPermissionReqVO(SysPermissionReqVO reqVO) {
        if (reqVO == null) {
            return BaseResponse.error(ErrorEnum.EMPTY_ERROR.getCode(), "reqVO:" + ErrorEnum.EMPTY_ERROR.getMsgEn());
        }
        //根据permissionCode查询
        List<SysPermissionDO> sysPermissionDOList = getPermissionList(reqVO.getPermissionCode());
        if (!CollectionUtils.isEmpty(sysPermissionDOList)) {
            if (reqVO.getId() == null) {
                log.info("SysPermissionService.checkPermissionReqVO:permissionCode已存在,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.EXIST_ERROR.getCode(), "permissionCode:" + ErrorEnum.EXIST_ERROR.getMsgEn());
            } else {
                for (SysPermissionDO sysPermissionDO : sysPermissionDOList) {
                    if (sysPermissionDO.getId().longValue() != reqVO.getId().longValue()) {
                        log.info("SysPermissionService.checkPermissionReqVO:permissionCode已存在,reqVO={}", reqVO);
                        return BaseResponse.error(ErrorEnum.EXIST_ERROR.getCode(), "permissionCode:" + ErrorEnum.EXIST_ERROR.getMsgEn());
                    }
                }
            }
        }
        if (reqVO.getId() != null) {
            SysPermissionDO sysPermissionDO = getById(reqVO.getId());
            if (sysPermissionDO == null) {
                log.info("SysPermissionService.checkPermissionReqVO:sysPermissionDO不存在,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.NOT_EXIST_ERROR.getCode(), "sysPermissionDO:" + ErrorEnum.NOT_EXIST_ERROR.getMsgEn());
            }

            if (MyStringUtils.isNotNullParam(reqVO.getPermissionCode()) && !reqVO.getPermissionCode().equalsIgnoreCase(sysPermissionDO.getPermissionCode())) {
                log.info("SysPermissionService.checkPermissionReqVO:permissionCode不能更新,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.UPDATED_ERROR.getCode(), "permissionCode:" + ErrorEnum.UPDATED_ERROR.getMsgEn());
            }
        }
        if (!PermissionTypeEnum.GROUP.getPermissionType().equalsIgnoreCase(reqVO.getPermissionType()) && reqVO.getParentId() != null) {
            SysPermissionDO permissionDO = getById(reqVO.getParentId());
            if (permissionDO == null || permissionDO.getId() == null) {
                log.info("SysPermissionService.checkPermissionReqVO:parentId不存在,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.NOT_EXIST_ERROR.getCode(), "parentId:" + ErrorEnum.NOT_EXIST_ERROR.getMsgEn());
            }
        }

        return BaseResponse.success();
    }
}
