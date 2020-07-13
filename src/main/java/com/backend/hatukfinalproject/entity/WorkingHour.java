package com.backend.hatukfinalproject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class WorkingHour {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String hour;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hour", cascade = CascadeType.ALL) 
	@JsonIgnore
	private List<TherapistServiceSchedule> serviceSchedules;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public List<TherapistServiceSchedule> getServiceSchedules() {
		return serviceSchedules;
	}
	public void setServiceSchedules(List<TherapistServiceSchedule> serviceSchedules) {
		this.serviceSchedules = serviceSchedules;
	}
}
