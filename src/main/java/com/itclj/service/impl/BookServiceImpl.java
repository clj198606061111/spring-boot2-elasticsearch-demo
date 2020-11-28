package com.itclj.service.impl;

import com.itclj.models.Book;
import com.itclj.repositories.BookRepository;
import com.itclj.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public boolean delete(String id) {
        bookRepository.deleteById(id);
        return true;
    }

    public Book getById(String id) {
        return bookRepository.findBookById(id);
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }
}
