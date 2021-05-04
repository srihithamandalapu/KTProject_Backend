package com.app.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Project.model.User;
import com.app.Project.service.RegistrationService;

@RestController
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
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
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception {
			String tempEmailId = user.getEmailId();
			String tempPass = user.getPassword();
			User userObj=null;
			if (tempEmailId !=null && tempPass !=null) {
				userObj= service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
			}
			if(userObj == null) {
				throw new Exception("User Does Not Exists!!");
			}
			return userObj;
		}
	
	
}
