package com.hworld.serivice.login;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.hworld.base.BaseResponse;
import com.hworld.entity.sys.SysUserDO;
import com.hworld.enums.ErrorEnum;
import com.hworld.utils.MyStringUtils;
import com.hworld.vo.req.LoginReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
public class CrmLoginServiceImpl extends LoginServiceImpl {

    @Value("${earthCrm.salt}")
    private String salt;

    @Override
    public BaseResponse setLoginReqVO(HttpServletRequest request, HttpServletResponse response, LoginReqVO reqVO) {
        return BaseResponse.success();
    }

    /**
     * 校验请求参数
     *
     * @return
     */
    @Override
    public BaseResponse checkReqData(LoginReqVO reqVO) {
        if (reqVO == null) {
            return BaseResponse.error(ErrorEnum.EMPTY_ERROR.getCode(), "user" + ErrorEnum.EMPTY_ERROR.getMsgEn());
        }
        if (MyStringUtils.isNullParam(reqVO.getEmployeeNo())) {
            return BaseResponse.error(ErrorEnum.EMPTY_ERROR.getCode(), "employeeNo" + ErrorEnum.EMPTY_ERROR.getMsgEn());
        }
        if (MyStringUtils.isNullParam(reqVO.getPassWord())) {
            return BaseResponse.error(ErrorEnum.EMPTY_ERROR.getCode(), "passWord" + ErrorEnum.EMPTY_ERROR.getMsgEn());
        }
        return BaseResponse.success();
    }


    /**
     * 校验用户信息
     *
     * @param sysUserDO   用户信息
     * @param passWordReq 用户输入的密码
     * @return
     */
    @Override
    public BaseResponse checkUser(SysUserDO sysUserDO, String passWordReq) {
        if (sysUserDO == null) {
            return BaseResponse.error(ErrorEnum.USER_NOT_EXIST_ERROR.getCode(), ErrorEnum.USER_NOT_EXIST_ERROR.getMsgEn());
        }
        //对密码进行加密
        String passWord = SaSecureUtil.md5BySalt(passWordReq, salt);

        if (!passWord.equals(sysUserDO.getPassWord())) {
            return BaseResponse.error(ErrorEnum.PASSWORD_ERROR.getCode(), ErrorEnum.PASSWORD_ERROR.getMsgEn());
        }

        return BaseResponse.success();
    }

    @Override
    public BaseResponse getSsoAuth(String token) {
        return BaseResponse.success();
    }
}
