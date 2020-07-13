package com.backend.hatukfinalproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hatukfinalproject.dao.SpecialtyRepo;
import com.backend.hatukfinalproject.entity.Specialty;
import com.backend.hatukfinalproject.service.SpecialtyService;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
	
	@Autowired
	private SpecialtyRepo specialtyRepo;

	@Override
	public Iterable<Specialty> showSpecialties() {
		return specialtyRepo.findAll();
	}
	
}
