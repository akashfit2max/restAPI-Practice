package com.spring.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.entities.Book;
import com.spring.rest.service.BookService;

@RestController
public class BooksController {

	@Autowired
	private BookService bookService;

//	@RequestMapping(value = "/books", method = RequestMethod.GET)
//	@ResponseBody
	@GetMapping("/books")
	public Book getBooks() {
		Book book = new Book(1, "java programming", "Akash");
		return book;
	}

//	get all books handler
//	@GetMapping("/allbooks")
//	public List<Book> getAllBooks() {
//		return bookService.getAllBooks();
//	}

//	handling errors
	@GetMapping("/allbooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> allBooks = bookService.getAllBooks();
		if (allBooks.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(allBooks);
	}

//	get a single book handler
//	@GetMapping("/allbooks/{id}")
//	public Book getBook(@PathVariable("id") int id) {
//		return bookService.getBook(id);
//	}

//	handling errors
	@GetMapping("/allbooks/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book = bookService.getBook(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}

//	add a book handler
	@PostMapping("/books")
	public ResponseEntity<?> addBooks(@RequestBody Book book) {
		Book b = null;
		try {
			b = bookService.addBook(book);
			System.out.println(b);
			return ResponseEntity.status(HttpStatus.CREATED).body(b);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

// delete handler
	@DeleteMapping("/books/{id}")
	public List<Book> deleteBook(@PathVariable("id") int id) {
		bookService.deleteBook(id);
		return bookService.getAllBooks();
	}

//	update book
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int id) {
		try {
			bookService.updateBook(book, id);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
