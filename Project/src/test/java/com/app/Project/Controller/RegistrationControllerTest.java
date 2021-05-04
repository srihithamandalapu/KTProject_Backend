package com.app.Project.Controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.Project.DTO.SubscriptionDetails;
import com.app.Project.controller.RegistrationController;
import com.app.Project.model.*;
import com.app.Project.repository.RegistrationRepository;
import com.app.Project.service.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class RegistrationControllerTest {
	
	@Autowired
	private RegistrationRepository repo;
	
	@Autowired
	private RegistrationController rcontroller;
	
	@Autowired
	private RegistrationService rservice;
	
	@Autowired
	private CategoryService catservice;
	
	@Autowired
	private VideoService vservice;
	

	@Test
	void registerUserTest() {
		User u1=new User(1,"rash234i@gmail.com","Rashi","Chhajer","rasewrwqhi123","No", Collections.<Video>emptySet(),Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(),Collections.<ReportedComments>emptySet(),Collections.<Category>emptySet());
		
		User u=new User();
		
		try {
			u = rcontroller.registerUser(u1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(u.toString());
			e.printStackTrace();
			
		}
		assertEquals(u1.toString(), u.toString());
	}
	
	@Test
	void loginUserTest() {
		User u1=new User(0,"anjana@gmail.com","Anjana","Shah","anjana123","No", Collections.<Video>emptySet(),Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(),Collections.<ReportedComments>emptySet(),Collections.<Category>emptySet());
		rservice.saveUser(u1);
		User u=new User();
		
		try {
			u = rcontroller.loginUser(u1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(u.toString());
			e.printStackTrace();
			
		}
		assertEquals(u1.toString(), u.toString());
		
	}
	/*
	@Test
	public void getUserTest() {
		List<User> l1= rcontroller.getUser();
		List<User> l2=repo.findAll();
		assertEquals(l1.toString(), l2.toString());
	}
	*/
	
	@Test
	public void getSubsCatTest() {
		Category cat=new Category("Cat 1");
		Category cat1=catservice.saveCategory(cat);
		Set<Category> scat=new HashSet<>();
		scat.add(cat1);
		User u1=new User(1,"rashi6@gmail.com","Rashi","Chhajer","rashi123","No", Collections.<Video>emptySet(),Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(),Collections.<ReportedComments>emptySet(),scat);
		User u=rservice.saveUser(u1);
		
		Set<Category> cat2=rcontroller.getSubsCat(u.getId());
		assertEquals(cat2.toString(), scat.toString());
	}
	
	@Test
	public void  getSubsDetailsTest() {
		Category cat=new Category("Test Category 1");
		Category cat1=catservice.saveCategory(cat);
		Set<Category> scat=new HashSet<>();
		scat.add(cat1);
		User u1=new User(1,"rashi7@gmail.com","Rashi","Chhajer","rashi1234","No", Collections.<Video>emptySet(),Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(),Collections.<ReportedComments>emptySet(),scat);
		User u=rservice.saveUser(u1);
		List<SubscriptionDetails> setOfResults= new ArrayList<SubscriptionDetails>();
		Set<Category> subscribed = u.getCat();
		List<Category> subscribedList= new ArrayList<Category>();
		subscribedList.addAll(subscribed);
		List<Category> allCats= catservice.getCategory();
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
		List<SubscriptionDetails> sub1=rcontroller.getSubsDetails(u.getId());
		assertEquals(sub1.toString(), setOfResults.toString());		
	}
	
	@Test
	public void getAllVidsTest() {
		Video vid=new Video();
		Video vid1=vservice.saveVideo(vid);
		Set<Video> svid=new HashSet<>();
		svid.add(vid1);
		User u1=new User(1,"rashi7@gmail.com","Rashi","Chhajer","rashi1234","No", svid,Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(),Collections.<ReportedComments>emptySet(),Collections.<Category>emptySet());
		User u=rservice.saveUser(u1);

		Set<Video> vid2=rcontroller.getAllVids(u.getId());
		assertEquals(vid2.toString(), svid.toString());
		
	}
	
}