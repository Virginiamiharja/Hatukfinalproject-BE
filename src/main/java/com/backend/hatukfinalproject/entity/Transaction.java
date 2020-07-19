package com.backend.hatukfinalproject.entity;

import java.util.Date;
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
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int totalPrice;
	private String image;
	private String status;
	private String reason;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "serviceschedule_id")
	@JsonIgnore
	private TherapistServiceSchedule serviceSchedule;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "therapistdetail_id")
	@JsonIgnore
	private TherapistDetail therapistDetail;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction", cascade = CascadeType.ALL) 
	@JsonIgnore
	private List<TransactionDetail> transactionDetails;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction", cascade = CascadeType.ALL) 
//	@JsonIgnore
	private List<BookingRequest> bookingRequests;
	private Date createdAt;
	
	public TherapistDetail getTherapistDetail() {
		return therapistDetail;
	}
	public void setTherapistDetail(TherapistDetail therapistDetail) {
		this.therapistDetail = therapistDetail;
	}
	public TherapistServiceSchedule getServiceSchedule() {
		return serviceSchedule;
	}
	public void setServiceSchedule(TherapistServiceSchedule serviceSchedule) {
		this.serviceSchedule = serviceSchedule;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public List<BookingRequest> getBookingRequests() {
		return bookingRequests;
	}
	public void setBookingRequests(List<BookingRequest> bookingRequests) {
		this.bookingRequests = bookingRequests;
	}
	public List<TransactionDetail> getTransactionDetails() {
		return transactionDetails;
	}
	public void setTransactionDetails(List<TransactionDetail> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
