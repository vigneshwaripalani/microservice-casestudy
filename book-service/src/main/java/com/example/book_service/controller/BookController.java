package com.example.book_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_service.model.Book;
import com.example.book_service.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable String bookId) {
        return bookService.getBookById(bookId)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        Book savedBook = bookService.save(newBook);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
    
    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable String bookId, @RequestBody Book updatedBook) {
        Optional<Book> existingBook = bookService.getBookById(bookId);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setName(updatedBook.getName());
            book.setAuthor(updatedBook.getAuthor());
            book.setCopiesAvailable(updatedBook.getCopiesAvailable());
            book.setTotalCopies(updatedBook.getTotalCopies());
            bookService.save(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
