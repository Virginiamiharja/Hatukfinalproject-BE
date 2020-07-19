package com.backend.hatukfinalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hatukfinalproject.dao.BookingRequestRepo;
import com.backend.hatukfinalproject.dao.ClinicRepo;
import com.backend.hatukfinalproject.dao.ReviewRepo;
import com.backend.hatukfinalproject.dao.TherapistDetailRepo;
import com.backend.hatukfinalproject.dao.TherapistServiceScheduleRepo;
import com.backend.hatukfinalproject.dao.TransactionRepo;
import com.backend.hatukfinalproject.dao.UserRepo;
import com.backend.hatukfinalproject.entity.Clinic;
import com.backend.hatukfinalproject.entity.TherapistDetail;
import com.backend.hatukfinalproject.entity.User;
import com.backend.hatukfinalproject.service.TherapistDetailService;

@RestController
@CrossOrigin
@RequestMapping("/therapistdetails")
public class TherapistDetailController {
	
	@Autowired
	private TherapistDetailService therapistDetailService;
	
	@Autowired
	private TherapistDetailRepo therapistDetailRepo;
	
	@Autowired
	private ClinicRepo clinicRepo;
	
	@Autowired
	private ReviewRepo reviewRepo;
	
	@Autowired
	private TherapistServiceScheduleRepo scheduleRepo;
	
	@Autowired
	private BookingRequestRepo bookingRepo;
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	
	@GetMapping("/pure")
	public Iterable<TherapistDetail> getAll() {
		return therapistDetailRepo.findAll();
	}
	
	@GetMapping("/custom")
	public Iterable<Object[]> findTherapistCustom() {
		return therapistDetailService.findTherapistCustom();
	}
	
//	Belom di taro di service impl
	@PutMapping("/edittherapist")
	public TherapistDetail editTherapist(@RequestBody TherapistDetail therapist, @RequestParam int clinicId) {
		TherapistDetail findTherapist = therapistDetailRepo.findById(therapist.getId()).get();
		Clinic findClinic = clinicRepo.findById(clinicId).get();
		
		findTherapist.setClinic(findClinic);
		findTherapist.setAbout(therapist.getAbout());
		findTherapist.setExperience(therapist.getExperience());
		findTherapist.setJobdesc(therapist.getJobdesc());
		findTherapist.setServiceFee(therapist.getServiceFee());
		return therapistDetailRepo.save(findTherapist); 
	}
	
	@GetMapping
	public Iterable<TherapistDetail> showTherapistDetails(@RequestParam int offset, @RequestParam String sortType) {
		return therapistDetailService.getAllTherapistDetails(offset, sortType);
	}
	
	@GetMapping("/{therapistDetailId}")
	public TherapistDetail showTherapistDetailById(@PathVariable int therapistDetailId) {
		return therapistDetailService.showTherapistDetailById(therapistDetailId);
	}
	
	@GetMapping("/findbyuser")
	public TherapistDetail findByUserId(@RequestParam int userId) {
		return therapistDetailRepo.findByUserId(userId);
	}
	
	@PostMapping("/addtherapistdetail")
	public TherapistDetail addTherapistDetail(@RequestParam int userId, @RequestParam int clinicId, @RequestBody TherapistDetail therapistDetail) {
		return therapistDetailService.addTherapistDetail(userId, clinicId, therapistDetail);
	}
	
	@GetMapping("/addtherapistspecialty")
	public TherapistDetail addTherapistSpecialty(@RequestParam int therapistDetailId, @RequestParam int specialtyId) {
		return therapistDetailService.addTherapistSpecialty(therapistDetailId, specialtyId);
	}
	
//	Belum dibikin service impl
	@DeleteMapping("/deletetherapistspecialty")
	public TherapistDetail deleteTherapistSpecialty(@RequestParam int therapistId) {
		TherapistDetail findTherapist = therapistDetailRepo.findById(therapistId).get();
		findTherapist.setSpecialties(null);
		return therapistDetailRepo.save(findTherapist);
	}
	
//	Masih temporary
	@DeleteMapping("/deletetherapist")
	public void deleteTherapist(@RequestParam int therapistId) {
		TherapistDetail findTherapist = therapistDetailRepo.findById(therapistId).get();
		
		User findUser = findTherapist.getUser();
//		Memutuskan hubungan 
		findTherapist.setUser(null); 
		findTherapist.setClinic(null);
		findTherapist.setSpecialties(null);
		
		findTherapist.getReviews().forEach(review -> {
			reviewRepo.delete(review);
		});
		
		
		findTherapist.getTherapistServiceSchedules().forEach(schedule -> {
			scheduleRepo.delete(schedule);
		});
		
		findTherapist.getTransactions().forEach(trx -> {
			trx.getBookingRequests().forEach(request -> {
				bookingRepo.delete(request);
			});
			transactionRepo.delete(trx);
		});
	
		userRepo.delete(findUser);
		therapistDetailRepo.delete(findTherapist);
	}
	
	

}
