package com.backend.hatukfinalproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hatukfinalproject.dao.BookingRequestRepo;
import com.backend.hatukfinalproject.dao.TherapistServiceScheduleRepo;
import com.backend.hatukfinalproject.dao.TransactionRepo;
import com.backend.hatukfinalproject.dao.UserRepo;
import com.backend.hatukfinalproject.entity.BookingRequest;
import com.backend.hatukfinalproject.entity.TherapistServiceSchedule;
import com.backend.hatukfinalproject.entity.Transaction;
import com.backend.hatukfinalproject.entity.User;
import com.backend.hatukfinalproject.service.BookingRequestService;

@Service
public class BookingRequestServiceImpl implements BookingRequestService {
	
	@Autowired
	private BookingRequestRepo requestRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TherapistServiceScheduleRepo scheduleRepo;
	
	@Autowired
	private TransactionRepo transactionRepo;

	@Override
	public Iterable<BookingRequest> getAllBookingRequests() {
		return requestRepo.findAll();
	}

	@Override
	public BookingRequest bookSchedule(int transactionId, int userId, int scheduleId, BookingRequest bookingRequest) {
		User user = userRepo.findById(userId).get();
		TherapistServiceSchedule schedule = scheduleRepo.findById(scheduleId).get();
		Transaction transaction = transactionRepo.findById(transactionId).get();
		
		bookingRequest.setTransaction(transaction);
		bookingRequest.setId(0);
		bookingRequest.setStatus("pending");
		bookingRequest.setServiceSchedule(schedule);
		bookingRequest.setUser(user);
		
		return requestRepo.save(bookingRequest);
	}

}
