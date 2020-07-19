package com.backend.hatukfinalproject.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hatukfinalproject.dao.BookingRequestRepo;
import com.backend.hatukfinalproject.dao.TherapistDetailRepo;
//import com.backend.hatukfinalproject.dao.ScalarFunctionRepo;
import com.backend.hatukfinalproject.dao.TherapistServiceScheduleRepo;
import com.backend.hatukfinalproject.dao.TransactionRepo;
import com.backend.hatukfinalproject.dao.UserRepo;
import com.backend.hatukfinalproject.entity.TherapistDetail;
import com.backend.hatukfinalproject.entity.TherapistServiceSchedule;
import com.backend.hatukfinalproject.entity.Transaction;
import com.backend.hatukfinalproject.entity.User;
import com.backend.hatukfinalproject.service.TransactionService;
import com.backend.hatukfinalproject.util.EmailUtil;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepo transactionRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BookingRequestRepo bookingRepo;
	
	@Autowired
	private TherapistServiceScheduleRepo scheduleRepo;
	
	@Autowired
	private TherapistDetailRepo therapistRepo;
	
	@Autowired
	private EmailUtil emailUtil;
	
	
	@Override
	public Iterable<Transaction> getAllTransaction(int offset, String type) {
		if(type.contains("oldest")) {
			return transactionRepo.findTransactionOldest(offset);
		} else if(type.contains("latest")) {
			return transactionRepo.findTransactionLatest(offset);
		} else if(type.contains("pricedesc")) {
			return transactionRepo.findTransactionPriceDesc(offset);
		} else if(type.contains("priceasc")) {
			return transactionRepo.findTransactionPriceAsc(offset);
		} else if (type.contains("dashboard")) {
			return transactionRepo.findTransactionByStatus();
		}
		
		return transactionRepo.findTransactionCustom(offset);

	}

	@Override
	public Transaction addTransaction(int therapistId, int scheduleId, int userId, Transaction transaction) {
		Date createdAt = new Date();
		
		User findUser = userRepo.findById(userId).get();
		TherapistServiceSchedule findSchedule = scheduleRepo.findById(scheduleId).get();
		TherapistDetail findTherapist = therapistRepo.findById(therapistId).get();
		
		transaction.setUser(findUser);
		transaction.setServiceSchedule(findSchedule);
		transaction.setTherapistDetail(findTherapist);
		transaction.setCreatedAt(createdAt);
		
		return transactionRepo.save(transaction);
	}

	@Override
	public Transaction changeStatus(Transaction transaction) {
		Transaction findTransaction = transactionRepo.findById(transaction.getId()).get();
		
		findTransaction.setStatus(transaction.getStatus());
		findTransaction.setReason(transaction.getReason());
		
// 		Dia bakal kirim email apabila status sudah booked
		if(transaction.getStatus().equals("booked")) {
			
			findTransaction.getBookingRequests().forEach(request -> {
				request.setStatus(transaction.getStatus());
			});
			
			String message = "<h1>Congrats! Your payment has been accepted</h1>\n";
			
//			message += "Dear ms/mr. " + transaction.getUser().getName() + "\n\n\n" ;
//			message += "Your appointment with ms/mr. " + transaction.getTherapistDetail().getUser().getName() + " has been approved";
			message += "Total price " + transaction.getTotalPrice() + " for " + transaction.getBookingRequests().size() + " meeting/s \n";
			transaction.getBookingRequests().forEach(request -> {
//				message += "Date : " + request.getServiceDate() + "\n";
			});
			message += "Thank you";
			this.emailUtil.sendEmail("virgnm@gmail.com", "Mom Story - Invoice", message);
			
		} else if (transaction.getStatus().equals("finish")) {
			
			findTransaction.getBookingRequests().forEach(request -> {
				request.setStatus(transaction.getStatus());
			});
		}
		
		transaction.setBookingRequests(findTransaction.getBookingRequests());
		return transactionRepo.save(findTransaction);
	}

	@Override
	public Iterable<Object[]> findTransactionCustom() {
		return transactionRepo.findTransactionDetails();
	}

}
