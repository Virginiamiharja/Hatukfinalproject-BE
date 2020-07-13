package com.backend.hatukfinalproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.backend.hatukfinalproject.entity.TherapistDetail;

public interface TherapistDetailRepo extends JpaRepository<TherapistDetail, Integer>  {
	
//	Untuk sorting 
	
	@Query(value = "SELECT AVG(rating) as avgrating, t.id, t.about, t.experience, t.jobdesc, t.service_fee, t.clinic_id, t.user_id FROM therapist_detail t LEFT JOIN review r ON r.therapistdetail_id = t.id group by t.id ORDER BY avgrating ASC", nativeQuery = true)
	public Iterable <TherapistDetail> getTherapistWithRatingAsc();
	
	@Query(value = "SELECT AVG(rating) as avgrating, t.id, t.about, t.experience, t.jobdesc, t.service_fee, t.clinic_id, t.user_id FROM therapist_detail t LEFT JOIN review r ON r.therapistdetail_id = t.id group by t.id ORDER avgrating DESC", nativeQuery = true)
	public Iterable <TherapistDetail> getTherapistWithRatingDesc();	

	@Query(value = "SELECT * from therapist_detail ORDER BY service_fee ASC", nativeQuery = true)
	public Iterable <TherapistDetail> getTherapistWithPriceAsc();	
		
	@Query(value = "SELECT * from therapist_detail ORDER BY service_fee DESC", nativeQuery = true)
	public Iterable <TherapistDetail> getTherapistWithPriceDesc();	

}
