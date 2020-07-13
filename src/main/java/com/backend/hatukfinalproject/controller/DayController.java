package com.backend.hatukfinalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hatukfinalproject.entity.Day;
import com.backend.hatukfinalproject.service.DayService;

@RestController
@CrossOrigin
@RequestMapping("/days")
public class DayController {
	
	@Autowired
	private DayService dayService;
	
	@GetMapping
	public Iterable<Day> getCities() {
		return dayService.getAllDays();
	}

}
