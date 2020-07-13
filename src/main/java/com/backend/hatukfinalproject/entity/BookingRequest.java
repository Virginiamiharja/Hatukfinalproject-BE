package com.backend.hatukfinalproject.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BookingRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "serviceschedule_id")
	@JsonIgnore
	private TherapistServiceSchedule serviceSchedule;
	private Date serviceDate;
	private String status;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "transaction_id")
	@JsonIgnore
	private Transaction transaction;
	
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
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
	public TherapistServiceSchedule getServiceSchedule() {
		return serviceSchedule;
	}
	public void setServiceSchedule(TherapistServiceSchedule serviceSchedule) {
		this.serviceSchedule = serviceSchedule;
	}
	public Date getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
