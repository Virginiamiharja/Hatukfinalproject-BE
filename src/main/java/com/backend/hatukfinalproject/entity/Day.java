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
public class Day {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String dayName;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "day", cascade = CascadeType.ALL) 
	@JsonIgnore
	private List<TherapistServiceSchedule> therapistServiceSchedules;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	public List<TherapistServiceSchedule> getTherapistServiceSchedules() {
		return therapistServiceSchedules;
	}
	public void setTherapistServiceSchedules(List<TherapistServiceSchedule> therapistServiceSchedules) {
		this.therapistServiceSchedules = therapistServiceSchedules;
	}
}
