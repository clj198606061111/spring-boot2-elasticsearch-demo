package com.itclj.es.rest.high.service;

import com.itclj.es.rest.high.entity.City;

import java.io.IOException;
import java.util.List;

public interface EsService {

    City add(City city) throws IOException;

    City update(City city) throws IOException;

    boolean delById(Integer id) throws IOException;

    City getById(Integer id) throws IOException;

    List<City> queryByName(String name) throws IOException;
}
