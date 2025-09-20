package com.BookReviewPla.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table(name = "book")
@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	@Column(
            nullable = false
    )
	private String title;
	
	@Column ( nullable = true)
	private String author;
	
	@Column(
            nullable = false
    )
	private String description;
	
	@Column(
            nullable = false
    )
	private LocalDate publicationDate;
	public Book() {
		super();
	}
	public Book(Long bookId, String title, String author, String description, LocalDate publicationDate) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.description = description;
		this.publicationDate = publicationDate;
	}
	public Book(String title, String author, String description, LocalDate publicationDate) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.publicationDate = publicationDate;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Book book = (Book) o;

	    if (bookId != null ? !bookId.equals(book.bookId) : book.bookId != null) return false;
	    if (!title.equals(book.title)) return false;
	    if (author != null ? !author.equals(book.author) : book.author != null) return false;
	    if (!description.equals(book.description)) return false;
	    return publicationDate.equals(book.publicationDate);
	}

	@Override
	public int hashCode() {
	    int result = bookId != null ? bookId.hashCode() : 0;
	    result = 31 * result + title.hashCode();
	    result = 31 * result + (author != null ? author.hashCode() : 0);
	    result = 31 * result + description.hashCode();
	    result = 31 * result + publicationDate.hashCode();
	    return result;
	}


}
