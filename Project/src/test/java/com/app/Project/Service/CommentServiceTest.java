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
import com.app.Project.repository.CommentsRepository;
import com.app.Project.service.CommentService;
import com.app.Project.service.RegistrationService;
import com.app.Project.service.ReportedCommentService;
import com.app.Project.service.ReportedVideoService;
import com.app.Project.service.VideoService;

	@RunWith(SpringRunner.class)
	@SpringBootTest
	@Transactional
	class CommentServiceTest {
		
		@Autowired
		private CommentsRepository comRepo;
		
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
		void testsaveComment() {
			Comment c=new Comment(1,"This is JUnit",Collections.<ReportedComments>emptySet());
			Comment c1=cservice.saveComment(c);
			assertEquals(c.toString(), c1.toString());
		}
		
		@Test
		void testfetchCommentbyId() {
			Comment c=new Comment(1,"This is JUnit",Collections.<ReportedComments>emptySet());
			Comment c1=cservice.saveComment(c);
			Comment c2=cservice.fetchCommentbyId(c1.getCommentId());
			assertEquals(c1.toString(), c2.toString());
		}
		
		@Test
		void testfindCommentByRep() {
			ReportedComments rcObj=new ReportedComments();
			ReportedComments rcObj1=rcservice.saveReportedComment(rcObj);
			Set<ReportedComments> setComm= new HashSet<ReportedComments>();
			setComm.add(rcObj1);
			Comment c=new Comment(1,"This is JUnit",setComm);
			Comment c1=cservice.saveComment(c);
			Comment c3=cservice.findCommentByRep(rcObj1);
			assertEquals(c1.toString(), c3.toString());
		}
		
		@Test
		void testfetchAllComment() {
			List<Comment> allComm= new ArrayList<Comment>();
			allComm=comRepo.findAll();
			List<Comment> fetchedComm=cservice.fetchAllComment();
			assertEquals(allComm, fetchedComm);
		}
		
		@Test
		void testgetCommentByRepComId() {
			ReportedComments rcObj=new ReportedComments();
			ReportedComments rcObj1=rcservice.saveReportedComment(rcObj);
			Set<ReportedComments> setComm= new HashSet<ReportedComments>();
			setComm.add(rcObj1);
			Comment c=new Comment(1,"This is JUnit",setComm);
			Comment c1=cservice.saveComment(c);
			Comment c3=cservice.getCommentByRepComId(rcObj1);
			assertEquals(c1.toString(), c3.toString());
		}
		
	}
		