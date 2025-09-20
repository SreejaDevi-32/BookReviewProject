package com.BookReviewPla.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.BookReviewPla.Repository.BookRepository;
import com.BookReviewPla.Service.BookService;
import com.BookReviewPla.controller.BookController;
import com.BookReviewPla.model.Book;
import com.BookReviewPla.security.SecurityFilterChainConfig;

@SpringBootTest
@AutoConfigureMockMvc
@Import(SecurityFilterChainConfig.class)
public class BookControllerTest {

	@Autowired
	 private MockMvc mockMvc;
	
	@Autowired
	 private BookService bookService;
	 
	@Autowired
	 private ObjectMapper objectMapper;
	
	@Autowired
	private BookRepository bookRepository;

	 
	/*
	 * @Test public void testCreateBook() throws Exception { // Construct book
	 * without an ID (like a real incoming request) Book requestBook = new
	 * Book("Spring", "Author X", "Tech", LocalDate.parse("2025-06-19"));
	 * 
	 * // Perform request MvcResult result = mockMvc.perform(post("/api/v1/book")
	 * .contentType(MediaType.APPLICATION_JSON)
	 * .content(objectMapper.writeValueAsString(requestBook)))
	 * .andExpect(status().isOk()) .andReturn();
	 * 
	 * // Extract response String responseBody =
	 * result.getResponse().getContentAsString(); Book responseBook =
	 * objectMapper.readValue(responseBody, Book.class);
	 * 
	 * // Validate response data assertEquals("Spring", responseBook.getTitle());
	 * assertEquals("Author X", responseBook.getAuthor()); assertEquals("Tech",
	 * responseBook.getDescription()); assertEquals(LocalDate.parse("2025-06-19"),
	 * responseBook.getPublicationDate());
	 * 
	 * // Book ID should be generated // assert responseBook.getBookId() != null; }
	 */
	@Test
	public void testCreateRunbook_NullCheck() 
	{
		BookController bookController = new BookController();
		 Book book = new Book(null, "Spring Boot", "Author X", "Tech", LocalDate.parse("2025-06-19"));
		  Book bookWithId = new Book(1L, "Spring Boot", "Author X", "Tech", LocalDate.parse("2025-06-19"));
		 Assertions.assertThrows(IllegalArgumentException.class, () ->
		 {
			 bookController.createBook(bookWithId);
		 }
				 );
	}
	
	

	

}
