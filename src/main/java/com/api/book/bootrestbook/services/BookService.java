package com.api.book.bootrestbook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.api.book.bootrestbook.model.Book;

import org.springframework.stereotype.Service;


@Service // @Component both are same till now:
public class BookService {
    
    private static List<Book> list = new ArrayList<>();
    
    static {
        list.add(new Book(12, "Java", "Vikash"));
        list.add(new Book(13, "C++", "Vikky"));
        list.add(new Book(14, "C#", "Kumar"));
        list.add(new Book(15, "Python", "Basant"));
    }


    // get all book:
    public List<Book> getAllBooks() {
        return list;
    }


    // get single book by Id:
    public Book getBookById(int id) {
        Book book = null;
        try{
            book = list.stream().filter(ele -> (ele.getId() == id)).findFirst().get();
        }catch(Exception e){
            e.printStackTrace();
        }
        return book;
    }


    // adding the book
    public Book addBook(Book b) {
        list.add(b);
        return b;
    }


    // deleting the book
    public void deleteBook(int id) {
        list = list.stream().filter(singleBook -> (singleBook.getId() != id)).collect(Collectors.toList());
    }


    // updating the book
    public void updateBook(Book book, int id) {
        list = list.stream().map(singleBook -> {
            if (singleBook.getId() == id) {
                singleBook.setAuthor(book.getAuthor());
                singleBook.setTitle(book.getTitle());
            }
            return singleBook;
        }).collect(Collectors.toList());
        
    }
}
