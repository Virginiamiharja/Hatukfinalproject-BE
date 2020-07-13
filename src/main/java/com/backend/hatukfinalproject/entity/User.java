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
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String username;
	private String email;
	private String password;
	private String phoneNumber;
	private String image;
	private String role;
//	Field untuk nampung cityId
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "city_id")
	private City city;
	private String subdistrict;
	private String area;
	private String rt;
	private String rw;
	private String address;
	private boolean isVerified;
	private String verifyToken;
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	// Kadang di get dia ngeloop banyak gitu jadi kasih ini buat jaga2
	@JsonIgnore
	private TherapistDetail therapistDetail;
//	Untuk nyambungin sama rating
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL) 
	@JsonIgnore
	private List<Review> reviews;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL) 
//	@JsonIgnore
	private List<BookingRequest> requests;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL) 
//	@JsonIgnore
	private List<Transaction> transactions;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL) 
//	@JsonIgnore
	private List<TransactionDetail> transactionDetails;
	
	public List<TransactionDetail> getTransactionDetails() {
		return transactionDetails;
	}
	public void setTransactionDetails(List<TransactionDetail> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public TherapistDetail getTherapistDetail() {
		return therapistDetail;
	}
	public void setTherapistDetail(TherapistDetail therapistDetail) {
		this.therapistDetail = therapistDetail;
	}
	public boolean isVerified() {
		return isVerified;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	public String getVerifyToken() {
		return verifyToken;
	}
	public void setVerifyToken(String verifyToken) {
		this.verifyToken = verifyToken;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public List<BookingRequest> getRequests() {
		return requests;
	}
	public void setRequests(List<BookingRequest> requests) {
		this.requests = requests;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	
}
