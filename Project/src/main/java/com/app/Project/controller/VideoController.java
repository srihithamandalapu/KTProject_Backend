package com.app.Project.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Project.DTO.Commentor;
import com.app.Project.model.Category;
import com.app.Project.model.Comment;
import com.app.Project.model.User;
import com.app.Project.model.Video;
import com.app.Project.service.CategoryService;
import com.app.Project.service.RegistrationService;
import com.app.Project.service.VideoService;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
public class VideoController {
	
	 @Autowired
	 private JavaMailSender emailSender;
	
	
	@Autowired
	private RegistrationService service;
	
	@Autowired
	private CategoryService cservice;
	
	
	@Autowired
	private VideoService vservice;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/upload/{id}/{catName}")
	public Video postRequest(@RequestBody Video video, @PathVariable("id") int userId, @PathVariable("catName") String catName) {
		Category defCatObj=new Category();
		defCatObj.setCategoryName("DEFAULT");
		Video videoObj=null;
		videoObj=vservice.saveVideo(video);
		Category catObj= cservice.findCategoryByName(catName);
		if(catObj==null) {
			catObj=new Category();
			catObj.setCategoryName(catName);
		}
		Set<Category> catOfThisVideo = videoObj.getCat();
		catOfThisVideo.add(catObj);
		catOfThisVideo.add(defCatObj);
		videoObj.setCat(catOfThisVideo);
		vservice.saveVideo(videoObj);
		Set<Video> allVideosOfThisCat=catObj.getVid();
		allVideosOfThisCat.add(videoObj);
		catObj.setVid(allVideosOfThisCat);
		Set<User> allUsersOfThisCat=catObj.getUser();
		allUsersOfThisCat.add(service.fetchUserById(userId));
		catObj.setUser(allUsersOfThisCat);
		cservice.saveCategory(catObj);
		service.updateUser(userId, videoObj);
		return videoObj;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/upload/{id}")
	public Video postRequestWithoutCat(@RequestBody Video video, @PathVariable("id") int userId) {
		Category defCatObj=new Category();
		defCatObj.setCategoryName("DEFAULT");
		
		Video videoObj=null;
		Set<Category>  setOfCatGivenObj=video.getCat();
		
		for (Category temp : setOfCatGivenObj) {
	        String catName=temp.getCategoryName();
	        if(cservice.findCategoryByName(catName)==null && !catName.equals("DEFAULT")){
	        	videoObj=vservice.saveVideo(video);
	        	}
	        else {
	        	Category catInDB=cservice.findCategoryByName(catName);
	        	int catIdInDB=catInDB.getCategoryId();
	        	setOfCatGivenObj.remove(temp);
	        	setOfCatGivenObj.add(catInDB);
	        	video.setCat(setOfCatGivenObj);
	        	videoObj=vservice.saveVideo(video);	
	        }
	     }
		Set<Category> setOfCatObj=video.getCat();
		for(Category catObj : setOfCatObj) {
			Set<Category> catOfThisVideo = videoObj.getCat();
			catOfThisVideo.add(defCatObj);
			videoObj.setCat(catOfThisVideo);
			vservice.saveVideo(videoObj);
			Set<Video> allVideosOfThisCat=catObj.getVid();
			allVideosOfThisCat.add(videoObj);
			catObj.setVid(allVideosOfThisCat);
			Set<User> allUsersOfThisCat=catObj.getUser();
			allUsersOfThisCat.add(service.fetchUserById(userId));
			catObj.setUser(allUsersOfThisCat);
			cservice.saveCategory(catObj);
			service.updateUser(userId, videoObj);
		}

		User uTemp=service.fetchUserById(userId);
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rashichhajer1@gmail.com");
        message.setTo(uTemp.getEmailId()); 
        message.setSubject("Thank you for adding a video!"); 
        message.setText("Your video has been uploaded sucessfully!");
        emailSender.send(message);
        
		return videoObj;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="/getAllVids", produces="application/json")
	public List<Video> getAllVideos(){
		List<Video> approvedVids=vservice.getApprovedVideos();
		return approvedVids;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="/play/{id}",produces = "application/json")
	public Commentor read(@PathVariable("id") int videoId) {
		Video vObj= vservice.getVideo(videoId);
		Set<Comment> setOfComments= vObj.getComment();
		if(setOfComments.size()==0) {
			Commentor cmntrObj=new Commentor();
			cmntrObj.setCat(vObj.getCat());
			cmntrObj.setVideoId(vObj.getVideoId());
			cmntrObj.setVideoDesc(vObj.getVideoDesc());
			cmntrObj.setVideoLink(vObj.getVideoLink());
			cmntrObj.setIsApproved(vObj.getIsApproved());
			cmntrObj.setVideoTitle(vObj.getVideoTitle());
			cmntrObj.setLike(vObj.getLike());
			cmntrObj.setReportedVid(vObj.getReportedVid());
			return cmntrObj;
		}
		
		//If there are comments
		
		Iterator value = setOfComments.iterator(); 
		Commentor cmntrObjFin=null;
		Commentor cmntrObj=new Commentor();
		while(value.hasNext()) {
			Comment cObj=(Comment) value.next();
			User uObj=service.getUserByCom(cObj);
			
			
			String userNameOnComment= uObj.getFirstName()+" "+uObj.getLastName();
			cmntrObj.setCat(vObj.getCat());
			cmntrObj.setVideoId(vObj.getVideoId());
			cmntrObj.setVideoDesc(vObj.getVideoDesc());
			cmntrObj.setVideoLink(vObj.getVideoLink());
			cmntrObj.setIsApproved(vObj.getIsApproved());
			cmntrObj.setVideoTitle(vObj.getVideoTitle());
			cmntrObj.setLike(vObj.getLike());
			cmntrObj.setReportedVid(vObj.getReportedVid());
			
			List<Integer> lCid=cmntrObj.getcID();
			if(lCid==null) {
				lCid = new ArrayList<Integer>();
			}
			lCid.add(cObj.getCommentId());
			cmntrObj.setcID(lCid);
			
			List<String> lComments=cmntrObj.getCommentMade();
			if(lComments==null) {
				lComments=new ArrayList<String>();
			}
			lComments.add(cObj.getCommentDesc());
			cmntrObj.setCommentMade(lComments);
			
			List<String> lUsers= cmntrObj.getName();
			if(lUsers==null) {
				lUsers=new ArrayList<String>();
			}
			lUsers.add(userNameOnComment);
			cmntrObj.setName(lUsers);
			cmntrObj.setLike(vObj.getLike());
			cmntrObj.setReportedVid(vObj.getReportedVid());
			cmntrObjFin=cmntrObj;
		}
		return cmntrObjFin;
	}

	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="/UnapprovedVideos",produces = "application/json")
	public List<Video> getUnapprovedVideos() {
		List<Video> unapprovedVids=vservice.getUnapprovedVideos();
		return unapprovedVids;	
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value="/approveVid/{id}", produces="application/json")
	public void approveVideo(@PathVariable("id") int videoId) {
		Video vObj=vservice.getVideo(videoId);
		vObj.setIsApproved("Yes");
		vservice.saveVideo(vObj);
	}

	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value="/updateVid/{id}", produces = "application/json")
	public void updateVideo(@RequestBody Map<String, String> payload, @PathVariable("id") int videoId) {
		Video vObj= vservice.getVideo(videoId);
		vObj.setVideoDesc(payload.get("videoDesc"));
		vObj.setVideoTitle(payload.get("videoTitle"));
		vservice.saveVideo(vObj);	
	}	
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping(value="/deleteVid/{id}", produces = "application/json")
	public void deleteVideo(@PathVariable("id") int videoId) {
		Video vObj=vservice.getVideo(videoId);
		String vtitle=vObj.getVideoTitle();
		User uObj=service.getUserByVideo(vObj);
		vservice.deleteVideoById(videoId);
		User uTemp=service.fetchUserById(uObj.getId());
		//send an email saying your video was successfully uploaded.
		//SimpleMailMessage message = new SimpleMailMessage(); 
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rashichhajer1@gmail.com");
        message.setTo(uTemp.getEmailId()); 
        message.setSubject("Notice! Video Status"); 
        message.setText("Your Video ("+vtitle+") has been deleted after careful review.");
        emailSender.send(message);

	}
}
