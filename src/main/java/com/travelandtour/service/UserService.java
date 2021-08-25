package com.travelandtour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.travelandtour.repository.*;
import com.travelandtour.model.*;
import com.travelandtour.dataclass.*;


@Service
public class UserService {
	@Autowired
	UserRepository user_repo;
	
	@Autowired
	ContactRepository contact_repo;
	
	@Autowired
	TransportRepository transport_repo;
	
	@Autowired
	BookingRepository booking_repo;
	
	@Autowired
	PaymentRepository payment_repo;
	
	@Autowired
	PassengerRepository passenger_repo;
	
	@Autowired
	FeedbackRepository feedback_repo;
	
	public User saveUser(User user)
	{
		return user_repo.save(user);
	}
	
	public User fetchUserByEmailId(String email)
	{
		return user_repo.findByEmail(email);
	}
	
	public User fetchUserByPhoneno(String phoneno)
	{
		return user_repo.findByPhoneno(phoneno);
	}
	
	public User fetchUserByEmailAndPassword(String email,String password)
	{
		return user_repo.findByEmailAndPassword(email,password);
	}
	public Contact saveContactUs(Contact contact)
	{
		return contact_repo.save(contact);
	}
	public Transport saveTransport(Transport transport)
	{
		return transport_repo.save(transport);
	}
	public List<Transport> fetchTransport(String source,String destination,Integer type)
	{	
		
		return transport_repo.findBySourceAndDestinationAndType(source,destination,type);
	}
	
	public Booking saveBooking(BookingData bd)
	{
		Booking b1=new Booking();
		b1.setJourneyDate(bd.getJourneyDate());
		b1.setStatus(1);
		
		Booking  b2=booking_repo.save(b1);
		
		Payment payment=new Payment(bd.getPaymentDate(),bd.getPaymentTime());
		payment=payment_repo.save(payment);
		
		Passenger passenger=new Passenger(bd.getFirstName(),bd.getLastName(),bd.getGender(),bd.getAge());
		passenger=passenger_repo.save(passenger);
		
		User user=user_repo.findByUserId(bd.getUserId());
	
		Transport transport=transport_repo.findByTransportId(bd.getTransportId());
		
		b2.setUser(user);
		b2.setTransport(transport);
		b2.setPayment(payment);
		b2.setPassenger(passenger);
		
		return booking_repo.save(b2);
	}
	
	public List<Booking> fetchBookingHistory(Long user_id)
	{	
		
		return booking_repo.findBookingHistory(user_id);
	}
	
	public List<Booking> cancelBooking(Long booking_id)
	{	
		Booking b1=booking_repo.findByBookingId(booking_id);
		b1.setStatus(0);
		
		Booking b2=booking_repo.save(b1);
		
		return booking_repo.findBookingHistory(b2.getUser().getUser_id());
	}
	
	public Feedback saveFeedback(Feedback feedback)
	{
		User user=user_repo.findByUserId(feedback.getUser().getUser_id());
		feedback.setUser(null);
		Feedback f1=feedback_repo.save(feedback);
		f1.setUser(user);
		return feedback_repo.save(f1);
	}

}
