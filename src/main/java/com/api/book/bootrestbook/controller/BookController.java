package com.api.book.bootrestbook.controller;


import java.util.List;
import java.util.Optional;

import com.api.book.bootrestbook.model.Book;
import com.api.book.bootrestbook.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    // get all books handler
    @GetMapping(value="/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> list = bookService.getAllBooks();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }


    // get single book handler
    @GetMapping(value="/books/{id}")
    public ResponseEntity<Book> getMethodName(@PathVariable("id") int id) {
        Book book = bookService.getBookById(id);
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    // create a new book handler
    @PostMapping("/save")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book addedBook = null;
        try{
            addedBook = this.bookService.addBook(book);
            return ResponseEntity.of(Optional.of(addedBook));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
        
        
    }


    // delete book handler
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") int id) {
        Book book = null;
        book = bookService.getBookById(id);
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } 
        bookService.deleteBook(id);
        return ResponseEntity.of(Optional.of(book));
        
    }

    // update book handler
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int id) {
        
        try{

            bookService.updateBook(book, id);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }
}
 