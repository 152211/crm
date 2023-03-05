package com.hworld.serivice.sys;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
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
import com.hworld.entity.sys.SysRoleDO;
import com.hworld.entity.sys.SysUserDO;
import com.hworld.entity.sys.SysUserPermissionDO;
import com.hworld.entity.sys.SysUserRoleDO;
import com.hworld.enums.ErrorEnum;
import com.hworld.mapper.sys.SysUserMapper;
import com.hworld.service.api.async.sys.SysRedisAsyncService;
import com.hworld.service.api.sys.*;
import com.hworld.utils.MyBeanUtils;
import com.hworld.utils.MyStringUtils;
import com.hworld.utils.RedisUtil;
import com.hworld.utils.WrapperUtiles;
import com.hworld.vo.req.sys.SysUserChangeReqVO;
import com.hworld.vo.req.sys.SysUserReqVO;
import com.hworld.vo.resp.sys.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements SysUserService {

    @Autowired
    public SysUserMapper sysUserMapper;

    @Autowired
    public SysRoleService sysRoleService;

    @Autowired
    public SysUserRoleService sysUserRoleService;

    @Autowired
    public SysUserPermissionService sysUserPermissionService;

    @Autowired
    public SysPermissionService sysPermissionService;

    @Autowired
    public SysUserHotelService sysUserHotelService;

    @Autowired
    public SysRedisAsyncService sysRedisAsyncService;

    @Value("${earthCrm.salt}")
    private String salt;

    /**
     * 根据employeeNo 工号查询用户信息
     *
     * @param employeeNo
     * @return
     */
    @Override
    public SysUserDO getOneByEmployeeNo(String employeeNo) {
        List<SysUserDO> list = selectListByEmployeeNo(employeeNo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据userID查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    @Cached(area = "default", name = RedisConstants.CRM_USER, key = "#userId",
            expire = RedisConstants.REDIS_EXPIRE, cacheType = CacheType.REMOTE)
    public SysUserDO getOneById(Long userId) {
        return getById(userId);
    }

    /**
     * 根据employeeNo 工号查询用户信息
     *
     * @param employeeNo
     * @return
     */
    @Override
    public List<SysUserDO> selectListByEmployeeNo(String employeeNo) {

        QueryWrapper<SysUserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_no", employeeNo);
        queryWrapper.eq("deleted_flag", Constants.ZERO);
        return sysUserMapper.selectList(queryWrapper);
    }

    /**
     * 获取用户列表(不分页)
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse getUserList(SysUserReqVO reqVO) {
        List<SysUserRespVO> resultList = new ArrayList<>();

        SysUserDO queryDO = MyBeanUtils.dtoToDo(reqVO, SysUserDO.class);
        QueryWrapper<SysUserDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        queryWrapper.eq("deleted_flag", 0);
        List<SysUserDO> list = sysUserMapper.selectList(queryWrapper);

        for (SysUserDO sysUserDO : list) {
            resultList.add(MyBeanUtils.doToVo(sysUserDO, SysUserRespVO.class));
        }

        return BaseResponse.success(resultList);
    }

    /**
     * 获取用户列表(分页)
     *
     * @param reqPage
     * @return
     */
    @Override
    public BasePagedResponse getUserPage(BaseReqPage<SysUserReqVO> reqPage) {

        SysUserReqVO reqVO = reqPage.getParams() == null ? new SysUserReqVO() : reqPage.getParams();
        SysUserDO queryDO = MyBeanUtils.voTodo(reqVO, SysUserDO.class);
        QueryWrapper<SysUserDO> queryWrapper = WrapperUtiles.entityToWrapper(queryDO);
        queryWrapper.eq("deleted_flag", 0);


        Page<SysUserDO> page = new Page<>(reqPage.getPage(), reqPage.getRows());
        IPage<SysUserDO> iPage = sysUserMapper.selectPage(page, queryWrapper);

        List<SysUserRespVO> resultList = new ArrayList<>();
        if (CollectionUtils.isEmpty(iPage.getRecords())) {
            return BasePagedResponse.success(iPage.getTotal(), resultList);
        }
        for (SysUserDO sysUserDO : iPage.getRecords()) {
            resultList.add(MyBeanUtils.doToVo(sysUserDO, SysUserRespVO.class));
        }

        return BasePagedResponse.success(iPage.getTotal(), resultList);
    }

    /**
     * 获取用户详情
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse getUserInfo(SysUserReqVO reqVO) {
        //1.0.查询用户信息
        SysUserDO user = getOneById(reqVO.getId());
        if (user == null) {
            return BaseResponse.success();
        }

        SysUserDetailsRespVO userDetailsRespVO = MyBeanUtils.doToVo(user, SysUserDetailsRespVO.class);
        userDetailsRespVO.setIsAdmin(false);

        //1.1.查询用户角色信息
        List<SysRoleDO> userRoleList = sysRoleService.getListByUserId(reqVO.getId());
        List<SysRoleRespVO> roleRespVOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userRoleList)) {
            //设置是否是管理员标识
            for (SysRoleDO role : userRoleList) {
                roleRespVOList.add(MyBeanUtils.doToVo(role, SysRoleRespVO.class));
                if (role.getIsAdmin() != null && role.getIsAdmin()) {
                    userDetailsRespVO.setIsAdmin(true);
                }
            }
        }

        //1.2.查询用户权限信息
        List<SysPermissionTreeRespVO> userPermissionList = sysPermissionService.getPermissionTreeByUserId(reqVO.getId());
        //1.3.查询用户酒店列表信息
        List<SysUserHotelRespVO> userHotelList = sysUserHotelService.getUserHotelByUserId(reqVO.getId());

        Set<Long> roleIdSet = null;
        Set<Long> hotelIdSet = null;
        Set<String> hotelNoSet = null;
        if (!CollectionUtils.isEmpty(userRoleList)) {
            roleIdSet = userRoleList.stream().map(SysRoleDO::getId).collect(Collectors.toSet());
        }
        if (!CollectionUtils.isEmpty(userHotelList)) {
            roleIdSet = userRoleList.stream().map(SysRoleDO::getId).collect(Collectors.toSet());
            hotelIdSet = userHotelList.stream().map(SysUserHotelRespVO::getHotelId).collect(Collectors.toSet());
            hotelNoSet = userHotelList.stream().map(SysUserHotelRespVO::getHotelNo).collect(Collectors.toSet());
        }
        userDetailsRespVO.setRoleList(roleRespVOList == null ? new ArrayList<>() : roleRespVOList);
        userDetailsRespVO.setPermissionList(userPermissionList == null ? new ArrayList<>() : userPermissionList);
        userDetailsRespVO.setHotelList(userHotelList == null ? new ArrayList<>() : userHotelList);
        userDetailsRespVO.setRoleIdList(roleIdSet == null ? new ArrayList<>() : new ArrayList<>(roleIdSet));
        userDetailsRespVO.setHotelIdList(hotelIdSet == null ? new ArrayList<>() : new ArrayList<>(hotelIdSet));
        userDetailsRespVO.setHotelNoList(hotelNoSet == null ? new ArrayList<>() : new ArrayList<>(hotelNoSet));

        return BaseResponse.success(userDetailsRespVO);
    }

    /**
     * 查询用户权限信息
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse getUserPermissionTree(SysUserReqVO reqVO) {
        //1.0.查询用户信息
        SysUserDO user = getOneById(reqVO.getId());
        if (user == null) {
            return BaseResponse.success();
        }
        List<SysPermissionTreeRespVO> userPermissionList = sysPermissionService.getPermissionTreeByUserId(reqVO.getId());

        return BaseResponse.success(userPermissionList);
    }

    /**
     * 查询当前用户菜单信息
     *
     * @return
     */
    @Override
    public BaseResponse getRouter(Long userId) {
        List<SysUserRouteRespVO> userPermissionList = sysPermissionService.getUserRouter(userId);

        return BaseResponse.success(userPermissionList);
    }

    @Override
    public BaseResponse getUserPermissionCode(Long userId) {
        if (userId == null) {
            return BaseResponse.success();
        }
        return BaseResponse.success(sysUserPermissionService.selectUserPermissionCodeByUserId(userId));
    }

    @Override
    public BaseResponse getUserHotelByUserId(Long userId) {
        if (userId == null) {
            return BaseResponse.success();
        }
        return BaseResponse.success(sysUserHotelService.getUserHotelByUserId(userId));
    }

    /**
     * 添加用户信息
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse addUser(SysUserChangeReqVO reqVO) {
        //1.0.检验参数
        BaseResponse response = checkUserReqVO(reqVO);
        if (!ErrorEnum.SUCCESS.getCode().equals(response.getCode())) {
            return response;
        }

        //1.1.添加用户
        SysUserDO sysUserDO = MyBeanUtils.dtoToDo(reqVO, SysUserDO.class);
        String passWord = MyStringUtils.isNotNullParam(reqVO.getPassWord()) ? SaSecureUtil.md5BySalt(reqVO.getPassWord(), salt) : SaSecureUtil.md5BySalt("test@123", salt);
        sysUserDO.setPassWord(passWord);
        this.save(sysUserDO);

        if (CollectionUtils.isEmpty(reqVO.getRoleIdList())) {
            return BaseResponse.success();
        }

        //1.2.根据roleIds查询角色信息
        List<SysRoleDO> roleList = sysRoleService.listByIds(new HashSet<>(reqVO.getRoleIdList()));
        if (CollectionUtils.isEmpty(roleList)) {
            return BaseResponse.success();
        }

        //1.3.添加用户角色关联信息
        sysUserRoleService.saveBatch(sysUserDO.getId(), roleList);

        //1.4.添加用户权限关联信息
        sysUserPermissionService.saveUserPermissionBatch(sysUserDO.getId(), reqVO.getRoleIdList());

        return BaseResponse.success(MyBeanUtils.doToVo(sysUserDO, SysUserRespVO.class));
    }

    /**
     * 伪删除用户信息
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse deleltUser(SysUserReqVO reqVO) {
        SysUserDO oldUser = getById(reqVO.getId());
        if (oldUser == null) {
            log.info("SysUserService.deleltUser:userId不存在,reqVO={}", reqVO);
            return BaseResponse.error(ErrorEnum.NOT_EXIST_ERROR.getCode(), "userId:" + ErrorEnum.NOT_EXIST_ERROR.getMsgEn());
        }

        //1.0.推出删除用户的登录信息
        StpUtil.login(reqVO.getId());

        //1.1.删除用户角色信息
        sysUserRoleService.removeByUserId(reqVO.getId());
        //1.2.删除用户权限信息
        sysUserPermissionService.removeByUserId(reqVO.getId());

        //1.3.伪删除用户信息
        SysUserDO newUser = new SysUserDO();
        newUser.setId(reqVO.getId());
        newUser.setDeletedFlag(true);
        updateById(newUser);

        //1.4.删除redis中用户信息
        sysRedisAsyncService.delUserAsync(reqVO.getId());

        return BaseResponse.success();
    }

    /**
     * 更新用户信息
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse updateUser(SysUserChangeReqVO reqVO) {
        //1.0.检验参数
        BaseResponse response = checkUserReqVO(reqVO);
        if (!ErrorEnum.SUCCESS.getCode().equals(response.getCode())) {
            return response;
        }

        //1.1.修改用户信息
        SysUserDO sysUserDO = MyBeanUtils.dtoToDo(reqVO, SysUserDO.class);
        this.updateById(sysUserDO);

        //需要添加的角色
        Set<Long> addRoleIdSet = new HashSet<>();
        //需要删除的角色
        Set<Long> removeRoleIdSet = new HashSet<>();

        //1.2.更新用户角色信息
        sysUserRoleService.renewUserRoleBatch(reqVO.getId(), reqVO.getRoleIdList(), addRoleIdSet, removeRoleIdSet);

        //1.3.更新用户权限信息
        sysUserPermissionService.renewUserPermissionBatch(reqVO.getId(), addRoleIdSet, removeRoleIdSet);

        //1.4.删除redis中用户信息
        sysRedisAsyncService.delUserAsync(reqVO.getId());

        return BaseResponse.success();
    }


    /**
     * 校验请求参数
     *
     * @param reqVO
     * @return
     */
    private BaseResponse checkUserReqVO(SysUserReqVO reqVO) {
        if (reqVO == null) {
            return BaseResponse.error("reqVO is empty");
        }
        //1.0.使用员工工号查询
        List<SysUserDO> userList = selectListByEmployeeNo(reqVO.getEmployeeNo());

        if (!CollectionUtils.isEmpty(userList)) {
            if (reqVO.getId() == null) {
                log.info("SysUserService.checkUserReqVO:employeeNo已存在,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.EXIST_ERROR.getCode(), "employeeNo:" + ErrorEnum.EXIST_ERROR.getMsgEn());
            } else {
                for (SysUserDO user : userList) {
                    if (user.getId().longValue() != reqVO.getId().longValue()) {
                        log.info("SysUserService.checkUserReqVO:employeeNo已存在,reqVO={}", reqVO);
                        return BaseResponse.error(ErrorEnum.EXIST_ERROR.getCode(), "employeeNo:" + ErrorEnum.EXIST_ERROR.getMsgEn());
                    }
                }
            }
        }

        if (reqVO.getId() != null) {
            SysUserDO user = getById(reqVO.getId());
            if (user == null) {
                log.info("SysUserService.checkUserReqVO:user不存在,reqVO={}", reqVO);
                return BaseResponse.error(ErrorEnum.NOT_EXIST_ERROR.getCode(), "userId:" + ErrorEnum.NOT_EXIST_ERROR.getMsgEn());
            }
        }
        return BaseResponse.success();
    }
}
