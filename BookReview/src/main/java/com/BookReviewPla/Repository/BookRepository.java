package com.BookReviewPla.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BookReviewPla.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
	
	boolean existsBookByBookId(Long bookId);

	Optional<Book> findById(Long bookId);

	void deleteById(Long bookId);
}
