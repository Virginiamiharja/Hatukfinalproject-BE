package com.backend.hatukfinalproject.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hatukfinalproject.dao.BookingRequestRepo;
import com.backend.hatukfinalproject.dao.TherapistServiceScheduleRepo;
import com.backend.hatukfinalproject.dao.UserRepo;
import com.backend.hatukfinalproject.entity.BookingRequest;
import com.backend.hatukfinalproject.entity.TherapistServiceSchedule;
import com.backend.hatukfinalproject.entity.User;
import com.backend.hatukfinalproject.service.BookingRequestService;

@RestController
@CrossOrigin
@RequestMapping("/requests")
public class BookingRequestController {
	
	@Autowired
	private BookingRequestService requestService;
	
	@GetMapping
	public Iterable<BookingRequest> getAllBookingRequest() {
		return requestService.getAllBookingRequests();
	}

	@PostMapping("/bookschedule")
	public BookingRequest bookSchedule(@RequestParam int transactionId, @RequestParam int userId, @RequestParam int scheduleId, @RequestBody BookingRequest bookingRequest) {
		return requestService.bookSchedule(transactionId, userId, scheduleId, bookingRequest);
	}
}
