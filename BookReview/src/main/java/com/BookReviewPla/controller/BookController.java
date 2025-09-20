package com.BookReviewPla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BookReviewPla.Service.BookService;
import com.BookReviewPla.model.Book;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
	    if (book.getBookId() != null) {
	        throw new IllegalArgumentException("New book should not have an ID.");
	    }
	    return ResponseEntity.ok(bookService.addBook(book));
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateBook(@RequestBody Book updateBook)
	{
		return ResponseEntity.ok()
				.body(bookService.updateBook(updateBook));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id)
	{
		bookService.deleteBook(id);
		return ResponseEntity.ok()
				.build();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(Long bookId)
	{
		return ResponseEntity.ok()
				.body(bookService.getBook(bookId));
	}
	
	@GetMapping
	public ResponseEntity<?> getBookByAuthor(@RequestParam String author)
	{
		return ResponseEntity.ok().body(bookService.getBooksByAuthor(author));
	}
	

}
