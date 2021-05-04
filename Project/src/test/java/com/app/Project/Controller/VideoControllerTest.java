package com.app.Project.Controller;
import static org.junit.Assert.*;

import java.util.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.Project.DTO.SubscriptionDetails;
import com.app.Project.controller.*;
import com.app.Project.model.*;
import com.app.Project.repository.RegistrationRepository;
import com.app.Project.repository.VideoRepository;
import com.app.Project.service.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class VideoControllerTest {
	
	@Autowired
	private RegistrationRepository repo;
	
	@Autowired
	private VideoRepository vrepo;
	
	@Autowired
	private RegistrationController rcontroller;
	
	@Autowired
	private VideoController vcontroller;
	
	@Autowired
	private RegistrationService rservice;
	
	@Autowired
	private CategoryService catservice;
	
	@Autowired
	private VideoService vservice;
	
	/*
	@Test
	public void postRequestTest() {
		Video v_test=new Video(13,"This is a video1","abc.com","Yes","Video 1",Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<Category>emptySet(),Collections.<ReportedVideos>emptySet());
		Video v_test1=vservice.saveVideo(v_test);
		Category defCatObj=new Category("DEFAULT");
		Category cat=new Category("Test Category 1");
		Category cat1=catservice.saveCategory(defCatObj);
		Category cat2=catservice.saveCategory(cat);
		Set<Category> scat=new HashSet<>();
		scat.add(cat1);
		scat.add(cat2);
		Video vid1=new Video(14,"This is a video1","abc.com","Yes","Video 1",Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),scat,Collections.<ReportedVideos>emptySet());
		Video vid=vservice.saveVideo(vid1);
		Set<Video> svid=new HashSet<>();
		svid.add(vid);
		User u1=new User(111,"rashi8@gmail.com","Rashi","Chhajer","rashi12345","No",Collections.<Video>emptySet() ,Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(),Collections.<ReportedComments>emptySet(),Collections.<Category>emptySet());
		User u=rservice.saveUser(u1);
		//Set<User> suser=new HashSet<>();
		//suser.add(u);
		//Set<Video> allVideosOfThisCat=cat2.getVid();
		//allVideosOfThisCat.add(vid);
		
		//Category cat3=new Category(1,"Test Category 1",svid ,suser);
		Video v2=vcontroller.postRequest(v_test1,u.getId(),cat2.getCategoryName());
		assertEquals(v2.toString(), vid.toString());	
		
	}
	*/
	
	@Test
	public void getAllVideosTest() {
		Video vid1=new Video(11,"This is a video1","abc.com","Yes","Video 1",Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<Category>emptySet(),Collections.<ReportedVideos>emptySet());
		Video v1=vservice.saveVideo(vid1);
		List<Video> lvid=new ArrayList<>();
		lvid= vrepo.findByIsApproved("yes");
		List<Video> v2=vcontroller.getAllVideos();
		assertEquals(v2, lvid);
	}
	
	@Test
	public void getUnapprovedVideosTest() {
		Video vid1=new Video(11,"This is a video1","abc.com","No","Video 1",Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<Category>emptySet(),Collections.<ReportedVideos>emptySet());
		Video v1=vservice.saveVideo(vid1);
		List<Video> lvid=new ArrayList<>();
		lvid= vrepo.findByIsApproved("No");
		List<Video> v2=vcontroller.getUnapprovedVideos();
		assertEquals(v2, lvid);
		
	}
	
	
	
}
	
