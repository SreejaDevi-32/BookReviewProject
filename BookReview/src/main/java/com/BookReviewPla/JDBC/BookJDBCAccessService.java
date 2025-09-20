package com.BookReviewPla.JDBC;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.BookReviewPla.BookDao;
import com.BookReviewPla.BookRowMapper;
import com.BookReviewPla.model.Book;

@Repository("jdbc")
public class BookJDBCAccessService implements BookDao{
	
	private final JdbcTemplate jdbcTemplate;
	private final BookRowMapper bookRowMapper;

	 public BookJDBCAccessService(JdbcTemplate jdbcTemplate, BookRowMapper bookRowMapper) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.bookRowMapper = bookRowMapper;
	}

	public List<Book> getBooksByAuthor(String author) {
	        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
	                .withProcedureName("get_books_by_author")
	                .returningResultSet("books",
	                    BeanPropertyRowMapper.newInstance(Book.class));

	        MapSqlParameterSource params = new MapSqlParameterSource()
	                .addValue("authorName", author);

	        Map<String, Object> result = jdbcCall.execute(params);

	        return (List<Book>) result.get("books");
	    }

	@Override
	public List<Book> selectAllBooks() {
		// TODO Auto-generated method stub
		var sql = " SELECT * from book LIMIT 100";
		return jdbcTemplate.query(sql,bookRowMapper);
		
	}

	@Override
	public Book insertBook(Book book) {
		// TODO Auto-generated method stub
		var sql = """
				INSERT INTO book (author, description, publication_date,title)
				VALUES (?, ?, ?, ?)
				""";
		int result = jdbcTemplate.update(
				sql,
				book.getAuthor(),
				book.getDescription(),
				book.getPublicationDate(),
				book.getTitle()
				
				);
		return book;
				
	}

	@Override
	public boolean existsBookByBookId(Long bookId) {
		
		var sql = """
				select count(*)
				from book
				where book_id = ?
				""";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class,bookId);
		
		return count != null && count>0 ;
	}

	@Override
	public void deleteBookByBookId(Long bookId) {
		// TODO Auto-generated method stub
		var sql = """
				DELETE *
				FROM book
				where book_id = ?
				""";
		int result = jdbcTemplate.update(sql,bookId);
		System.out.println("Deleted Record: "+result);
		
	}

	@Override
	public Book updateBook(Book update) {
		// TODO Auto-generated method stub
		var sql = """
				UPDATE book SET author = ? where book_id =? 
				""";
		int result = jdbcTemplate.update(sql,update.getAuthor(),update.getBookId());
		return update;
	}

	@Override
	public Book getBookById(Long bookId) {
	    String sql = """
	        SELECT * FROM book WHERE book_id = ?
	    """;

	    List<Book> books = jdbcTemplate.query(sql, bookRowMapper, bookId);
	    System.out.println("Books found: " + books.size());
	    return books.isEmpty() ? null : books.get(0);
	}


	 
		

}
