package com.BookReviewPla.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.BookReviewPla.BookDao;
import com.BookReviewPla.JDBC.BookJDBCAccessService;
import com.BookReviewPla.JPA.BookJPADataAccessService;
import com.BookReviewPla.Repository.BookRepository;
import com.BookReviewPla.exceptions.ResourceNotFoundException;
import com.BookReviewPla.model.Book;

@Service
public class BookService {

	private final BookDao bookDao ;
	
	private final BookJDBCAccessService bookJDBCService;
	private final BookJPADataAccessService bookJPADataService;
	
	
	public BookService(@Qualifier("jdbc") BookDao bookDao, BookJDBCAccessService bookJDBCService, BookJPADataAccessService bookJPADataService) {
		super();
		this.bookDao = bookDao;
		this.bookJDBCService = bookJDBCService;
		this.bookJPADataService = bookJPADataService;
	}
	public Book addBook(Book book) {
		
		return bookDao.insertBook(book);
		
	}
	public Book updateBook(Book updatedBook)
	{
		Long bookId = updatedBook.getBookId();
		return bookJPADataService.selectBookByBookId(bookId)
				.map(book -> {
				book.setAuthor(updatedBook.getAuthor());
				book.setDescription(updatedBook.getDescription());
				book.setPublicationDate(updatedBook.getPublicationDate());
				return bookDao.updateBook(book);
				}).orElseThrow(()->new RuntimeException("Book Not Found"));
	}
	
	
	public void deleteBook(Long id) 
	{
		if(!bookDao.existsBookByBookId(id))
		{
			throw new ResourceNotFoundException("Book Not found");
		}
		bookDao.deleteBookByBookId(id);
	}
	
	public Book getBook(Long bookId)
	{
		return bookDao.getBookById(bookId);
	}
	
	
	public List<Book> getBooksByAuthor(String author)
	{
		return bookJDBCService.getBooksByAuthor(author);
	}
	
	

}
