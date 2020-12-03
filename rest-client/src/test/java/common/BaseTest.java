package common;

import com.itclj.es.rest.EsRestApplication;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsRestApplication.class)
public class BaseTest {
    public Logger logger = LoggerFactory.getLogger(getClass());
}
