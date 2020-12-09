package com.itclj.es.rest.high.service.impl;

import com.alibaba.fastjson.JSON;
import com.itclj.es.rest.high.entity.City;
import com.itclj.es.rest.high.service.EsService;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class EsServiceImpl implements EsService {

    @Resource
    private RestHighLevelClient client;

    private static final String INDEX_CITY = "city";

    private static final String TYPE = "_doc";

    @Override
    public City add(City city) throws IOException {
        client.index(new IndexRequest(INDEX_CITY).id(String.valueOf(city.getId())).source(JSON.toJSONString(city), XContentType.JSON), RequestOptions.DEFAULT);
        return city;
    }

    @Override
    public City update(City city) throws IOException {
        client.update(new UpdateRequest(INDEX_CITY, String.valueOf(city.getId())).doc(new IndexRequest(INDEX_CITY).id(String.valueOf(city.getId())).source(JSON.toJSONString(city), XContentType.JSON)), RequestOptions.DEFAULT);
        return city;
    }

    @Override
    public boolean delById(Integer id) throws IOException {
        client.delete(new DeleteRequest(INDEX_CITY).id(String.valueOf(id)), RequestOptions.DEFAULT);
        return true;
    }

    @Override
    public City getById(Integer id) throws IOException {
        GetResponse response = client.get(new GetRequest(INDEX_CITY, String.valueOf(id)), RequestOptions.DEFAULT);
        return JSON.parseObject(response.getSourceAsString(), City.class);
    }

    @Override
    public List<City> queryByName(String name) throws IOException {
        SearchRequest request = new SearchRequest();
        request.scroll(new TimeValue(1, TimeUnit.HOURS)); //滚动游标保留多久
        request.setBatchedReduceSize(10);//每批次拉多少条
        request.indices(INDEX_CITY);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.prefixQuery("name", name));
        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("name", name);
        sourceBuilder.query(prefixQueryBuilder);
        sourceBuilder.timeout(TimeValue.timeValueMinutes(2L));
        //sourceBuilder.size(10);//分页量
        //sourceBuilder.sort("name", SortOrder.DESC);//排序

        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return Arrays.asList(response.getHits().getHits()).stream().map(obj -> JSON.parseObject(obj.getSourceAsString(), City.class)).collect(Collectors.toList());
    }
}
