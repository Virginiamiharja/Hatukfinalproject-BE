package com.backend.hatukfinalproject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Clinic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String clinicName;
	private String phoneNumber;
//	Temporary ni si image
	private String image;
//	Field untuk nampung cityId
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "city_id")
	private City city;
	private String subdistrict;
	private String area;
	private String rt;
	private String rw;
	private String address;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clinic", cascade = CascadeType.ALL) 
	@JsonIgnore
	private List<TherapistDetail> therapistDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getSubdistrict() {
		return subdistrict;
	}

	public void setSubdistrict(String subdistrict) {
		this.subdistrict = subdistrict;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRt() {
		return rt;
	}

	public void setRt(String rt) {
		this.rt = rt;
	}

	public String getRw() {
		return rw;
	}

	public void setRw(String rw) {
		this.rw = rw;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<TherapistDetail> getTherapistDetails() {
		return therapistDetails;
	}

	public void setTherapistDetails(List<TherapistDetail> therapistDetails) {
		this.therapistDetails = therapistDetails;
	}
}
