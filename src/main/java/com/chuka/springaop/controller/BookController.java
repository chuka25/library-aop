package com.chuka.springaop.controller;

import com.chuka.springaop.entity.Book;
import com.chuka.springaop.exceptions.AlreadyExistsException;
import com.chuka.springaop.service.BookService;
import com.chuka.springaop.util.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public CustomResponse<Book> getAll(){
        return bookService.getAll();
    }

    @GetMapping("/books/{title}")
    public CustomResponse<Book> getBookByTitle(@PathVariable("title") String title){
        return bookService.getBookByTitle(title);
    }

    @PostMapping("/books")
    public CustomResponse<Book> addBook(@RequestBody Book book) throws AlreadyExistsException {
        return bookService.addBook(book);
    }

    @DeleteMapping("/books/{title}")
    public CustomResponse<Book> deleteBookByTitle(@PathVariable ("title") String title){
        return bookService.deleteBookByTitle(title);
    }

    @DeleteMapping("/books/{id}")
    public CustomResponse<Book> deleteBookById(@PathVariable ("id") Long id){
        return bookService.deleteBookById(id);
    }

}
