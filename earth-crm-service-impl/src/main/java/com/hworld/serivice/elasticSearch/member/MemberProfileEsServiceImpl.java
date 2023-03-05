package com.hworld.serivice.elasticSearch.member;

import com.alibaba.fastjson.JSONObject;
import com.hworld.base.BaseResponse;
import com.hworld.bo.es.EsBaseBO;
import com.hworld.bo.es.EsSaveBO;
import com.hworld.bo.es.EsUpdateBO;
import com.hworld.constants.Constants;
import com.hworld.entity.member.MemberProfileDO;
import com.hworld.enums.ErrorEnum;
import com.hworld.enums.KafKaTopicEnum;
import com.hworld.service.api.elasticSearch.member.MemberProfileEsService;
import com.hworld.utils.EsUtils;
import com.hworld.utils.MyStringUtils;
import com.hworld.vo.req.member.MemberProfileReqVO;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 会员业务实现
 *
 * @author caoyang
 * @date 2022-02-15 17:26:20
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberProfileEsServiceImpl implements MemberProfileEsService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private EsUtils esUtils;

    /**
     * 创建会员
     *
     * @param reqDO
     * @return
     */
    @Override
    public BaseResponse memberEsRegister(MemberProfileDO reqDO) {
        try {
            // 创建索引请求对象
            EsSaveBO esSaveBO = new EsSaveBO();
            esSaveBO.setEsId(String.valueOf(reqDO.getId()));
            esSaveBO.setBaseDO(reqDO);
            esSaveBO.setIndex(Constants.CRM_WORLD_MEMBER);
            esSaveBO.setType("doc");

            //ES新增数据
            esUtils.saveOrUpdate(esSaveBO, MemberProfileDO.class);
            return BaseResponse.success();
        } catch (Exception e) {
            log.error("memberEsRegister error:", e);
            return BaseResponse.error(ErrorEnum.ES_SAVE_ERROR.getMsgEn());
        }
    }

    /**
     * 修改会员
     *
     * @param reqDO
     * @return
     */
    @Override
    public BaseResponse updateEsMember(MemberProfileDO reqDO) {
        try {
            // 创建索引请求对象
            EsUpdateBO esUpdateBO = new EsUpdateBO();
            esUpdateBO.setEsId(String.valueOf(reqDO.getId()));
            esUpdateBO.setBaseDO(reqDO);
            esUpdateBO.setIndex(Constants.CRM_WORLD_MEMBER);
            esUpdateBO.setType("doc");

            //ES修改数据
            esUtils.update(esUpdateBO);
            return BaseResponse.success();
        } catch (Exception e) {
            log.error("updateEsMember error:", e);
            return BaseResponse.error(ErrorEnum.ES_SAVE_ERROR.getMsgEn());
        }
    }

    /**
     * 删除会员(伪删除)
     *
     * @param reqDO
     * @return
     */
    @Override
    public BaseResponse updateDeletedFlagEsMember(MemberProfileDO reqDO) {
        try {
            reqDO.setDeletedFlag(false);

            // 创建索引请求对象
            EsUpdateBO esUpdateBO = new EsUpdateBO();
            esUpdateBO.setBaseDO(reqDO);
            esUpdateBO.setEsId(String.valueOf(reqDO.getId()));
            esUpdateBO.setIndex(Constants.CRM_WORLD_MEMBER);
            esUpdateBO.setType("doc");

            //ES修改数据
            esUtils.update(esUpdateBO);
            return BaseResponse.success();
        } catch (Exception e) {
            log.error("ES逻辑删除失败:", e);
            return BaseResponse.error("ES逻辑删除失败");
        }
    }

    /**
     * 删除会员(物理删除)
     *
     * @param reqDO
     * @return
     */
    @Override
    public BaseResponse deleteEsMember(MemberProfileDO reqDO) {
        try {
            // 创建索引请求对象
            EsBaseBO esBaseBO = new EsBaseBO();
            esBaseBO.setEsId(String.valueOf(reqDO.getId()));
            esBaseBO.setIndex(Constants.CRM_WORLD_MEMBER);
            esBaseBO.setType("doc");

            //ES修改数据
            esUtils.delete(esBaseBO);
            return BaseResponse.success();
        } catch (Exception e) {
            log.error("ES会员修改失败:", e);
            return BaseResponse.error("ES会员修改失败");
        }
    }

    /**
     * 根据ID查询会员
     *
     * @param reqVO
     * @return
     */
    @Override
    public BaseResponse getMemberEsByID(MemberProfileReqVO reqVO) {
        try {
            EsBaseBO esBaseBO = new EsBaseBO();
            esBaseBO.setEsId(String.valueOf(reqVO.getId()));
            esBaseBO.setIndex(KafKaTopicEnum.CRM_WORLD_MEMBER.getTopic());
            esBaseBO.setType("doc");

            MemberProfileDO memberProfileDO = esUtils.getEsId(esBaseBO, MemberProfileDO.class);
            return BaseResponse.success(memberProfileDO);
        } catch (Exception e) {
            log.error("ES会员查询失败:", e);
            return BaseResponse.error("ES会员查询失败");
        }
    }


    public BaseResponse queryByVO(MemberProfileReqVO reqVO) {
        BoolQueryBuilder queryBuilder = buildFuzzQueryBuilder(reqVO);
        // 暂时写死查100个
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(queryBuilder).size(100);
        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder);
        // 设置查询范围
        searchRequest.indices(KafKaTopicEnum.CRM_WORLD_MEMBER.getTopic());

        List<MemberProfileDO> entities = new ArrayList<>();
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            Arrays.sort(hits, (h1, h2) -> (int) (h2.getScore() - h1.getScore()));
            for (SearchHit hit : hits) {
                String jsonString = hit.getSourceAsString();
                entities.add(JSONObject.parseObject(jsonString, MemberProfileDO.class));
            }
        } catch (IOException e) {
            log.warn("search failed.", e);
        }
        return BaseResponse.success(entities);
    }

    // 构建查询
    private BoolQueryBuilder buildFuzzQueryBuilder(MemberProfileReqVO reqVO) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (MyStringUtils.isNotNullParam(reqVO.getFirstName())) {
            // 模糊匹配
            boolQueryBuilder.filter(QueryBuilders.wildcardQuery("firstName.keyword", reqVO.getFirstName()));
        }

        if (MyStringUtils.isNotNullParam(reqVO.getEmail())) {
            // 模糊匹配
            MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("email", reqVO.getEmail());
            boolQueryBuilder.filter(queryBuilder);
        }
        return boolQueryBuilder;
    }
}
