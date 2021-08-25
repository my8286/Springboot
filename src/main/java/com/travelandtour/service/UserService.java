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
	
	public Booking saveBooking(BookingData data)
	{
		Payment payment=new Payment(data.getPaymentDate(),data.getPaymentTime());
		payment=payment_repo.save(payment);
		
		Passenger passenger=new Passenger(data.getFirstName(),data.getLastName(),data.getGender(),data.getAge());
		passenger=passenger_repo.save(passenger);
		
		Booking booking=new Booking(data.getJourneyDate(),data.getUserId(),payment.getPaymentId(),passenger.getPassengerId(),data.getTransportId());
		return booking_repo.save(booking);
	}
}
