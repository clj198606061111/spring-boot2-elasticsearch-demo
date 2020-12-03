package service;

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
}
