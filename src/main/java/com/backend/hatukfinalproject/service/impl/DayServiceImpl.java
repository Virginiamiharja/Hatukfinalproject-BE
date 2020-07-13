package com.backend.hatukfinalproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hatukfinalproject.dao.DayRepo;
import com.backend.hatukfinalproject.entity.Day;
import com.backend.hatukfinalproject.service.DayService;

@Service
public class DayServiceImpl implements DayService {
	
	@Autowired
	private DayRepo dayRepo;

	@Override
	public Iterable<Day> getAllDays() {
		return dayRepo.findAll();
	}

}
