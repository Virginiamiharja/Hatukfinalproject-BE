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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TherapistServiceSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	Buat nandain hari yang ini bisa dibooking atau engga
	private boolean isbooked;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "therapistdetail_id")
	@JsonIgnore
	private TherapistDetail therapistDetail;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "day_id")
	private Day day;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "hour_id")
	private WorkingHour hour;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceSchedule", cascade = CascadeType.ALL) 
//	@JsonIgnore
	private List<BookingRequest> requests;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceSchedule", cascade = CascadeType.ALL) 
	@JsonIgnore
	private List<TransactionDetail> transactionDetails;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceSchedule", cascade = CascadeType.ALL) 
	@JsonIgnore
	private List<Transaction> transactions;
	
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public List<TransactionDetail> getTransactionDetails() {
		return transactionDetails;
	}
	public void setTransactionDetails(List<TransactionDetail> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
	public boolean isIsbooked() {
		return isbooked;
	}
	public void setIsbooked(boolean isbooked) {
		this.isbooked = isbooked;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TherapistDetail getTherapistDetail() {
		return therapistDetail;
	}
	public void setTherapistDetail(TherapistDetail therapistDetail) {
		this.therapistDetail = therapistDetail;
	}
	public Day getDay() {
		return day;
	}
	public void setDay(Day day) {
		this.day = day;
	}
	public WorkingHour getHour() {
		return hour;
	}
	public void setHour(WorkingHour hour) {
		this.hour = hour;
	}
	public List<BookingRequest> getRequests() {
		return requests;
	}
	public void setRequests(List<BookingRequest> requests) {
		this.requests = requests;
	}
	
	
}
