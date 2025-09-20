package com.BookReviewPla.JPA;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.BookReviewPla.ReviewDao;
import com.BookReviewPla.ReviewRequest;
import com.BookReviewPla.Repository.BookRepository;
import com.BookReviewPla.Repository.ReviewRepository;
import com.BookReviewPla.Repository.UserRepository;
import com.BookReviewPla.exceptions.AccessDeniedException;
import com.BookReviewPla.exceptions.ResourceNotFoundException;
import com.BookReviewPla.model.Book;
import com.BookReviewPla.model.Review;
import com.BookReviewPla.model.User;

@Service
public class ReviewJPADataAccessService implements ReviewDao {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    public ReviewJPADataAccessService(UserRepository userRepository,
                                      BookRepository bookRepository,
                                      ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review addReview(Long bookId, String username, ReviewRequest reviewRequest) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }

        Review review = new Review();
        review.setContent(reviewRequest.content());
        review.setRating(reviewRequest.rating());
        review.setCreatedAt(LocalDateTime.now());
        review.setLikes(0L);
        review.setDislikes(0L);
        review.setUser(user);
        review.setBook(book);

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsByBook(Long bookId) {
        return reviewRepository.findByBook_BookId(bookId); // ✅ fixed method name
    }

    @Override
    public Review likeReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));
        review.setLikes(review.getLikes() + 1);
        return reviewRepository.save(review);
    }

    @Override
    public Review dislikeReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));
        review.setDislikes(review.getDislikes() + 1);
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long reviewId, String username, ReviewRequest reviewRequest) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));

        if (!review.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("You are not allowed to update this review");
        }

        review.setContent(reviewRequest.content());
        review.setRating(reviewRequest.rating());
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId, String username) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + reviewId));

        if (!review.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("You are not allowed to delete this review");
        }

        reviewRepository.delete(review);
    }

	/*
	 * @Override public List<Review> getReviewByUser(String username) { User user =
	 * userRepository.findByUsername(username); if (user == null) { throw new
	 * ResourceNotFoundException("User not found with username: " + username); }
	 * return reviewRepository.findByUser_Id(user.getUserId()); // ✅ fixed method
	 * name }
	 */
}
