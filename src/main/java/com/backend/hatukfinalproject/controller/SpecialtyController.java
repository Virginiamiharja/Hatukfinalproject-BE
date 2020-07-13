package com.backend.hatukfinalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hatukfinalproject.entity.Specialty;
import com.backend.hatukfinalproject.service.SpecialtyService;

@RestController
@CrossOrigin
@RequestMapping("/specialties")
public class SpecialtyController {
	
	@Autowired
	private SpecialtyService specialtyService;
	
	@GetMapping
	public Iterable<Specialty> showSpecialties(){
		return specialtyService.showSpecialties();
	}

}
