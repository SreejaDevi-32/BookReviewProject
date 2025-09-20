package com.BookReviewPla;

import java.util.List;
import java.util.Optional;

import com.BookReviewPla.model.Book;

public interface BookDao {
	
	List<Book> selectAllBooks();
	Book insertBook(Book book);
	boolean existsBookByBookId(Long bookId);
	void deleteBookByBookId(Long bookId);
	Book updateBook(Book update);
	Book getBookById(Long bookId);

}
