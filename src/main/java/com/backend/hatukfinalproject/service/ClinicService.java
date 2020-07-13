package com.backend.hatukfinalproject.service;

import com.backend.hatukfinalproject.entity.Clinic;

public interface ClinicService {
	
	public Iterable <Clinic> getAllClinics();
	
	public Clinic addClinic(Clinic clinic, int cityId);

}
