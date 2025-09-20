package com.BookReviewPla;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.BookReviewPla.model.Book;

@Component
public class BookRowMapper implements RowMapper<Book>{

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Book (
				rs.getLong("book_id"),
				rs.getString("title"),
				rs.getString("author"),
				rs.getString("description"),
				rs.getDate("publication_date").toLocalDate()
				);
				
	}

}
