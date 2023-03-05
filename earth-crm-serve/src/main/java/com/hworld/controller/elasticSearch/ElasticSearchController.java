package com.hworld.controller.elasticSearch;

import com.hworld.base.BaseResponse;
import com.hworld.service.api.elasticSearch.member.MemberProfileEsService;
import com.hworld.vo.req.member.MemberProfileReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统配置 Controller
 *
 * @author caoyang
 * @date 2022-02-08 16:00:49
 */
@Api(value = "ElasticSearch查询", tags = "ElasticSearch查询")
@RestController
@RequestMapping("/es")
@Slf4j
@Validated
public class ElasticSearchController {


    @Autowired
    private MemberProfileEsService memberProfileEsService;

    /**
     * 模糊查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/getMemberEsByID")
    @ApiModelProperty("模糊查询")
    @ResponseBody
    public BaseResponse getMemberEsByID(@RequestBody MemberProfileReqVO reqVO) {
        return memberProfileEsService.getMemberEsByID(reqVO);
    }
}