package com.backend.hatukfinalproject.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hatukfinalproject.dao.CityRepo;
import com.backend.hatukfinalproject.entity.City;
import com.backend.hatukfinalproject.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepo cityRepo;
	
	
	@Override
	@Transactional
	public Iterable<City> getAllCities() {
		return cityRepo.findAll();
	}

}
