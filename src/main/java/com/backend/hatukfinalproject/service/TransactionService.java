package com.backend.hatukfinalproject.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.hatukfinalproject.entity.Transaction;

public interface TransactionService {
	
	public Iterable<Transaction> getAllTransaction();
	
	public Transaction addTransaction (int userId, Transaction transaction);
	
	public Transaction changeStatus (int transactionId, String status); 

}
