package com.BookReviewPla.JPA;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.BookReviewPla.BookDao;
import com.BookReviewPla.Repository.BookRepository;
import com.BookReviewPla.model.Book;

@Repository("jpa")
public class BookJPADataAccessService implements BookDao{
	
	private final BookRepository bookRepository;
	

	public BookJPADataAccessService(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public List<Book> selectAllBooks() {
		Page<Book> books = bookRepository.findAll(Pageable.ofSize(10));
		return books.getContent();
	}

	public Optional<Book> selectBookByBookId(Long bookId) {
		// TODO Auto-generated method stub
		return bookRepository.findById(bookId);
	}

	@Override
	public Book insertBook(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}

	@Override
	public boolean existsBookByBookId(Long bookId) {
		
		return bookRepository.existsBookByBookId(bookId);
	}

	@Override
	public void deleteBookByBookId(Long bookId){
		
		
		bookRepository.deleteById(bookId);
		
	}

	@Override
	public Book updateBook(Book updatedBook) {
		// TODO Auto-generated method stub
		return bookRepository.save(updatedBook);
		
	}

	@Override
	public Book getBookById(Long id) {
	    return bookRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
	}


}
