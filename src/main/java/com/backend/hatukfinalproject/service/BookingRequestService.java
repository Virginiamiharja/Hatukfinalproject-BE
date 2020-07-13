package com.backend.hatukfinalproject.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.hatukfinalproject.entity.BookingRequest;

public interface BookingRequestService {
	
	public Iterable<BookingRequest> getAllBookingRequests();
	
	public BookingRequest bookSchedule(int transactionId, int userId, int scheduleId, BookingRequest bookingRequest);

}
