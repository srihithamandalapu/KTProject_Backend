package com.app.Project.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Project.DTO.Commentor;
import com.app.Project.DTO.SubscriptionDetails;
import com.app.Project.model.Category;
import com.app.Project.model.Comment;
import com.app.Project.model.User;
import com.app.Project.model.Video;
import com.app.Project.repository.RegistrationRepository;
import com.app.Project.service.CategoryService;
import com.app.Project.service.CommentService;
import com.app.Project.service.RegistrationService;

@RestController
public class RegistrationController {
	
	
	@Autowired
	private RegistrationService service;
	
	@Autowired
	private CategoryService cservice;
	
	@Autowired
	RegistrationRepository regrepo;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempEmailId= user.getEmailId();
			if( (tempEmailId!=null) && !"".equals(tempEmailId)) {
				if(service.fetchUserByEmailId(tempEmailId)!=null) {
					throw new Exception("User Exists");
					}
				}
		
		User userObj=null;
		userObj=service.saveUser(user);
		return userObj;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception {
			String tempEmailId = user.getEmailId();
			String tempPass = user.getPassword();
			User userObj=null;
			if (tempEmailId !=null && tempPass !=null) {
				userObj= service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
			}
			if(tempEmailId!=null && tempPass==null) {
				user.setIsadmin("No");
				userObj=service.saveUser(user);
			}
			if(userObj == null ) {
				throw new Exception("user does not exsist");

			}
			
			return userObj;
		}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="/getUser", produces="application/json")
	public List<User> getUser() {
		return service.getUser();
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="/getSubscribedCategory/{id}",produces = "application/json")
	public Set<Category> getSubsCat(@PathVariable("id") int userId){
		User uObj = service.fetchUserById(userId);
		return uObj.getCat();
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="/getSubscribedDetails/{id}",produces = "application/json")
	public List<SubscriptionDetails> getSubsDetails(@PathVariable("id") int userId){
		User uObj = service.fetchUserById(userId);
		List<SubscriptionDetails> setOfResults= new ArrayList<SubscriptionDetails>();
		
		Set<Category> subscribed = uObj.getCat();
		List<Category> subscribedList= new ArrayList<Category>();
		subscribedList.addAll(subscribed);
		
		List<Category> allCats= cservice.getCategory();
		
		for(Category obj : subscribedList) {
			SubscriptionDetails subsDetObj=new SubscriptionDetails();
			subsDetObj.setCatName(obj.getCategoryName());
			subsDetObj.setSubs(true);
			setOfResults.add(subsDetObj);
		}
		
		allCats.removeAll(subscribedList);
		
		for(Category obj : allCats) {
			SubscriptionDetails subsDetObj1=new SubscriptionDetails();
			subsDetObj1.setCatName(obj.getCategoryName());
			subsDetObj1.setSubs(false);
			setOfResults.add(subsDetObj1);
		}
		
		return setOfResults;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="getAllUserVideos/{id}")
	public Set<Video> getAllVids(@PathVariable("id") int userId){
		User uObj=service.fetchUserById(userId);
		return uObj.getVidsPosted();
	}
	
	
	
}
