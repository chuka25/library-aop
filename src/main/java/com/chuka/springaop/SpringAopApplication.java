package com.chuka.springaop;

import com.chuka.springaop.entity.Book;
import com.chuka.springaop.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringAopApplication implements CommandLineRunner {

	@Autowired
	private final BookRepo bookRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book1 = new Book("Война и мир", "Л.Н.Толстой");
		Book book2 = new Book("Капитанская дочка", "А.С.Пушкин");

		bookRepo.save(book1);
		bookRepo.save(book2);
	}
}
