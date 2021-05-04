package com.app.Project.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Project.DTO.CommentById;
import com.app.Project.model.Category;
import com.app.Project.model.Likes;
import com.app.Project.model.ReportedComments;
import com.app.Project.model.User;
import com.app.Project.model.Comment;
import com.app.Project.model.Video;
import com.app.Project.service.CategoryService;
import com.app.Project.service.CommentService;
import com.app.Project.service.RegistrationService;
import com.app.Project.service.ReportedCommentService;
import com.app.Project.service.VideoService;

@RestController
public class ReportedCommentController {
	

	@Autowired
	private VideoService vservice;
	
	@Autowired
	CommentService cservice;
	
	@Autowired
	RegistrationService rservice;
	
	
	@Autowired
	ReportedCommentService repcomservice;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/reportvideo")
	public ReportedComments uploadReportedComment(@RequestBody ReportedComments repcom) {
		
		
		ReportedComments repcomObj=null;
		repcomObj=repcomservice.saveReportedComment(repcom);
		return repcomObj;
	}
	


	@SuppressWarnings("null")
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="/getReportedComments", produces="application/json")
	public String getReportedComments() {
		
		List<ReportedComments> allReportedCom=repcomservice.getReportedComments();
		
		JSONArray ja = new JSONArray();
		for(ReportedComments reportedCom: allReportedCom) {
			List<User> uObj=(List<User>) rservice.getUserByRepCom(reportedCom);
			Comment comObj=cservice.getCommentByRepComId(reportedCom);
			Video videoObj=vservice.getVideoByComment(comObj);
			for(User u: uObj) {
				JSONObject obj = new JSONObject();
				obj.put("firstName",u.getFirstName());
				obj.put("lastName",u.getLastName());
				obj.put("commentDesc", comObj.getCommentDesc());
				obj.put("commentId", comObj.getCommentId());
				obj.put("videoId", videoObj.getVideoId());
				obj.put("videoTitle", videoObj.getVideoTitle());
				obj.put("videoLink", videoObj.getVideoLink());
				ja.put(obj);
			}
		}
		return ja.toString();
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="/getReportedComments/{id}", produces="application/json")
	public CommentById getCommentOnVideo(@PathVariable("id") int vId) {
		List<ReportedComments> allReportedCom=repcomservice.getReportedComments();
		JSONArray ja = new JSONArray();
		for(ReportedComments reportedCom: allReportedCom) {
			List<User> uObj=(List<User>) rservice.getUserByRepCom(reportedCom);
			Comment comObj=cservice.getCommentByRepComId(reportedCom);
			Video videoObj=vservice.getVideoByComment(comObj);
			for(User u: uObj) {
				JSONObject obj = new JSONObject();
				obj.put("firstName",u.getFirstName());
				obj.put("lastName",u.getLastName());
				obj.put("commentDesc", comObj.getCommentDesc());
				obj.put("commentId", comObj.getCommentId());
				obj.put("videoId", videoObj.getVideoId());
				obj.put("videoTitle", videoObj.getVideoTitle());
				obj.put("videoLink", videoObj.getVideoLink());
				ja.put(obj);
			}
		}
		
		List<String> tempSL = new ArrayList<String>();
		List<Integer> tempIL= new ArrayList<Integer>();
		List<String> tempSLUser = new ArrayList<String>();
		
		for(int i = 0; i < ja.length() ; i++) {
			JSONObject obj = ja.getJSONObject(i);
			if(obj.getInt("videoId")==vId) {
				tempSL.add(obj.getString("commentDesc"));
				tempIL.add(obj.getInt("commentId"));
				tempSLUser.add(obj.getString("firstName")+" "+obj.getString("lastName"));
			}
		}
		
		Set<String> set = new HashSet<>(tempSL);
		tempSL.clear();
		tempSL.addAll(set);
		
		Set<Integer> set2 = new HashSet<>(tempIL);
		tempIL.clear();
		tempIL.addAll(set2);
		
		CommentById res= new CommentById();
		res.setcDesc(tempSL);
		res.setcId(tempIL);
		res.setuName(tempSLUser);
		res.setVideoId(vId);
		return res;
		
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/reportComment/{commentId}/{userId}")
	public void postRequest(@PathVariable("commentId") int commentId, @PathVariable("userId") int userId) {
		ReportedComments repcomObj=new ReportedComments();
		User uObj=rservice.fetchUserById(userId);
		Comment comObj=cservice.fetchCommentbyId(commentId);
		Set<ReportedComments> setOfReportedCommentByUser=uObj.getReportedCom();
		Set<ReportedComments> setOfReportedComments=comObj.getReportedCom();
		setOfReportedCommentByUser.retainAll(setOfReportedComments);
		int isReported=setOfReportedCommentByUser.size();
		if(isReported==0) {
			repcomservice.saveReportedComment(repcomObj);
			rservice.updateReportedComment(userId, repcomObj);
			cservice.updateReportedComment(commentId,repcomObj);
		}
	
	}
	

	
	
	
}
