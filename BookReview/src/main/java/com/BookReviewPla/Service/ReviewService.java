package com.BookReviewPla.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.BookReviewPla.ReviewDao;
import com.BookReviewPla.ReviewRequest;
import com.BookReviewPla.Repository.ReviewRepository;
import com.BookReviewPla.model.Review;

@Service
public class ReviewService {
	
	private final ReviewDao reviewDao;

	public ReviewService(ReviewDao reviewDao) {
		super();
		this.reviewDao = reviewDao;
	}
	
	public Review addReview(Long bookId, String username, ReviewRequest reviewRequest)
	{
		return reviewDao.addReview(bookId, username, reviewRequest);
	}
	
	/*
	 * public List<Review> getReviewsByBook(Long bookId) { return
	 * reviewDao.getReviewsByBook(bookId); }
	 */
	 
	 public Review likeReview(Long reviewId)
	 {
		 return reviewDao.likeReview(reviewId);
	 }
	 
	 public Review dislikeReview(Long reviewId) 
	 {
		 return reviewDao.dislikeReview(reviewId);
	 }
	 
	 public Review updateReview(Long reviewId, String username, ReviewRequest reviewRequest)
	 {
		 return reviewDao.updateReview(reviewId, username, reviewRequest);
	 }
	 
	 public void deleteReview(Long reviewId, String username)
	 {
		 reviewDao.deleteReview(reviewId, username);
	 }
	
	

}
