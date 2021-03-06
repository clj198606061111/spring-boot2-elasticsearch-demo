package com.itclj.service.impl;

import com.itclj.models.Book;
import com.itclj.repositories.BookRepository;
import com.itclj.service.BookService;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookRepository bookRepository;

    public Book add(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        Book bookR = bookRepository.findBookById(book.getId());
        if (null == bookR) {
            return book;
        }
        bookR.setAuthor(book.getAuthor());
        bookR.setName(book.getName());
        return bookRepository.save(bookR);
    }

    public boolean delById(String id) {
        bookRepository.deleteById(id);
        return true;
    }

    public Book getById(String id) {
        return bookRepository.findBookById(id);
    }

    public Iterable<Book> findAll() {
        Pageable pageable = PageRequest.of(1, 10);
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> queryPage(Integer pageNum, Integer pageSize, Book book) {

        //分页是从第0页开始的
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        if (!ObjectUtils.isEmpty(book.getName())) {
            builder.withQuery(QueryBuilders.matchQuery("name", book.getName()));
        }
        if (!ObjectUtils.isEmpty(book.getDesc())) {
            builder.withQuery(QueryBuilders.matchQuery("desc", book.getDesc()));
        }
        builder.withPageable(pageable);
        return bookRepository.search(builder.build());
    }
}
