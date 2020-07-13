package com.backend.hatukfinalproject.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hatukfinalproject.dao.BookingRequestRepo;
import com.backend.hatukfinalproject.dao.TransactionRepo;
import com.backend.hatukfinalproject.dao.UserRepo;
import com.backend.hatukfinalproject.entity.Transaction;
import com.backend.hatukfinalproject.entity.User;
import com.backend.hatukfinalproject.service.TransactionService;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	@GetMapping
	public Iterable<Transaction> getAllTransaction() {
		return transactionService.getAllTransaction();
	}
	
	@PostMapping("/add")
	public Transaction addTransaction (@RequestParam int userId, @RequestBody Transaction transaction) {
		return transactionService.addTransaction(userId, transaction);
	}
	
	@PutMapping("/changestatus")
	public Transaction changeStatus (@RequestParam int transactionId, @RequestParam String status) {
		return transactionService.changeStatus(transactionId, status);
	}
	
//	@putMapping("/uploadTransfer")
//	public Transaction changeStatus (@RequestParam )
	
}
