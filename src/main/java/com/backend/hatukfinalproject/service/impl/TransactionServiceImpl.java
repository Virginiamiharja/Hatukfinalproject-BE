package com.backend.hatukfinalproject.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hatukfinalproject.dao.BookingRequestRepo;
import com.backend.hatukfinalproject.dao.TransactionRepo;
import com.backend.hatukfinalproject.dao.UserRepo;
import com.backend.hatukfinalproject.entity.Transaction;
import com.backend.hatukfinalproject.entity.User;
import com.backend.hatukfinalproject.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepo transactionRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BookingRequestRepo bookingRepo;
	
	
	@Override
	public Iterable<Transaction> getAllTransaction() {
		return transactionRepo.findAll();
	}

	@Override
	public Transaction addTransaction(int userId, Transaction transaction) {
		Date createdAt = new Date();
		
		User findUser = userRepo.findById(userId).get();
		
		transaction.setUser(findUser);
		transaction.setCreatedAt(createdAt);
		
		return transactionRepo.save(transaction);
	}

	@Override
	public Transaction changeStatus(int transactionId, String status) {
		Transaction transaction = transactionRepo.findById(transactionId).get();
		transaction.setStatus(status);
		
//		Jadi disini biar kalo kita update si transaction, status booking request juga ke update
		transaction.getBookingRequests().forEach(booking -> {
			booking.setStatus(status);
//			Ini masih temporary si
			booking.getServiceSchedule().setIsbooked(true);
			bookingRepo.save(booking);
		});
		return transactionRepo.save(transaction);
	}

}
