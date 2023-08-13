package com.chuka.springaop.repository;

import com.chuka.springaop.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book, Long> {
    Optional <Book> findBookByTitle(String title);
}
