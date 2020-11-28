package service;

import com.alibaba.fastjson.JSON;
import com.itclj.models.Book;
import com.itclj.service.BookService;
import common.BaseTest;
import net.bytebuddy.utility.RandomString;
import org.junit.Test;

import javax.annotation.Resource;

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
    public void randomStr() {
        String randomStr = RandomString.make(32);
        logger.info("random string {}", randomStr);
    }
}
