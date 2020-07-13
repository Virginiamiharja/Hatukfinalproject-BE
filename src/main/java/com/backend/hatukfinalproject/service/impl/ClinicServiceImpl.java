package com.backend.hatukfinalproject.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hatukfinalproject.dao.CityRepo;
import com.backend.hatukfinalproject.dao.ClinicRepo;
import com.backend.hatukfinalproject.entity.City;
import com.backend.hatukfinalproject.entity.Clinic;
import com.backend.hatukfinalproject.service.ClinicService;

@Service
public class ClinicServiceImpl implements ClinicService {
	
	@Autowired
	private ClinicRepo clinicRepo;
	
	@Autowired
	private CityRepo cityRepo;

	@Override
	@Transactional
	public Iterable<Clinic> getAllClinics() {
		return clinicRepo.findAll();
	}

	@Override
	public Clinic addClinic(Clinic clinic, int cityId) {
		City findCity = cityRepo.findById(cityId).get();
		
		clinic.setId(0);
		clinic.setCity(findCity);
		return clinicRepo.save(clinic);
	}

	

}
