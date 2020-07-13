package com.backend.hatukfinalproject.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int rating;
	private String comment;
	private LocalDate postedDate;
	
//	Field untuk nampung userId
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
//	@JsonIgnore
	private User user;
//	Field untuk nampung therapistId
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "therapistdetail_id")
	@JsonIgnore
	private TherapistDetail therapistDetail;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDate getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TherapistDetail getTherapistDetail() {
		return therapistDetail;
	}
	public void setTherapistDetail(TherapistDetail therapistDetail) {
		this.therapistDetail = therapistDetail;
	}
	
}
