package com.backend.hatukfinalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hatukfinalproject.entity.Review;
import com.backend.hatukfinalproject.service.ReviewService;

@RestController
@CrossOrigin
@RequestMapping("/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/addreview")
	public Review addReview(@RequestBody Review review, @RequestParam int userId, @RequestParam int therapistDetailId ) {
//		Nanti dikasih validasi, kalo userId == therapist.userId berarti dia gaboleh kasih review ke diri dia sendiri gitu
		return reviewService.addReview(review, userId, therapistDetailId);
	}
	
	@GetMapping
	public Iterable <Review> showReviews() {
		return reviewService.showReviews();
	}

}
