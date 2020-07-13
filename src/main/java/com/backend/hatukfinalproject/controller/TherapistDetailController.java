package com.backend.hatukfinalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hatukfinalproject.entity.TherapistDetail;
import com.backend.hatukfinalproject.service.TherapistDetailService;

@RestController
@CrossOrigin
@RequestMapping("/therapistdetails")
public class TherapistDetailController {
	
	@Autowired
	private TherapistDetailService therapistDetailService;
	
	@GetMapping
	public Iterable<TherapistDetail> showTherapistDetails(@RequestParam String sortType) {
		return therapistDetailService.getAllTherapistDetails(sortType);
	}
	
	@GetMapping("/{therapistDetailId}")
	public TherapistDetail showTherapistDetailById(@PathVariable int therapistDetailId) {
		return therapistDetailService.showTherapistDetailById(therapistDetailId);
	}
	
	@PostMapping("/addtherapistdetail")
	public TherapistDetail addTherapistDetail(@RequestParam int userId, @RequestParam int clinicId, @RequestBody TherapistDetail therapistDetail) {
		return therapistDetailService.addTherapistDetail(userId, clinicId, therapistDetail);
	}
	
	@PutMapping("/addtherapistspecialty")
	public TherapistDetail addTherapistSpecialty(@RequestParam int therapistDetailId, @RequestParam int specialtyId) {
		return therapistDetailService.addTherapistSpecialty(therapistDetailId, specialtyId);
	}
	
	

}
