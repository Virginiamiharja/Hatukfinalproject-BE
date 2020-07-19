package com.backend.hatukfinalproject.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.hatukfinalproject.dao.BookingRequestRepo;
import com.backend.hatukfinalproject.dao.TransactionRepo;
import com.backend.hatukfinalproject.dao.UserRepo;
import com.backend.hatukfinalproject.entity.Transaction;
import com.backend.hatukfinalproject.entity.User;
import com.backend.hatukfinalproject.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

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
	
	private String uploadPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\";

	@GetMapping("/custom")
	public Iterable<Object[]> findTransactionCustom() {
		return transactionService.findTransactionCustom();
	}
	
//	Liat di implnya
	@GetMapping
	public Iterable<Transaction> getAllTransaction(@RequestParam int offset, @RequestParam String type) {
		return transactionService.getAllTransaction(offset, type);
	}
	
	@PostMapping("/add")
	public Transaction addTransaction (@RequestParam int therapistId, @RequestParam int scheduleId, @RequestParam int userId, @RequestBody Transaction transaction) {
		return transactionService.addTransaction(therapistId, scheduleId, userId, transaction);
	}
	
	@PutMapping("/changestatus")
	public Transaction changeStatus (@RequestBody Transaction transaction) {
		return transactionService.changeStatus(transaction);
	}

	@PutMapping("/uploadtransfer")
	public Transaction uploadTransfer(int transactionId, MultipartFile trxPicture)
			throws JsonMappingException, JsonProcessingException {
		Transaction findTransaction = transactionRepo.findById(transactionId).get();

//		Set penamaan dan location si picture
		String fileExtension = trxPicture.getContentType().split("/")[1];
		String newFileName = "ProfilePicture-" + findTransaction.getId() + "." + fileExtension;
		String fileName = StringUtils.cleanPath(newFileName);
		Path path = Paths.get(StringUtils.cleanPath(uploadPath) + fileName);
		
		try {
			Files.copy(trxPicture.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String image = ServletUriComponentsBuilder.fromCurrentContextPath().path("/documents/download/").path(fileName).toUriString();
		findTransaction.setStatus("pending");
		findTransaction.setImage(image);
		
		return transactionRepo.save(findTransaction);
	}
	

	
}
