package com.backend.hatukfinalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hatukfinalproject.dao.WorkingHourRepo;
import com.backend.hatukfinalproject.entity.WorkingHour;


@RestController
@CrossOrigin
@RequestMapping("/hours")
public class HourController {
	
	@Autowired
	private WorkingHourRepo hourRepo;
	
	@GetMapping
	public Iterable<WorkingHour> getHours() {
		return hourRepo.findAll();
	}


}

