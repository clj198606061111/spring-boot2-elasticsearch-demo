package com.itclj.service.impl;

import com.itclj.models.Book;
import com.itclj.repositories.BookRepository;
import com.itclj.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> queryPage(Integer pageNum, Integer pageSize, Book book) {
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return pageNum;
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        return bookRepository.searchSimilar(book, null, pageable);
    }
}
