package com.itclj.service;

import com.itclj.models.Book;
import org.springframework.data.domain.Page;


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
    boolean delById(String id);

    /**
     * 按照ID查询
     *
     * @param id
     * @return
     */
    Book getById(String id);

    /**
     * 查询所有
     * <p>
     * 最多一万条，超过一万条不允许查询
     *
     * @return
     */
    Iterable<Book> findAll();

    /**
     * 分页查询
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return
     */
    Page<Book> queryPage(Integer pageNum, Integer pageSize, Book book);
}
