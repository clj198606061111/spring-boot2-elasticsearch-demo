package service;

import com.alibaba.fastjson.JSON;
import com.itclj.es.rest.entity.Book;
import com.itclj.es.rest.service.EsService;
import common.BaseTest;
import net.bytebuddy.utility.RandomString;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;

public class EsServiceTest extends BaseTest {

    @Resource
    private EsService esService;

    @Test
    public void edit() throws IOException {
        Book book = new Book();
        book.setId(RandomString.make(32));
        book.setName("itclj_rest_test_" + RandomString.make(10));
        book.setAuthor("itclj_" + RandomString.make(3));
        book.setDesc("这是一个描述 rest client");
        esService.edit(book);
    }

    @Test
    public void delById() throws IOException {
        String id = "znGdKf8GjxML0DrCneu5j5KGtFHNiboM";
        boolean res = esService.delById(id);
        logger.info("res={}", res);
    }

    @Test
    public void getById() throws IOException {
        String id = "XRnpZd8TgvFhyzTMnGUZmm2fj4nBgogr";
        Book book = esService.getById(id);
        logger.info(JSON.toJSONString(book));
    }

    @Test
    public void updateDescByScript() throws IOException {
        String id = "XRnpZd8TgvFhyzTMnGUZmm2fj4nBgogr";
        String desc = "通过脚本修改特定字段值";
        boolean res = esService.updateDescByScript(id, desc);
        logger.info("res={}", res);
    }

    @Test
    public void update() throws IOException {
        String id = "XRnpZd8TgvFhyzTMnGUZmm2fj4nBgogr";
        Book book = new Book();
        book.setAuthor("作者");
        book.setDesc("直接修改描述");
        boolean res = esService.update(id, book);
        logger.info("res={}", res);
    }

    @Test
    public void queryByName() throws IOException {
        String name="itclj_test_55uSth7adT";
        esService.queryByName(name);
    }
}
