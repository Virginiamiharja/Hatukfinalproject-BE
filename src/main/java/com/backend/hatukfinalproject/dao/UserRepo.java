package com.backend.hatukfinalproject.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.hatukfinalproject.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByEmail(String email);

	@Query(value = "SELECT * From User where verify_token = ?1", nativeQuery = true)
	public Optional<User> findByToken(String userToken);
	
}
