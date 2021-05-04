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

import com.app.Project.controller.CategoryController;
import com.app.Project.controller.VideoController;
import com.app.Project.model.*;
import com.app.Project.repository.CategoryRepository;
import com.app.Project.service.CategoryService;
import com.app.Project.service.CommentService;
import com.app.Project.service.RegistrationService;
import com.app.Project.service.ReportedCommentService;
import com.app.Project.service.ReportedVideoService;
import com.app.Project.service.VideoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CategoryControllerTest {
		
		@Autowired
		private CategoryRepository crepo;
		
		@Autowired
		private CategoryController catcontroller;
		
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
		
		@Autowired
		private CategoryService catService;
		
		@Test
		public void getCategoryTest() {
			List<Category> sc1= crepo.findAll();
			List<Category> sc2= catService.getCategory();
			assertEquals(sc2,sc1);
		}
		
		@Test
		public void GetAllVidsOfCatTest() {
			Video vid1=new Video(14,"This is a video1","abc.com","Yes","Video 1",Collections.<Comment>emptySet(),Collections.<Likes>emptySet(),Collections.<Category>emptySet(),Collections.<ReportedVideos>emptySet());
			Video vid=vservice.saveVideo(vid1);
			Set<Video> svid=new HashSet<>();
			svid.add(vid);
			Category c_1=new Category(1,"Junit", svid,Collections.<User>emptySet());
			Category c1=catService.saveCategory(c_1);
			Set<Video> vid2= catcontroller.GetAllVidsOfCat(c1.getCategoryId());
			assertEquals(svid,vid2);
			 
		}
		

}
