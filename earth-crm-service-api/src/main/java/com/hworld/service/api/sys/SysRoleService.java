package com.hworld.service.api.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hworld.base.BasePagedResponse;
import com.hworld.base.BaseReqPage;
import com.hworld.base.BaseResponse;
import com.hworld.entity.sys.SysRoleDO;
import com.hworld.vo.req.sys.SysRoleChangeReqVO;
import com.hworld.vo.req.sys.SysRoleReqVO;

import java.util.List;

/**
 * 角色维护业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2021-11-30 11:22:09
 **/
public interface SysRoleService extends IService<SysRoleDO> {

    /**
     * 添加角色信息
     *
     * @param reqVO
     * @return
     */
    BaseResponse addRole(SysRoleChangeReqVO reqVO);

    /**
     * 删除角色信息
     *
     * @param reqVO
     * @return
     */
    BaseResponse deleltRole(SysRoleReqVO reqVO);

    /**
     * 更新角色信息
     *
     * @param reqVO
     * @return
     */
    BaseResponse updateRole(SysRoleChangeReqVO reqVO);

    /**
     * 查询角色列表(不分页)
     *
     * @param reqVO
     * @return
     */
    BaseResponse getRoleList(SysRoleReqVO reqVO);

    /**
     * 查询角色列表(分页)
     *
     * @param reqPage
     * @return
     */
    BasePagedResponse getRolePage(BaseReqPage<SysRoleReqVO> reqPage);

    /**
     * 查询角色详情
     *
     * @param reqVO
     * @return
     */
    BaseResponse getRoleOne(SysRoleReqVO reqVO);


    /**
     * 获取超级管理员角色列表
     *
     * @return
     */
    List<SysRoleDO> getAdminRoleList();

    /**
     * 根据roleCode查询roleList
     *
     * @param roleCode
     * @return
     */
    List<SysRoleDO> getRoleListByRoleCode(String roleCode);


    /**
     * 根据userId 查询用户拥有的角色信息
     *
     * @param userId
     * @return
     */
    List<SysRoleDO> getListByUserId(Long userId);
}
