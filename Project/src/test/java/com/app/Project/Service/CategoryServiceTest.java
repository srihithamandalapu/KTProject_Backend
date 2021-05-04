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
	class CategoryServiceTest {
		
		@Autowired
		private CategoryRepository catRepo;
		
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
		void testsaveCategory() {
			Category cObj=new Category(1,"Junit", Collections.<Video>emptySet(),Collections.<User>emptySet());
			Category cObj2=catService.saveCategory(cObj);
			assertEquals(cObj.toString(), cObj2.toString());
		}
		
		@Test
		void testgetCategory() {
			List<Category> li= catRepo.findAll();
			List<Category> li2= catService.getCategory();
			assertEquals(li,li2);
		}
		
		@Test
		void testfetchCategorybyId() {
			Category cObj=new Category(1,"Junit", Collections.<Video>emptySet(),Collections.<User>emptySet());
			Category cObj2=catService.saveCategory(cObj);
			Category cObj3=catService.fetchCategorybyId(cObj2.getCategoryId());
			assertEquals(cObj2, cObj3);
		}
		
		@Test
		void testfindCategoryByName() {
			Category cObj=new Category(1,"Junit", Collections.<Video>emptySet(),Collections.<User>emptySet());
			Category cObj2=catService.saveCategory(cObj);
			Category cObj3=catService.findByName(cObj2.getCategoryName());
			assertEquals(cObj2, cObj3);
		}
		
		@Test
		void testfindByName() {
			Category cObj=new Category(1,"Junit", Collections.<Video>emptySet(),Collections.<User>emptySet());
			Category cObj2=catService.saveCategory(cObj);
			Category cObj3=catService.findByName(cObj2.getCategoryName());
			assertEquals(cObj2, cObj3);
		}
		
	}