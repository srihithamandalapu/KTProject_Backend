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
import com.app.Project.repository.VideoRepository;
import com.app.Project.service.CommentService;
import com.app.Project.service.RegistrationService;
import com.app.Project.service.ReportedCommentService;
import com.app.Project.service.ReportedVideoService;
import com.app.Project.service.VideoService;

	@RunWith(SpringRunner.class)
	@SpringBootTest
	@Transactional
	class VideoServiceTest {
		
		@Autowired
		private VideoRepository vrepo;
		
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
		void testsaveVideo() {
			Video vObj=new Video(1,"This is Junit Video Desc","www.junitvid.com","Yes","Junit Video Title",Collections.<Comment>emptySet(), Collections.<Likes>emptySet(), Collections.<Category>emptySet(), Collections.<ReportedVideos>emptySet());
			Video vObj2=vservice.saveVideo(vObj);
			assertEquals(vObj.toString(), vObj2.toString());
		}
		
		@Test
		void testgetAllVideos() {
			List<Video> listAll=vrepo.findAll();
			List<Video> listRes=vservice.getAllVideos();
			assertEquals(listAll, listRes);
		}
		
		@Test
		void testgetApprovedVideos() {
			Video vObj=new Video(1,"This is Junit Video Desc","www.junitvid.com","Yes","Junit Video Title",Collections.<Comment>emptySet(), Collections.<Likes>emptySet(), Collections.<Category>emptySet(), Collections.<ReportedVideos>emptySet());
			Video vObj2=vservice.saveVideo(vObj);
			List<Video> isApLi=new ArrayList<Video>();
			isApLi= vrepo.findByIsApproved("yes");
			List<Video> listRes=vservice.getApprovedVideos();
			assertEquals(isApLi, listRes);
		}
		
		@Test
		void testgetUnapprovedVideos() {
			Video vObj=new Video(1,"This is Junit Video Desc","www.junitvid.com","Yes","Junit Video Title",Collections.<Comment>emptySet(), Collections.<Likes>emptySet(), Collections.<Category>emptySet(), Collections.<ReportedVideos>emptySet());
			Video vObj2=vservice.saveVideo(vObj);
			List<Video> isApLi=new ArrayList<Video>();
			isApLi= vrepo.findByIsApproved("no");
			List<Video> listRes=vservice.getUnapprovedVideos();
			assertEquals(isApLi, listRes);
		}

		@Test
		void testgetVideo() {
			Video vObj=new Video(1,"This is Junit Video Desc","www.junitvid.com","Yes","Junit Video Title",Collections.<Comment>emptySet(), Collections.<Likes>emptySet(), Collections.<Category>emptySet(), Collections.<ReportedVideos>emptySet());
			Video vObj2=vservice.saveVideo(vObj);
			Video vObj3=vservice.getVideo(vObj2.getVideoId());
			assertEquals(vObj2.toString(), vObj3.toString());
		}
		
		@Test
		void testgetVideoByComment() {
			Comment com1=new Comment();
			com1.setCommentDesc("Junit Comment");
			Comment com= cservice.saveComment(com1);
			Set<Comment> setOfComments = new HashSet<Comment>();
			setOfComments.add(com);
			Video vObj=new Video(1,"This is Junit Video Desc","www.junitvid.com","Yes","Junit Video Title",setOfComments, Collections.<Likes>emptySet(), Collections.<Category>emptySet(), Collections.<ReportedVideos>emptySet());
			Video vObj2=vservice.saveVideo(vObj);
			Video vObj3=vservice.getVideoByComment(com);
			assertEquals(vObj2.toString(), vObj3.toString());
		}
		
		@Test
		void testgetVideoByRepVid() {
			ReportedVideos repVid=new ReportedVideos();
			ReportedVideos rvObj=rvservice.saveReportedVideos(repVid);
			
			Set<ReportedVideos> setRep=new HashSet<ReportedVideos>();
			setRep.add(rvObj);
			List<Video> userList=new ArrayList<Video>();
			Video vObj=new Video(1,"This is Junit Video Desc","www.junitvid.com","Yes","Junit Video Title",Collections.<Comment>emptySet(), Collections.<Likes>emptySet(), Collections.<Category>emptySet(), setRep);
			Video vObj2=vservice.saveVideo(vObj);
			userList.add(vObj2);
			
			List<Video> userListFin=vservice.getVideoByRepVid(rvObj);
			
			assertEquals(userList, userListFin);
		}
		
		
	}