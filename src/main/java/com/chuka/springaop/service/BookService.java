package com.chuka.springaop.service;

import com.chuka.springaop.entity.Book;
import com.chuka.springaop.exceptions.AlreadyExistsException;
import com.chuka.springaop.repository.BookRepo;
import com.chuka.springaop.util.CustomResponse;
import com.chuka.springaop.util.CustomStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;

    public CustomResponse<Book> getAll(){
        List<Book> books = bookRepo.findAll();
        return new CustomResponse<>(books, CustomStatus.SUCCESS);
    }

    public CustomResponse<Book> getBookByTitle(String title){
        Book book= bookRepo.findBookByTitle(title).orElseThrow();
        return new CustomResponse<>(Stream.of(book).collect(Collectors.toList()), CustomStatus.SUCCESS);
    }

    public CustomResponse<Book> addBook(Book book) throws AlreadyExistsException {
        if(bookRepo.findBookByTitle(book.getTitle()).isPresent())
            throw new AlreadyExistsException("Книга уже имеется в репозитории");
        Book newBook = bookRepo.save(book);
        return new CustomResponse<>(Stream.of(newBook).collect(Collectors.toList()), CustomStatus.SUCCESS);
    }

    public CustomResponse<Book> deleteBookByTitle(String title){
        Book book = bookRepo.findBookByTitle(title).orElseThrow();
        bookRepo.deleteById(book.getId());
        return new CustomResponse<>(CustomStatus.SUCCESS);
    }

    public CustomResponse<Book> deleteBookById(Long id){
        bookRepo.deleteById(id);
        return new CustomResponse<>(CustomStatus.SUCCESS);
    }
}
