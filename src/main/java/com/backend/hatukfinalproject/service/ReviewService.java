package com.backend.hatukfinalproject.service;

import com.backend.hatukfinalproject.entity.Review;

public interface ReviewService {
	
	public Review addReview(Review review, int userId, int therapistDetailId);
	
	public Iterable<Review> showReviewsById(int therapistId, int offset);

}
