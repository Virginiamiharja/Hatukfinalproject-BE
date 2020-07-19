package com.backend.hatukfinalproject.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hatukfinalproject.dao.ClinicRepo;
import com.backend.hatukfinalproject.dao.SpecialtyRepo;
import com.backend.hatukfinalproject.dao.TherapistDetailRepo;
import com.backend.hatukfinalproject.dao.UserRepo;
import com.backend.hatukfinalproject.entity.Clinic;
import com.backend.hatukfinalproject.entity.Specialty;
import com.backend.hatukfinalproject.entity.TherapistDetail;
import com.backend.hatukfinalproject.entity.User;
import com.backend.hatukfinalproject.service.TherapistDetailService;

@Service
public class TherapistDetailServiceImpl implements TherapistDetailService {

	@Autowired
	private TherapistDetailRepo therapistDetailRepo;
	
	@Autowired
	private ClinicRepo clinicRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private SpecialtyRepo specialtyRepo;
	
	@Override
	@Transactional
	public Iterable<TherapistDetail> getAllTherapistDetails(int offset, String sortType) {
		if(sortType.contains("ratingdesc")) {
			return therapistDetailRepo.getTherapistWithRatingDesc(offset);
		} else if(sortType.contains("ratingasc")) {
			return therapistDetailRepo.getTherapistWithRatingAsc(offset);
		} else if(sortType.contains("pricedesc")) {
			return therapistDetailRepo.getTherapistWithPriceDesc(offset);
		} else if(sortType.contains("priceasc")) {
			return therapistDetailRepo.getTherapistWithPriceAsc(offset);
		} else if (sortType.contains("dashboard")) {
			return therapistDetailRepo.findAll();
		}
		return therapistDetailRepo.getTherapistCustom(offset);
	}

	@Override
	public TherapistDetail addTherapistDetail(int userId, int clinicId, TherapistDetail therapistDetail) {
		User findUser = userRepo.findById(userId).get();
		Clinic findClinic = clinicRepo.findById(clinicId).get();
		
		therapistDetail.setId(0);
		therapistDetail.setUser(findUser);
		therapistDetail.setClinic(findClinic);
		return therapistDetailRepo.save(therapistDetail); 
	}

	@Override
	public TherapistDetail addTherapistSpecialty(int therapistDetailId, int specialtyId) {
		TherapistDetail findTherapist = therapistDetailRepo.findById(therapistDetailId).get();
		Specialty findSpecialty = specialtyRepo.findById(specialtyId).get();
		findTherapist.getSpecialties().add(findSpecialty);
		return therapistDetailRepo.save(findTherapist);
	}

	@Override
	public TherapistDetail showTherapistDetailById(int therapistDetailId) {
		return therapistDetailRepo.findById(therapistDetailId).get();
	}

	@Override
	public Iterable<Object[]> findTherapistCustom() {
		return therapistDetailRepo.findTherapistCustom();
	}

}
