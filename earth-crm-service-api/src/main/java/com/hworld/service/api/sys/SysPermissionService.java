package com.hworld.service.api.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hworld.base.BasePagedResponse;
import com.hworld.base.BaseReqPage;
import com.hworld.base.BaseResponse;
import com.hworld.entity.sys.SysPermissionDO;
import com.hworld.vo.req.sys.SysParentReqVO;
import com.hworld.vo.req.sys.SysPermissionReqVO;
import com.hworld.vo.resp.sys.SysPermissionTreeRespVO;
import com.hworld.vo.resp.sys.SysUserRouteRespVO;

import java.util.List;

/**
 * 系统权限业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2021-11-30 11:22:06
 **/
public interface SysPermissionService extends IService<SysPermissionDO> {

    /**
     * 添加权限信息
     *
     * @param reqVO
     * @return
     */
    BaseResponse addPermission(SysPermissionReqVO reqVO);

    /**
     * 删除权限信息
     *
     * @param reqVO
     * @return
     */
    BaseResponse deleltPermission(SysPermissionReqVO reqVO);

    /**
     * 更新权限信息
     *
     * @param reqVO
     * @return
     */
    BaseResponse updatePermission(SysPermissionReqVO reqVO);

    /**
     * 获取权限列表(不分页)
     *
     * @param reqVO
     * @return
     */
    BaseResponse getPermissionList(SysPermissionReqVO reqVO);

    /**
     * 获取权限树结构
     *
     * @param reqVO
     * @return
     */
    BaseResponse getPermissionTree(SysPermissionReqVO reqVO);

    /**
     * 查询权限列表(分页)
     *
     * @param reqPage
     * @return
     */
    BasePagedResponse getPermissionPage(BaseReqPage<SysPermissionReqVO> reqPage);

    /**
     * 获取权限详情
     *
     * @param reqVO
     * @return
     */
    BaseResponse getPermissionOne(SysPermissionReqVO reqVO);

    /**
     * 获取父级权限列表
     *
     * @param reqVO
     * @return
     */
    BaseResponse getParentList(SysParentReqVO reqVO);

    /**
     * 根据userId获取用户权限树结构
     *
     * @param userId
     * @return
     */
    List<SysPermissionTreeRespVO> getPermissionTreeByUserId(Long userId);

    /**
     * 根据userId获取用户路由权限
     *
     * @param userId
     * @return
     */
    List<SysUserRouteRespVO> getUserRouter(Long userId);

    /**
     * 根据permissionCode查询权限列表
     *
     * @param permissionCode
     * @return
     */
    List<SysPermissionDO> getPermissionList(String permissionCode);

    /**
     * 根据parent_id查询权限列表
     *
     * @param parentId
     * @return
     */
    List<SysPermissionDO> getPermissionListByParentId(Long parentId);

    /**
     * 根据roleId查询权限列表
     *
     * @param roleId
     * @return
     */
    List<SysPermissionDO> getPermissionListByRoleList(Long roleId);

    /**
     * 查询所有权限列表
     *
     * @return
     */
    List<SysPermissionDO> getPermissionAllList();
}
