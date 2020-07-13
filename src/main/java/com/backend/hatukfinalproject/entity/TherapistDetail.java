package com.backend.hatukfinalproject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TherapistDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	Field untuk nampung userId
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	private String jobdesc;
// Temporary tipe datanya, mungkin aja nanti kita mintanya start date awal dia mulai jadi terapis jdi nanti tinggal dihitung berdasarkan tahun aja
	private int experience;
	private int serviceFee;
	@Column(columnDefinition = "TEXT")
	private String about;
//	Field untuk nampung clinicId
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "clinic_id")
	private Clinic clinic;
//	Column untuk nyambungin therapist dengan category
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable (name = "therapistdetail_specialty", joinColumns = @JoinColumn(name = "therapistdetail_id"), inverseJoinColumns = @JoinColumn (name = "specialty_id"))
	private List<Specialty> specialties;
//	Untuk nyambungin sama rating
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "therapistDetail", cascade = CascadeType.ALL) 
	private List<Review> reviews;
//	Untuk nyambungin sama schedule
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "therapistDetail", cascade = CascadeType.ALL) 
	private List<TherapistServiceSchedule> therapistServiceSchedules;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getJobdesc() {
		return jobdesc;
	}
	public void setJobdesc(String jobdesc) {
		this.jobdesc = jobdesc;
	}
	public Clinic getClinic() {
		return clinic;
	}
	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(int serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public List<Specialty> getSpecialties() {
		return specialties;
	}
	public void setSpecialties(List<Specialty> specialties) {
		this.specialties = specialties;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public List<TherapistServiceSchedule> getTherapistServiceSchedules() {
		return therapistServiceSchedules;
	}
	public void setTherapistServiceSchedules(List<TherapistServiceSchedule> therapistServiceSchedules) {
		this.therapistServiceSchedules = therapistServiceSchedules;
	}
	
}
