package com.backend.hatukfinalproject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String cityName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = CascadeType.ALL) 
	@JsonIgnore
	private List<User> users;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = CascadeType.ALL) 
	@JsonIgnore
	private List<Clinic> clinics;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Clinic> getClinics() {
		return clinics;
	}
	public void setClinics(List<Clinic> clinics) {
		this.clinics = clinics;
	}
}
