package com.app.Project.Service;
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

import com.app.Project.model.*;
import com.app.Project.service.CommentService;
import com.app.Project.service.RegistrationService;
import com.app.Project.service.ReportedCommentService;
import com.app.Project.service.ReportedVideoService;
import com.app.Project.service.VideoService;

	@RunWith(SpringRunner.class)
	@SpringBootTest
	@Transactional
	class RegistrationServiceTest {
		
	//	@Autowired
	//	private RegistrationRepository repo;
		
		@Autowired
		private RegistrationService rservice;
		
		@Autowired
		private VideoService vservice;
		
		@Autowired
		private CommentService cservice;
		
		@Autowired
		private ReportedCommentService rcservice;
		
		@Autowired
		private ReportedVideoService rvservice;
		
	
		@Test
		void testLoginUser() {
			User u1=new User(1,"rashi@gmail.com","Rashi","Chhajer","rashi123","No", Collections.<Video>emptySet(),Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(),Collections.<ReportedComments>emptySet(),Collections.<Category>emptySet());
			User u = rservice.saveUser(u1);
			assertEquals(u1.toString(), u.toString());
		}
		
		@Test
		void testfetchUserByEmailId() {
			User u1=new User(1,"rashi2@gmail.com","Rashi","Chhajer","rashi123","No", Collections.<Video>emptySet(),Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(),Collections.<ReportedComments>emptySet(),Collections.<Category>emptySet());
			User u = rservice.saveUser(u1);
			//User u=new User();
			u=rservice.fetchUserByEmailId("rashi2@gmail.com");
			assertEquals(u1.toString(), u.toString());
		}
		
		@Test
		void testfetchUserByEmailIdAndPassword() {
			User u1=new User(1,"rashi3@gmail.com","Rashi","Chhajer","rashi123","No", Collections.<Video>emptySet(),Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(),Collections.<ReportedComments>emptySet(),Collections.<Category>emptySet());
			User u = rservice.saveUser(u1);
			//User u=new User();
			u=rservice.fetchUserByEmailIdAndPassword("rashi3@gmail.com", "rashi123");
			assertEquals(u1.toString(), u.toString());
		}
		
		@Test
		void testfetchUserById() {
			User u1=new User(10001,"rashi4@gmail.com","Rashi","Chhajer","rashi123","No", Collections.<Video>emptySet(),Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(),Collections.<ReportedComments>emptySet(),Collections.<Category>emptySet());
			User u = rservice.saveUser(u1);
			//User u=new User();
			User u2=rservice.fetchUserById(u.getId());
			assertEquals(u1.toString(), u2.toString());
		}
		
//		@Test
//		void testgetUser() {
//			User u1=new User(1,"rashi@gmail.com","Rashi","Chhajer","rashi123","No", Collections.<Video>emptySet(),Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(),Collections.<ReportedComments>emptySet(),Collections.<Category>emptySet());
//			User u = rservice.saveUser(u1);
//			List<User> li1= new ArrayList<User>();
//			li1.add(u1);
//			List<User> li= new ArrayList<User>();
//			li=rservice.getUser();
//			
//		}
		
		@Test
		void testgetUserByCom() {
			Comment cObj=new Comment();
			cObj.setCommentDesc("ABC");
			cObj.setCommentId(1);
			Comment cObj2=cservice.saveComment(cObj);
			Set<Comment> setComm= new HashSet<Comment>(); 
			setComm.add(cObj2);
			User u1=new User(1,"rashi5@gmail.com","Rashi","Chhajer","rashi123","No", Collections.<Video>emptySet(),setComm,Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(),Collections.<ReportedComments>emptySet(),Collections.<Category>emptySet());
			User u=rservice.saveUser(u1);
			User u2=rservice.getUserByCom(cObj2);
			assertEquals(u1.toString(), u2.toString());
		}
		
		@Test
		void testgetUserByRepCom() {
			ReportedComments rcObj=new ReportedComments();
			ReportedComments rcObj2=rcservice.saveReportedComment(rcObj);
			Set<ReportedComments> setComm= new HashSet<ReportedComments>(); 
			setComm.add(rcObj2);
			User u1=new User(1,"rashi6@gmail.com","Rashi","Chhajer","rashi123","No", Collections.<Video>emptySet(),Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(),setComm,Collections.<Category>emptySet());
			User u=rservice.saveUser(u1);
			List<User> u1L=new ArrayList<User>();
			u1L.add(u);
			List<User> u2=rservice.getUserByRepCom(rcObj2);
			assertEquals(u1L, u2);
		}
		
		@Test
		void testgetUserByRepVid() {
			ReportedVideos rvObj=new ReportedVideos();
			ReportedVideos rvObj2= rvservice.saveReportedVideos(rvObj);
			Set<ReportedVideos> setComm= new HashSet<ReportedVideos>(); 
			setComm.add(rvObj2);
			User u1=new User(1,"rashi6@gmail.com","Rashi","Chhajer","rashi123","No", Collections.<Video>emptySet(),Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),setComm, Collections.<ReportedComments>emptySet(),Collections.<Category>emptySet());
			User u=rservice.saveUser(u1);
			List<User> u1L=new ArrayList<User>();
			u1L.add(u);
			List<User> u2=rservice.getUserByRepVid(rvObj2);
			assertEquals(u1L, u2);
		}
	//Video(int videoId, String videoDesc, String videoLink, String isApproved, String videoTitle,
	//	Set<Comment> comment, Set<Likes> like, Set<Category> cat,Set<ReportedVideos> reportedVid)	
		@Test
		void testgetUserByVideo() {
			Video vObj=new Video(101,"Junit video","junit.com","yes","JUNIT",Collections.<Comment>emptySet(), Collections.<Likes>emptySet(), Collections.<Category>emptySet(), Collections.<ReportedVideos>emptySet());
			Video vObj2= vservice.saveVideo(vObj);
			Set<Video> setComm= new HashSet<Video>(); 
			setComm.add(vObj2);
			User u1=new User(1,"rashi6@gmail.com","Rashi","Chhajer","rashi123","No", setComm,Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<ReportedVideos>emptySet(), Collections.<ReportedComments>emptySet(),Collections.<Category>emptySet());
			User u=rservice.saveUser(u1);
			User u2=rservice.getUserByVideo(vObj2);
			assertEquals(u, u2);
			
		}
	
	}
