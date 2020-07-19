package com.backend.hatukfinalproject.service.impl;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hatukfinalproject.dao.ReviewRepo;
import com.backend.hatukfinalproject.dao.TherapistDetailRepo;
import com.backend.hatukfinalproject.dao.UserRepo;
import com.backend.hatukfinalproject.entity.Review;
import com.backend.hatukfinalproject.entity.TherapistDetail;
import com.backend.hatukfinalproject.entity.User;
import com.backend.hatukfinalproject.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepo reviewRepo;
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	private TherapistDetailRepo therapistDetailRepo;
	
	@Override
	public Review addReview(Review review, int userId, int therapistDetailId) {
		LocalDate date = LocalDate.now();
		User findUser = userRepo.findById(userId).get();
		TherapistDetail findTherapist = therapistDetailRepo.findById(therapistDetailId).get();
		
		review.setTherapistDetail(findTherapist);
		review.setUser(findUser);
		review.setPostedDate(date);
		review.setId(0);
		return reviewRepo.save(review);
	}

	@Override
	public Iterable<Review> showReviewsById(int therapistId, int offset) {
//		return reviewRepo.findAll();
		return reviewRepo.findAllLimit(therapistId, offset);
	}

}
