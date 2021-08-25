package com.travelandtour.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table(name = "bookings")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id")
	private Long bookingId;
	private String journeyDate;
	private Long userId;
	private Long paymentId;
	private Long passengerId;
	private Long TransportId;
	
	
	
	
	public Booking(String journeyDate, Long userId, Long paymentId, Long passengerId, Long transportId) {
		super();
		this.journeyDate = journeyDate;
		this.userId = userId;
		this.paymentId = paymentId;
		this.passengerId = passengerId;
		TransportId = transportId;
	}
	public Long getTransportId() {
		return TransportId;
	}
	public void setTransportId(Long transportId) {
		TransportId = transportId;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public String getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Long getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}	
}
