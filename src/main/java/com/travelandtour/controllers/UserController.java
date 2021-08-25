package com.travelandtour.controllers;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.travelandtour.repository.*;
import com.travelandtour.model.*;
import com.travelandtour.service.*;
import com.travelandtour.dataclass.*;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	UserRepository repo1;
	@Autowired
	UserAddressRepository repo2;
	
	@Autowired
	UserService service;
	
	@PostMapping("/save")
	public User saveUser(@RequestBody User user) throws Exception
	{
		String email=user.getEmail();
		if(email!=null && !email.equals(""))
		{
			User obj=service.fetchUserByEmailId(email);
			if(obj!=null)
			{
				throw new Exception("user with "+email+" is already exist");
			}
		}
		String phone=user.getPhoneno();
		if(phone!=null && !phone.equals(""))
		{
			User obj=service.fetchUserByPhoneno(phone);
			if(obj!=null)
			{
				throw new Exception("user with "+phone+" is already exist");
			}
		}
		User obj=null;
		obj=service.saveUser(user);
		
		return obj;
		
	}

	
	@PostMapping("/login")
	public User saveStudent(@RequestBody User user) throws Exception
	{
		String email=user.getEmail();
		String password=user.getPassword();
		User obj=null;
		if(email!=null && password!=null)
		{
			obj=service.fetchUserByEmailAndPassword(email, password);
		}
		if(obj==null)
		{
			throw new Exception("Bad credential");
		}
		
		return obj;
	}
	@PostMapping("/contactus")
	public Contact saveStudent(@RequestBody Contact contact) throws Exception
	{
		Contact obj=service.saveContactUs(contact);
		
		return obj;
	}
	
	@PostMapping("/save_transport")
	public Transport saveTransport(@RequestBody Transport transport) throws Exception
	{
		Transport obj=service.saveTransport(transport);
		
		return obj;
	}
	@PostMapping("/save_booking")
	public Booking saveBooking(@RequestBody BookingData booking) throws Exception
	{
		Booking obj=service.saveBooking(booking);
		
		return obj;
	}
	
	@PostMapping("/save_feedback")
	public Feedback saveFeedback(@RequestBody Feedback feedback) throws Exception
	{
		System.out.println(feedback);
		Feedback obj=service.saveFeedback(feedback);
		
		return obj;
	}
	
	
	@GetMapping("/get_transport")
	public List<Transport> getTransport(@RequestParam(defaultValue = "empty") String source,@RequestParam(defaultValue = "empty") String destination,@RequestParam Integer type) 
	{
			//System.out.println("s="+source+" d="+destination+" t"+type);
			List<Transport> obj=service.fetchTransport(source,destination,type);
			//System.out.println("list="+obj);
			return obj;
	}
	
	@GetMapping("/get_history")
	public List<Booking> getBookingHistory(@RequestParam Long user_id) 
	{
			//System.out.println("s="+source+" d="+destination+" t"+type);
			List<Booking> obj=service.fetchBookingHistory(user_id);
			//System.out.println("list="+obj);
			return obj;
	}
	
	@GetMapping("/cancel_booking")
	public List<Booking> cancelBooking(@RequestParam Long booking_id) 
	{
			//System.out.println("s="+source+" d="+destination+" t"+type);
			List<Booking> obj=service.cancelBooking(booking_id);
			//System.out.println("list="+obj);
			return obj;
	}
	
	



}
