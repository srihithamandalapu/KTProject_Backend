package com.app.Project.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Project.model.Category;
import com.app.Project.model.Comment;
import com.app.Project.model.ReportedComments;
import com.app.Project.model.User;
import com.app.Project.model.Video;
import com.app.Project.service.CategoryService;
import com.app.Project.service.RegistrationService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService catservice;
	
	@Autowired
	RegistrationService rservice;
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="/getCategory", produces="application/json")
	public List<Category> getCategory() {
		return catservice.getCategory();
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value="/subscribe/{catName}/{userId}")
	public void SubscribeRequest(@PathVariable("catName") String catName,@PathVariable("userId") int userId) {
		User uObj=rservice.fetchUserById(userId);
		Category catObj=catservice.findByName(catName);
		Set<Category> setOfCategorySubscribedByUser=uObj.getCat();
		Set<User> setOfSubscription=catObj.getUser();
		if(!setOfCategorySubscribedByUser.contains(catObj)) {
			setOfCategorySubscribedByUser.add(catObj);
			uObj.setCat(setOfCategorySubscribedByUser);
			setOfSubscription.add(uObj);
			catObj.setUser(setOfSubscription);
			rservice.saveUser(uObj);
			catservice.saveCategory(catObj);
		}
		else {
			setOfCategorySubscribedByUser.remove(catObj);
			rservice.saveUser(uObj);
			setOfSubscription.remove(uObj);
			catservice.saveCategory(catObj);
		}
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="/getVideosByCat/{catId}")
	public Set<Video> GetAllVidsOfCat(@PathVariable("catId") int catId){
		Category catObj=catservice.fetchCategorybyId(catId);
		return catObj.getVid();
	}
}
