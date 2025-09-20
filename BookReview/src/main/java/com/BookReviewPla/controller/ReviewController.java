package com.BookReviewPla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BookReviewPla.ReviewRequest;
import com.BookReviewPla.Service.ReviewService;
import com.BookReviewPla.model.Review;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	 @PostMapping("/{bookId}")
	public ResponseEntity<Review> addReview(@PathVariable Long bookId, @RequestParam String username, @RequestBody ReviewRequest reviewRequest)
	{
		return ResponseEntity.ok()
				.body(reviewService.addReview(bookId, username, reviewRequest));
	}
	
	 @PostMapping("/{reviewId}/like")
	public ResponseEntity<Review> likeReview(@PathVariable Long reviewId)
	{
		return ResponseEntity.ok()
				.body(reviewService.likeReview(reviewId));
	}
	
	 @PostMapping("/{reviewId}/dislike")
	public ResponseEntity<Review> dilikeReview(Long reviewId)
	{
		return ResponseEntity.ok()
				.body(reviewService.dislikeReview(reviewId));
	}
	
	/*
	 * @GetMapping("/book/{bookId}") public ResponseEntity<List<Review>>
	 * getReviewsByBook(Long bookId) { return ResponseEntity.ok()
	 * .body(reviewService.getReviewsByBook(bookId)); }
	 */
	
	
}
