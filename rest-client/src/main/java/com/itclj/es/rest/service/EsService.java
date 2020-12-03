package com.itclj.es.rest.service;

import com.itclj.es.rest.entity.Book;

import java.io.IOException;
import java.util.List;

public interface EsService {

    Book edit(Book book) throws IOException;

    boolean delById(String id) throws IOException;

    List<Book> queryByName(String name);
}
