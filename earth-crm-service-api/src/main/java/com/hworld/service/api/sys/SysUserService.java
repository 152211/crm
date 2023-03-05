package com.hworld.service.api.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hworld.base.BasePagedResponse;
import com.hworld.base.BaseReqPage;
import com.hworld.base.BaseResponse;
import com.hworld.entity.sys.*;
import com.hworld.vo.req.sys.SysUserChangeReqVO;
import com.hworld.vo.req.sys.SysUserReqVO;

import java.util.List;

/**
 * 用户用户对应关系业务接口
 *
 * @author caoyang
 * @version 1.0
 * @date 2021-11-30 11:22:10
 **/
public interface SysUserService extends IService<SysUserDO> {
    /**
     * 根据employeeNo 工号查询用户信息
     *
     * @param employeeNo
     * @return
     */
    SysUserDO getOneByEmployeeNo(String employeeNo);

    /**
     * 根据userID查询用户信息
     *
     * @param userId
     * @return
     */
    SysUserDO getOneById(Long userId);

    /**
     * 根据employeeNo 工号查询用户信息
     *
     * @param employeeNo
     * @return
     */
    List<SysUserDO> selectListByEmployeeNo(String employeeNo);


    /**
     * 获取用户列表(不分页)
     *
     * @param reqVO
     * @return
     */
    BaseResponse getUserList(SysUserReqVO reqVO);

    /**
     * 获取用户列表(分页)
     *
     * @param reqPage
     * @return
     */
    BasePagedResponse getUserPage(BaseReqPage<SysUserReqVO> reqPage);

    /**
     * 获取用户详情
     *
     * @param reqVO
     * @return
     */
    BaseResponse getUserInfo(SysUserReqVO reqVO);

    /**
     * 查询用户权限信息
     *
     * @param reqVO
     * @return
     */
    BaseResponse getUserPermissionTree(SysUserReqVO reqVO);

    /**
     * 查询用户菜单信息
     *
     * @return
     */
    BaseResponse getRouter(Long userId);

    /**
     * 获取用户权限code列表
     *
     * @param userId
     * @return
     */
    BaseResponse getUserPermissionCode(Long userId);

    /**
     * 获取酒店列表
     *
     * @param userId
     * @return
     */
    BaseResponse getUserHotelByUserId(Long userId);

    /**
     * 添加用户信息
     *
     * @param reqVO
     * @return
     */
    BaseResponse addUser(SysUserChangeReqVO reqVO);

    /**
     * 伪删除用户信息
     *
     * @param reqVO
     * @return
     */
    BaseResponse deleltUser(SysUserReqVO reqVO);

    /**
     * 更新用户信息
     *
     * @param reqVO
     * @return
     */
    BaseResponse updateUser(SysUserChangeReqVO reqVO);

}
