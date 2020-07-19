package com.backend.hatukfinalproject.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.hatukfinalproject.entity.Transaction;

public interface TransactionService {
	
	public Iterable<Transaction> getAllTransaction(int offset, String type);
	
	public Transaction addTransaction (int therapistId, int scheduleId, int userId, Transaction transaction);
	
	public Transaction changeStatus (Transaction transaction); 
	
	public Iterable<Object[]> findTransactionCustom();

}
