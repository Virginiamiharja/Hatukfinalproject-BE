package com.backend.hatukfinalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hatukfinalproject.entity.Clinic;
import com.backend.hatukfinalproject.service.ClinicService;

@RestController
@CrossOrigin
@RequestMapping("/clinics")
public class ClinicController {
	
	@Autowired 
	private ClinicService clinicService;
	
	@GetMapping
	public Iterable<Clinic> getAllClinics() {
		return clinicService.getAllClinics();
	}
	
	@PostMapping("/add")
	public Clinic addClinic(@RequestBody Clinic clinic, @RequestParam int cityId) {
		return clinicService.addClinic(clinic, cityId);
	}
	

}
