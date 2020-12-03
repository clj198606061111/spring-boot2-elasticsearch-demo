package com.itclj.es.rest.config;

import com.alibaba.fastjson.JSON;

public class EsUtil {

    public static <T> T pass(String str, Class<T> clazz) {
        return JSON.parseObject(JSON.parseObject(str).getString("_source"), clazz);
    }
}
