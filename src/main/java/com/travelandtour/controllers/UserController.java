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
	
	@PutMapping("/modify/{id}")
	public ResponseEntity<String> updateStudent(@PathVariable Long id,@RequestBody Address address)
	{
		ResponseEntity<String> resp =null;

		try {
			
			Optional<Address> opt =  repo2.findById(id);
			if(opt.isPresent()) {
				
				Address actual = opt.get();
				if(address.getStreet()!=null)
				{
					actual.setStreet(address.getStreet());
				}
				if(address.getCity()!=null)
				{
					actual.setCity(address.getCity());
				}
				if(address.getState()!=null)
				{
					actual.setState(address.getState());
				}
				
				repo2.save(actual);
				
				
				resp = new ResponseEntity<String>(
						"Student '"+id+"' Updated", 
						//HttpStatus.RESET_CONTENT
						HttpStatus.OK
						);
			} else {
				
				resp = new ResponseEntity<String>(
						"Student '"+id+"' not found", 
						//HttpStatus.RESET_CONTENT
						HttpStatus.BAD_REQUEST
						);
			}

		} catch (Exception e) {
			resp = new ResponseEntity<String>(
					"Unable to process Update",
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeStudent(@PathVariable Long id)
	{
		ResponseEntity<String> resp = null;
		try {
			boolean exist = repo2.existsById(id);
			if(exist) {
				repo2.deleteById(id);
				resp = new ResponseEntity<String>(
						"Student '"+id+"' deleted",
						HttpStatus.OK);
			} else {
				
				resp = new ResponseEntity<String>(
						"Student '"+id+"' not exist",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			
			resp = new ResponseEntity<String>(
					"Unable to delete", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllStudents() {
		
		ResponseEntity<?> resp = null ;
		try {

			List<Address> list = repo2.findAll();
			if(list!=null && !list.isEmpty()) {
				
				list.sort((s1,s2)->s1.getStreet().compareTo(s2.getStreet()));
				/* JDK 1.8
				list = list.stream()
						.sorted((s1,s2)->s1.getName().compareTo(s2.getName()))
						.collect(Collectors.toList());
				 */
				resp = new ResponseEntity<List<Address>>(list, HttpStatus.OK);
			} else {
				

				//resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				resp = new ResponseEntity<String>(
						"No Students Found",
						HttpStatus.OK);
			}
		} catch (Exception e) {
			

			resp =  new ResponseEntity<String>(
					"Unable to Fetch Students", 
					HttpStatus.INTERNAL_SERVER_ERROR); //500
			e.printStackTrace();
		}
		
		return resp;
	}
	@GetMapping("/one/{id}")
	public Optional<User> getOneStudent(@PathVariable Long id) 
	{
	
			Optional<User> opt =  repo1.findById(id);
			//if(opt.isPresent()) 
				//resp = new ResponseEntity<User>(opt.get(), HttpStatus.OK);
			
		return opt;
	}
	@GetMapping("/get_transport")
	public List<Transport> getTransport(@RequestParam(defaultValue = "empty") String source,@RequestParam(defaultValue = "empty") String destination,@RequestParam Integer type) 
	{
			//System.out.println("s="+source+" d="+destination+" t"+type);
			List<Transport> obj=service.fetchTransport(source,destination,type);
			//System.out.println("list="+obj);
			return obj;
	}



}
