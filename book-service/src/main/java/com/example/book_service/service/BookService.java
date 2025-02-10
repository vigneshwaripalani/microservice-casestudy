package com.example.book_service.service;

import java.util.List;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book_service.model.Book;
import com.example.book_service.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Optional<Book> getBookById(String bookId) {
        return bookRepository.findById(bookId);
    }
    
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
