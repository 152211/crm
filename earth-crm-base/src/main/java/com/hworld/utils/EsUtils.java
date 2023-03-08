package com.hworld.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hworld.base.es.BaseEsPagedResponse;
import com.hworld.base.es.BaseEsReq;
import com.hworld.base.es.BaseEsReqPage;
import com.hworld.base.es.BaseEsResponse;
import com.hworld.bo.es.EsBaseBO;
import com.hworld.bo.es.EsSaveBO;
import com.hworld.bo.es.EsUpdateBO;
import com.hworld.constants.DatePatternConstant;
import com.hworld.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
@Slf4j
public class EsUtils {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    /**
     * 新增ES数据
     *
     * @param esSaveBO
     * @return
     */
    public RestStatus save(EsSaveBO esSaveBO) throws IOException {
        IndexRequest indexRequest = new IndexRequest(esSaveBO.getIndex(), esSaveBO.getType(), esSaveBO.getEsId());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(esSaveBO.getBaseDO());
        jsonObject.put("id", esSaveBO.getEsId());

        String createdOnDateStr = LocalDateTImeUtil.format(esSaveBO.getBaseDO().getCreatedOn(), DatePatternConstant.YYYY_MM_DD_T_HH_MM_SSSZ);
        String modifiedDateStr = LocalDateTImeUtil.format(esSaveBO.getBaseDO().getCreatedOn(), DatePatternConstant.YYYY_MM_DD_T_HH_MM_SSSZ);
        jsonObject.put("createdOnDateTime", createdOnDateStr);
        jsonObject.put("modifiedDateTime", modifiedDateStr);

        // 将对象转换为 byte 数组
        byte[] json = JSON.toJSONBytes(jsonObject);
        // 设置文档内容
        indexRequest.source(json, XContentType.JSON);
        // 执行增加文档
        IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        log.info("ES save:index={},type={},status={}", esSaveBO.getIndex(), esSaveBO.getType(), response.status());
        return response.status();
    }

    /**
     * 新增或修改
     *
     * @param esSaveBO
     * @return
     */
    public <T> RestStatus saveOrUpdate(EsSaveBO esSaveBO, Class<T> respClassType) throws IOException {
        if (esSaveBO == null || MyStringUtils.isNullParam(esSaveBO.getIndex())) {
            log.error("EsUtils.saveOrUpdate error messages:{}", "index" + ErrorEnum.NOT_EXIST_ERROR);
            throw new Error("index is null");
        }
        //判断索引是否存在 不存在直接新增
        if (!exists(esSaveBO.getIndex())) {
            return save(esSaveBO);
        }

        if (getEsId(esSaveBO, respClassType) == null) {
            return save(esSaveBO);
        } else {
            EsUpdateBO esUpdateBO = MyBeanUtils.dtoToDo(esSaveBO, EsUpdateBO.class);
            return update(esUpdateBO);
        }
    }

    /**
     * 修改ES数据
     *
     * @param esUpdateBO
     * @return
     */
    public RestStatus update(EsUpdateBO esUpdateBO) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(esUpdateBO.getIndex(), esUpdateBO.getType(), esUpdateBO.getEsId());

        // 将对象转换为 byte 数组
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(esUpdateBO.getBaseDO());
        jsonObject.put("id", esUpdateBO.getEsId());
        String modifiedDateStr = LocalDateTImeUtil.format(esUpdateBO.getBaseDO().getCreatedOn(), DatePatternConstant.YYYY_MM_DD_T_HH_MM_SSSZ);
        jsonObject.put("modifiedDateTime", modifiedDateStr);

