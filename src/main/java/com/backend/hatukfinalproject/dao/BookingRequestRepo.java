package com.backend.hatukfinalproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.hatukfinalproject.entity.BookingRequest;

public interface BookingRequestRepo extends JpaRepository<BookingRequest, Integer> {

}
