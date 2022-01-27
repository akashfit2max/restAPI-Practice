package com.spring.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.dao.BookRepository;
import com.spring.rest.entities.Book;

@Service
public class BookService {

//	private static List<Book> books = new ArrayList<>();

	@Autowired
	private BookRepository bookRepository;

//	static {
//		books.add(new Book(1, "python", "ABC"));
//		books.add(new Book(2, "c", "Akash"));
//		books.add(new Book(3, "java", "mama"));
//		books.add(new Book(4, "cpp", "rupa"));
//		books.add(new Book(5, "kotlin", "mu"));
//	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Book getBook(int id) {
		Book book = null;
		try {
//			book = books.stream().filter(e -> e.getId() == id).findFirst().get();
			Optional<Book> optional = bookRepository.findById(id);
			book = optional.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	public Book addBook(Book book) {
//		books.add(book);
		bookRepository.save(book);
		return book;
	}

	public void deleteBook(int bkid) {
//		books = books.stream().filter(book -> book.getId() != bkid).collect(Collectors.toList());
		bookRepository.deleteById(bkid);

	}

	public void updateBook(Book book, int id) {
//		books = books.stream().map(b -> {
//			if (b.getId() == id) {
//				b.setTitle(book.getTitle());
//				b.setAuthor(book.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
		book.setId(id);
		bookRepository.save(book);

	}

}
