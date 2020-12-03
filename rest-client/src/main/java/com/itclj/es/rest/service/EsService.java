package com.itclj.es.rest.service;

import com.itclj.es.rest.entity.Book;

import java.io.IOException;
import java.util.List;

public interface EsService {

    Book edit(Book book) throws IOException;

    /**
     * 通过Id删除
     *
     * @param id 记录Id
     * @return
     * @throws IOException
     */
    boolean delById(String id) throws IOException;

    /**
     * 通过Id查询
     *
     * @param id 记录Id
     * @return
     */
    Book getById(String id) throws IOException;

    List<Book> queryByName(String name);
}
