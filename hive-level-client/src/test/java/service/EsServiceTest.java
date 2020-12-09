package service;

import com.alibaba.fastjson.JSON;
import com.itclj.es.rest.high.entity.City;
import com.itclj.es.rest.high.service.EsService;
import common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EsServiceTest extends BaseTest {

    @Resource
    private EsService esService;

    @Test
    public void add() throws IOException {
        City city = new City();
        city.setId("1");
        city.setName("city");
        city.setCreateBy("itclj");
        city.setCreateDate(new Date());
        city.setDesc("这里是北京");
        city.setPersons(300);
        city.setTags(Arrays.asList("冷", "沙尘暴", "首都"));
        City cityR = esService.add(city);
        logger.info(JSON.toJSONString(cityR));
    }

    @Test
    public void delById() throws IOException {
        Integer id = 10;
        boolean res = esService.delById(id);
        logger.info("res={}", res);
    }

    @Test
    public void update() throws IOException {
        City city = new City();
        city.setId("1");
        city.setName("city");
        city.setCreateBy("itclj");
        city.setCreateDate(new Date());
        city.setDesc("这里是北京，修改后的值");
        city.setPersons(300);
        city.setTags(Arrays.asList("冷", "沙尘暴", "小吃多"));
        City cityR = esService.update(city);
        logger.info(JSON.toJSONString(cityR));
    }

    @Test
    public void getById() throws IOException {
        Integer id = 1;
        City city = esService.getById(id);
        logger.info(JSON.toJSONString(city));
    }

    @Test
    public void queryByName() throws IOException {
        String name = "city2";
        List<City> cityList = esService.queryByName(name);
        cityList.forEach(obj -> {
            logger.info(JSON.toJSONString(obj));
        });
    }
}
