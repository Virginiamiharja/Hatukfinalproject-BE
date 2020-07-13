package com.backend.hatukfinalproject.service;

import com.backend.hatukfinalproject.entity.TherapistDetail;

public interface TherapistDetailService {
	
	public Iterable <TherapistDetail> getAllTherapistDetails(String sortType);
	
	public TherapistDetail addTherapistDetail(int userId, int clinicId, TherapistDetail therapistDetail);
	
	public TherapistDetail addTherapistSpecialty(int therapistDetailId, int specialtyId);
	
	public TherapistDetail showTherapistDetailById(int therapistDetailId);
	
}
