package com.itclj.service;

import com.itclj.models.Book;


public interface BookService {

    /**
     * 添加
     *
     * @param book
     * @return
     */
    Book add(Book book);

    /**
     * 修改
     *
     * @param book
     * @return
     */
    Book update(Book book);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 按照ID查询
     *
     * @param id
     * @return
     */
    Book getById(String id);

    /**
     * 查询所有
     *
     * @return
     */
    Iterable<Book> findAll();
}
