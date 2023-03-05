package com.hworld.serivice.prof;

import com.earth.diana.bo.apol.BProfAddrInfo;
import com.earth.diana.bo.apol.ext.IndividualVo;
import com.earth.diana.pojo.base.ResBean;
import com.hworld.base.BaseResponse;
import com.hworld.enums.ErrorEnum;
import com.hworld.service.api.prof.ProfileService;
import com.hworld.utils.MyStringUtils;
import com.hworld.utils.RestTemplateUtil;
import com.hworld.vo.req.prof.ProfBaseIndReqVO;
import com.hworld.vo.req.prof.ProfileSearchReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员业务实现
 *
 * @author caoyang
 * @date 2022-02-15 17:26:20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private HttpServletRequest request;

    @Value("${base.site.prof-url}")
    private String profUrl;

    /**
     * 保存个人客例
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse saveIndividualProf(IndividualVo req) {
        if (req == null) {
            return BaseResponse.error(ErrorEnum.EMPTY_ERROR.getCode(), "IndividualVo" + ErrorEnum.EMPTY_ERROR.getMsgEn());
        }
        //发送http请求
        ResponseEntity<ResBean> response = restTemplateUtil.post(profUrl + "profile/saveIndividual", getHeaderMap(), req, ResBean.class);

        //校验返回参数
        return checkResp(response);
    }

    /**
     * 根据档案ID查询档案详情
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse getProfOne(ProfileSearchReqVO req) {
        if (req == null) {
            return BaseResponse.error(ErrorEnum.EMPTY_ERROR.getCode(), "ProfileSearchReqVO" + ErrorEnum.EMPTY_ERROR.getMsgEn());
        }
        //发送http请求
        ResponseEntity<ResBean> response = restTemplateUtil.post(profUrl + "profile/getOne", getHeaderMap(), req, ResBean.class);

        //校验返回参数
        return checkResp(response);
    }

    /**
     * 查询档案基本信息
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse getProfBaseIndOne(ProfBaseIndReqVO req) {
        if (req == null) {
            return BaseResponse.error(ErrorEnum.EMPTY_ERROR.getCode(), "BProfBaseInd" + ErrorEnum.EMPTY_ERROR.getMsgEn());
        }
        //发送http请求
        ResponseEntity<ResBean> response = restTemplateUtil.post(profUrl + "profBaseInd/getOne", getHeaderMap(), req, ResBean.class);

        //校验返回参数
        return checkResp(response);
    }

    /**
     * 查询地址信息表
     *
     * @param req
     * @return
     */
    @Override
    public BaseResponse getProfAddrInfoAll(BProfAddrInfo req) {
        if (req == null) {
            return BaseResponse.error(ErrorEnum.EMPTY_ERROR.getCode(), "BProfAddrInfo" + ErrorEnum.EMPTY_ERROR.getMsgEn());
        }
        //发送http请求
        ResponseEntity<ResBean> response = restTemplateUtil.post(profUrl + "profAddrInfo/listAllForCRM", getHeaderMap(), req, ResBean.class);

        return checkResp(response);
    }

    /**
     * 校验返回参数
     *
     * @param response
     * @return
     */
    public BaseResponse checkResp(ResponseEntity<ResBean> response) {
        if (response == null || response.getBody() == null) {
            return BaseResponse.error(ErrorEnum.EMPTY_ERROR.getCode(), "response body:" + ErrorEnum.EMPTY_ERROR.getMsgEn());
        }

        ResBean apolResp = response.getBody();

        if (apolResp.getCode() != 200) {
            return BaseResponse.error(ErrorEnum.NOT_EXIST_ERROR.getCode(), apolResp.getErrMsg());
        }
        return BaseResponse.success(apolResp.getObj());
    }


    public Map<String, String> getHeaderMap() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("auth-type", "auth-no");
        if (MyStringUtils.isNotNullParam(request.getHeader("timezone"))) {
            headerMap.put("access-timezone", request.getHeader("timezone"));
        }
        if (MyStringUtils.isNotNullParam(request.getHeader("Language"))) {
            headerMap.put("Accept-Language", request.getHeader("Language"));
        }
        if (MyStringUtils.isNotNullParam(request.getHeader("profile-profId"))) {
            headerMap.put("profile-profId", request.getHeader("profile-profId"));
        }
        if (MyStringUtils.isNotNullParam(request.getHeader("auth-type"))) {
            headerMap.put("auth-type", request.getHeader("auth-type"));
        }
        if (MyStringUtils.isNotNullParam(request.getHeader("application-token"))) {
            headerMap.put("application-token", request.getHeader("application-token"));
        }
        if (MyStringUtils.isNotNullParam(request.getHeader("access-key"))) {
            headerMap.put("access-key", request.getHeader("access-key"));
        }
        if (MyStringUtils.isNotNullParam(request.getHeader("access-sign"))) {
            headerMap.put("access-sign", request.getHeader("access-sign"));
        }
        if (MyStringUtils.isNotNullParam(request.getHeader("login-channel"))) {
            headerMap.put("login-channel", request.getHeader("login-channel"));
        }
        return headerMap;
    }
}
