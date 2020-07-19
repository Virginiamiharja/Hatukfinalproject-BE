package com.backend.hatukfinalproject.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.hatukfinalproject.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
	
	@Query(value = "SELECT * FROM Review where therapistdetail_id = ?1 limit 2 offset ?2", nativeQuery = true)
	public Iterable<Review> findAllLimit(int therapistId, int offset);

}
