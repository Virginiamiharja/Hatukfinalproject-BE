package com.backend.hatukfinalproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.backend.hatukfinalproject.entity.TherapistDetail;

public interface TherapistDetailRepo extends JpaRepository<TherapistDetail, Integer>  {
	
//	Untuk sorting 
	
	@Query(value = "SELECT AVG(rating) as avgrating, t.id, t.about, t.experience, t.jobdesc, t.service_fee, t.clinic_id, t.user_id FROM therapist_detail t LEFT JOIN review r ON r.therapistdetail_id = t.id group by t.id ORDER BY avgrating ASC limit 3 offset ?1", nativeQuery = true)
	public Iterable <TherapistDetail> getTherapistWithRatingAsc(int offset);
	
	@Query(value = "SELECT AVG(rating) as avgrating, t.id, t.about, t.experience, t.jobdesc, t.service_fee, t.clinic_id, t.user_id FROM therapist_detail t LEFT JOIN review r ON r.therapistdetail_id = t.id group by t.id ORDER BY avgrating DESC limit 3 offset ?1", nativeQuery = true)
	public Iterable <TherapistDetail> getTherapistWithRatingDesc(int offset);	

	@Query(value = "SELECT * from therapist_detail ORDER BY service_fee ASC limit 3 offset ?1", nativeQuery = true)
	public Iterable <TherapistDetail> getTherapistWithPriceAsc(int offset);	
		
	@Query(value = "SELECT * from therapist_detail ORDER BY service_fee DESC limit 3 offset ?1", nativeQuery = true)
	public Iterable <TherapistDetail> getTherapistWithPriceDesc(int offset);	
	
	@Query(value = "SELECT * from therapist_detail limit 3 offset ?1", nativeQuery = true)
	public Iterable <TherapistDetail> getTherapistCustom(int offset);
	
	@Query(value = "SELECT * from therapist_detail limit 3 offset ?1", nativeQuery = true)
	public Iterable <TherapistDetail> findAllCustom(int offset);
	
	@Query(value = "SELECT name, clinic_name, service_fee, count(*) as bookingtimes, sum(total_price) as earnings, td.id from user u JOIN therapist_detail td ON u.id = td.user_id JOIN clinic c on c.id = td.clinic_id JOIN transaction t ON t.therapistdetail_id = td.id where t.status = \"booked\" group by name limit 5 offset 0", nativeQuery = true)
	public List<Object[]> findTherapistCustom();
	
	@Query(value = "select * from therapist_detail where user_id = ?1", nativeQuery = true)
	public TherapistDetail findByUserId(int userId);
}
