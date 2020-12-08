package com.itclj.es.rest.high.service.impl;

import com.alibaba.fastjson.JSON;
import com.itclj.es.rest.high.entity.City;
import com.itclj.es.rest.high.service.EsService;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

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
    public List<City> queryByName(String name) {
        return null;
    }
}
