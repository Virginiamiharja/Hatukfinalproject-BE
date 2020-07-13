package com.backend.hatukfinalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hatukfinalproject.entity.City;
import com.backend.hatukfinalproject.service.CityService;

@RestController
@CrossOrigin
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping
	public Iterable<City> getCities() {
		return cityService.getAllCities();
	}
}
