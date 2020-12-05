package com.itclj.es.rest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itclj.es.rest.config.EsUtil;
import com.itclj.es.rest.entity.Book;
import com.itclj.es.rest.service.EsService;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class EsServiceImpl implements EsService {

    private Logger logger = LoggerFactory.getLogger(EsServiceImpl.class);

    @Resource
    private RestClient client;

    @Override
    public Book edit(Book book) throws IOException {
        // 构造HTTP请求，第一个参数是请求方法，第二个参数是服务器的端点，host默认是http://localhost:9200，
        // endpoint直接指定为index/type的形式
        Request request = new Request(HttpMethod.POST.name(), new StringBuilder("/book/_doc/").
                append(book.getId()).toString());
        // 设置其他一些参数比如美化json
        request.addParameter("pretty", "true");

        // 设置请求体并指定ContentType，如果不指定默认为TEXT_PLAIN,
        //设置ContentType.APPLICATION_JSON，UTF-8格式，中文不会乱码
        request.setEntity(new NStringEntity(JSON.toJSONString(book), ContentType.APPLICATION_JSON));

        // 发送HTTP请求
        Response response = client.performRequest(request);

        // 获取响应体, id: AWXvzZYWXWr3RnGSLyhH
        String responseBody = EntityUtils.toString(response.getEntity());
        logger.info(responseBody);
        return book;
    }

    @Override
    public boolean delById(String id) throws IOException {
        Request request = new Request(HttpMethod.DELETE.name(),
                new StringBuilder("/book/_doc/").append(id).toString());
        Response response = client.performRequest(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        logger.info(responseBody);
        return true;
    }

    @Override
    public Book getById(String id) throws IOException {
        Request request = new Request(HttpMethod.GET.name(),
                new StringBuilder("/book/_doc/").append(id).toString());
        Response response = client.performRequest(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        logger.info(responseBody);
        return EsUtil.pass(responseBody, Book.class);
    }

    @Override
    public boolean update(String id, Book book) throws IOException {
        // 构造HTTP请求
        Request request = new Request("POST", new StringBuilder("/book/_doc/").
                append(id).append("/_update").toString());

        // 将数据丢进去，这里一定要外包一层“doc”，否则内部不能识别
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("doc", book);
        request.setEntity(new NStringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON));

        // 执行HTTP请求
        Response response = client.performRequest(request);

        // 获取返回的内容
        String responseBody = EntityUtils.toString(response.getEntity());
        logger.info(responseBody);
        return true;
    }

    @Override
    public boolean updateDescByScript(String id, String desc) throws IOException {
        // 构造HTTP请求
        Request request = new Request("POST", new StringBuilder("/book/_doc/").
                append(id).append("/_update").toString());

        JSONObject jsonObject = new JSONObject();
        // 创建脚本语言，如果是字符变量，必须加单引号
        StringBuilder op1 = new StringBuilder("ctx._source.desc=").append("'" + desc + "'");
        jsonObject.put("script", op1);

        request.setEntity(new NStringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON));

        // 执行HTTP请求
        Response response = client.performRequest(request);

        // 获取返回的内容
        String responseBody = EntityUtils.toString(response.getEntity());
        logger.info(responseBody);
        return true;
    }

    @Override
    public List<Book> queryByName(String name) throws IOException {
        Request request = new Request("POST", new StringBuilder("/book/_doc/_search").toString());

        JSONObject query = new JSONObject();
        JSONObject match = new JSONObject();
        JSONObject termName = new JSONObject();
        termName.put("name", name);
        match.put("match", termName);
        query.put("query", match);

        logger.info(JSON.toJSONString(query));

        request.setEntity(new NStringEntity(JSON.toJSONString(query), ContentType.APPLICATION_JSON));

        // 执行HTTP请求
        Response response = client.performRequest(request);

        // 获取返回的内容
        String responseBody = EntityUtils.toString(response.getEntity());
        logger.info(responseBody);

        return null;
    }
}
