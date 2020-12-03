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

    /**
     * 直接更新索引对象
     *
     * @param id   记录Id
     * @param book 更新的对象
     * @return
     */
    boolean update(String id, Book book) throws IOException;

    /**
     * 通过Id更新某个属性
     * <p>
     * 通过脚本更新，脚本的表达能力很强，可以实现复杂的处理逻辑，达到通过条件更新操作数据得目的
     *
     * @param id   记录Id
     * @param desc 描述
     * @return
     */
    boolean updateDescByScript(String id, String desc) throws IOException;

    List<Book> queryByName(String name);
}
