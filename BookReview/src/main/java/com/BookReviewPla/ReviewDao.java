package com.BookReviewPla;

import java.util.List;

import com.BookReviewPla.model.Review;

public interface ReviewDao {

	List<Review> getReviewsByBook(Long bookId);
	Review likeReview(Long reviewId);
	Review dislikeReview(Long reviewId);
	Review updateReview(Long reviewId,String username,ReviewRequest reviewRequest);
	void deleteReview(Long reviewId,String username);
	//List<Review> getReviewByUser(String username);
	Review addReview(Long bookId, String username, ReviewRequest reviewRequest);
	
}
