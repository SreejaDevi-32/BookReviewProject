package com.BookReviewPla.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BookReviewPla.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	/*
	 * List<Review> findByBook_BookId(Long bookId); // ✅ fixed
	 * 
	 * List<Review> findByUser_Id(Long userId); // ✅ fixed
	 * 
	 * List<Review> findByBook_BookIdOrderByLikesDesc(Long bookId); // ✅ fixed
	 * 
	 * Optional<Review> findByBook_BookIdAndUser_Id(Long bookId, Long userId); // ✅
	 * fixed
	 */
    Optional<Review> findById(Long reviewId);

    // ❌ REMOVE: Invalid naming
     List<Review> findByBook_BookId(Long bookId);
}
