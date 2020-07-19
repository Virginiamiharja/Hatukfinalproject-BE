package com.backend.hatukfinalproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.hatukfinalproject.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
	
	@Query(value = "SELECT count(*) as bookingtimes, day_name as day FROM Day d JOIN therapist_service_schedule s ON d.id = s.day_id JOIN transaction t ON s.id = t.serviceschedule_id where t.status = \"booked\" or t.status = \"finish\" group by d.id", nativeQuery = true)
	public List<Object[]> findTransactionDetails();
	
	@Query(value = "SELECT * FROM transaction where status = \"finish\" or status = \"booked\"", nativeQuery = true)
	public Iterable<Transaction> findTransactionByStatus();
	
	@Query(value = "SELECT * FROM transaction limit 3 offset ?1", nativeQuery = true)
	public Iterable<Transaction> findTransactionCustom(int offset);
	
	@Query(value = "SELECT * FROM transaction order by total_price desc limit 3 offset ?1", nativeQuery = true)
	public Iterable<Transaction> findTransactionPriceDesc(int offset);
	
	@Query(value = "SELECT * FROM transaction order by total_price asc limit 3 offset ?1", nativeQuery = true)
	public Iterable<Transaction> findTransactionPriceAsc(int offset);
	
	@Query(value = "SELECT * FROM transaction order by created_at asc limit 3 offset ?1", nativeQuery = true)
	public Iterable<Transaction> findTransactionOldest(int offset);
	
	@Query(value = "SELECT * FROM transaction order by created_at desc limit 3 offset ?1", nativeQuery = true)
	public Iterable<Transaction> findTransactionLatest(int offset);
	
	@Query(value = "SELECT * FROM transaction where user_id = ?1", nativeQuery = true)
	public Iterable <Transaction> byUserId(int userId);
	
	@Query(value = "SELECT * FROM transaction where therapistdetail_id = ?1", nativeQuery = true)
	public Iterable <Transaction> byTherapistId(int therapistId);
	
 
}
