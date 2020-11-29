package service;

import com.alibaba.fastjson.JSON;
import com.itclj.models.Book;
import com.itclj.service.BookService;
import common.BaseTest;
import net.bytebuddy.utility.RandomString;
import org.junit.Test;
import org.springframework.data.domain.Page;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

public class BookServiceTest extends BaseTest {

    @Resource
    private BookService bookService;

    @Test
    public void add() {
        Book book = new Book();
        book.setId(RandomString.make(32));
        book.setName("itclj_test_" + RandomString.make(10));
        book.setAuthor("itclj_" + RandomString.make(3));
        Book bookR = bookService.add(book);
        logger.info(JSON.toJSONString(bookR));
    }

    @Test
    public void addBatch() {
        long timeStart = System.currentTimeMillis();
        for (Integer n = 0; n < 1000000; n++) {
            Book book = new Book();
            book.setId(RandomString.make(32));
            book.setName("itclj_test_" + RandomString.make(10));
            book.setAuthor("itclj_" + RandomString.make(3));
            bookService.add(book);
            if (n % 100 == 0) {
                logger.info("batch add n={},use time={}", n, System.currentTimeMillis() - timeStart);
            }
        }
        logger.info("batch add finished,use time={}", System.currentTimeMillis() - timeStart);
    }

    @Test
    public void update() {
        Book book = new Book();
        book.setId("cHczGTXuxyN1ownvsUM7hFjHrBRsbL3u");
        book.setName("itclj_update_test_" + RandomString.make(10));
        book.setAuthor("itclj_update_" + RandomString.make(3));
        Book bookR = bookService.update(book);
        logger.info(JSON.toJSONString(bookR));
    }

    @Test
    public void getById() {
        String id = "cHczGTXuxyN1ownvsUM7hFjHrBRsbL3u";
        Book bookR = bookService.getById(id);
        logger.info(JSON.toJSONString(bookR));
    }

    @Test
    public void delById() {
        String id = "72ZqL8vVemGUQ4H2B0faLmNm6vPit2Ek";
        boolean res = bookService.delById(id);
        logger.info("del result ={}", res);
    }

    @Test
    public void findAll() {
        Iterable<Book> books = bookService.findAll();
        AtomicInteger n = new AtomicInteger(0);
        books.forEach(obj -> {
            logger.info("n={},obj={}", n.addAndGet(1), JSON.toJSONString(obj));
        });
    }

    @Test
    public void queryPage() {
        Integer pageNum = 1;
        Integer pageSize = 5;
        Book book = new Book();
        book.setName("itclj");
        book.setId("XRnpZd8TgvFhyzTMnGUZmm2fj4nBgogr");
        Page<Book> bookPage = bookService.queryPage(pageNum, pageSize, book);
        bookPage.forEach(obj -> {
            logger.info(JSON.toJSONString(obj));
        });
    }

    @Test
    public void randomStr() {
        String randomStr = RandomString.make(32);
        logger.info("random string {}", randomStr);
    }
}
