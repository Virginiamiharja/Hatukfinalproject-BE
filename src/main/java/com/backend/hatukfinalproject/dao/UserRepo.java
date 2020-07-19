package com.backend.hatukfinalproject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.hatukfinalproject.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByEmail(String email);

	@Query(value = "SELECT * From User where role = \"therapist\"", nativeQuery = true)
	public Iterable<User> findAllTherapist();
	
	@Query(value = "SELECT * FROM user where (role = \"user\" and is_verified = 1)", nativeQuery = true)
	public Iterable<User> findAllUser();
	
	@Query(value = "Select sum(total_price) as spending, u.name, u.image from transaction t JOIN user u on u.id = t.user_id where t.status = \"booked\" or t.status = \"finish\" group by name order by spending desc limit 3", nativeQuery = true)
	public List<Object[]> findTopSpendingUser();
	

	
	
}
