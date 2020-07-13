package com.backend.hatukfinalproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.hatukfinalproject.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

}
