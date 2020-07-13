package com.backend.hatukfinalproject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Specialty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String specialtyName;
//	Column untuk nyambungin category dengan therapist
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable (name = "therapistdetail_specialty", joinColumns = @JoinColumn(name = "specialty_id"), inverseJoinColumns = @JoinColumn (name = "therapistdetail_id"))
	@JsonIgnore
	private List<TherapistDetail> therapistdetails;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSpecialtyName() {
		return specialtyName;
	}
	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}
	public List<TherapistDetail> getTherapistdetails() {
		return therapistdetails;
	}
	public void setTherapistdetails(List<TherapistDetail> therapistdetails) {
		this.therapistdetails = therapistdetails;
	}
}