        byte[] json = JSON.toJSONBytes(jsonObject);
        // 设置更新文档内容
        updateRequest.doc(json, XContentType.JSON);
        // 执行更新文档
        UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        log.info("ES update:index={},type={},status={}", esUpdateBO.getIndex(), esUpdateBO.getType(), response.status());
        return response.status();
    }

    /**
     * 删除ES数据
     *
     * @param esBaseBO
     * @return
     */
    public RestStatus delete(EsBaseBO esBaseBO) throws IOException {
        // 创建删除请求对象
        DeleteRequest deleteRequest = new DeleteRequest(esBaseBO.getIndex(), esBaseBO.getType(), esBaseBO.getEsId());
        // 执行删除文档
        DeleteResponse response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);

        log.info("ES delete:index={},type={},status={}", esBaseBO.getIndex(), esBaseBO.getType(), response.status());
        return response.status();
    }

    /**
     * @param esBaseBO
     * @return
     */
    public <T> T getEsId(EsBaseBO esBaseBO, Class<T> respClassType) throws IOException {
        if (esBaseBO == null || MyStringUtils.isNullParam(esBaseBO.getEsId())) {
            return null;
        }
        //判断索引是否存在
        if (!exists(esBaseBO.getIndex())) {
            log.error("EsUtils.getEsId.error message={}:", "index:" + esBaseBO.getIndex() + ErrorEnum.NOT_EXIST_ERROR.getMsgEn());
            return null;
        }

        BoolQueryBuilder queryBuilder = buildFuzzQueryBuilder(esBaseBO);
        // 只查询1个
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(queryBuilder).size(1);
        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder);
        // 设置查询范围
        searchRequest.indices(esBaseBO.getIndex());

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();
        Arrays.sort(hits, (h1, h2) -> (int) (h2.getScore() - h1.getScore()));
        if (hits == null || hits.length <= 0) {
            return null;
        }
        return JSONObject.parseObject(hits[0].getSourceAsString(), respClassType);
    }

    /**
     * 判断索引是否存在
     *
     * @param indexName
     * @return
     * @throws IOException
     */
    public boolean exists(String indexName) throws IOException {
        //判断索引是否存在
        GetIndexRequest exist = new GetIndexRequest(indexName);
        return restHighLevelClient.indices().exists(exist, RequestOptions.DEFAULT);
    }

    /**
     * 分页查询ES数据
     *
     * @param reqPage
     * @param respClassType
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> BaseEsPagedResponse<T> getEsPage(BaseEsReqPage reqPage, Class<T> respClassType) throws IOException {
        Integer page = reqPage.getPage();
        Integer size = reqPage.getRows();

        // 构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(reqPage.getQueryBuilder());
        searchSourceBuilder.from((page - 1) * size);
        searchSourceBuilder.size(size);

        //设置索引名称
        SearchRequest searchRequest = new SearchRequest(reqPage.getIndex());
        searchRequest.source(searchSourceBuilder);

        //查询ES数据
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        if (searchHits == null || searchHits.length <= 0) {
            return BaseEsPagedResponse.success(0L, new ArrayList());
        }

        List<T> resultList = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            T data = JSONObject.parseObject(hit.getSourceAsString(), respClassType);
            resultList.add(data);
        }

        return BaseEsPagedResponse.success(searchResponse.getHits().getTotalHits().value, resultList);
    }

    /**
     * 查询ES列表数据
     *
     * @param req
     * @param respClassType
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> BaseEsResponse<T> getEsList(BaseEsReq req, Class<T> respClassType) throws IOException {
        // 构建搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(req.getQueryBuilder());

        //设置索引名称
        SearchRequest searchRequest = new SearchRequest(req.getIndex());
        searchRequest.source(searchSourceBuilder);

        //查询ES数据
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        if (searchHits == null || searchHits.length <= 0) {
            return BaseEsResponse.success(new ArrayList());
        }

        List<T> resultList = new ArrayList<>();
        for (SearchHit hit : searchHits) {
            T data = JSONObject.parseObject(hit.getSourceAsString(), respClassType);
            resultList.add(data);
        }

        return BaseEsResponse.success(resultList);
    }

    // 构建查询
    private BoolQueryBuilder buildFuzzQueryBuilder(EsBaseBO esBaseBO) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("_id", esBaseBO.getEsId());
        boolQueryBuilder.filter(queryBuilder);
        return boolQueryBuilder;
    }
}
