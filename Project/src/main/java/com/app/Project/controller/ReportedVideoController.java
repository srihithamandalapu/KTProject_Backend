package com.app.Project.controller;

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

import com.app.Project.model.Comment;
import com.app.Project.model.ReportedComments;
import com.app.Project.model.ReportedVideos;
import com.app.Project.model.User;
import com.app.Project.model.Video;
import com.app.Project.service.RegistrationService;
import com.app.Project.service.ReportedVideoService;
import com.app.Project.service.VideoService;

@RestController
public class ReportedVideoController {
	@Autowired
	private ReportedVideoService repvidservice;
	
	@Autowired
	RegistrationService rservice;
	
	@Autowired
	VideoService vservice;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/reportcomment")
	public ReportedVideos uploadReportedVideo(@RequestBody ReportedVideos repvid) {
		ReportedVideos repvidObj=null;
		repvidObj=repvidservice.saveReportedVideos(repvid);
		return repvidObj;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="/getReportedVideos", produces="application/json")
	public String getReportedComments() {
		
		List<ReportedVideos> allReportedVid=repvidservice.getReportedVideos();
		
		JSONArray ja = new JSONArray();
		for(ReportedVideos reportedVid: allReportedVid) {
			List<User> uObj=(List<User>) rservice.getUserByRepVid(reportedVid);
			List<Video> videoObj=(List<Video>)vservice.getVideoByRepVid(reportedVid);
			for(User u: uObj) {
				System.out.println(u.getFirstName()+" "+videoObj.get(0).getVideoTitle());
				JSONObject obj = new JSONObject();
				obj.put("firstName",u.getFirstName());
				obj.put("lastName",u.getLastName());
				obj.put("videoId", videoObj.get(0).getVideoId());
				obj.put("videoTitle", videoObj.get(0).getVideoTitle());
				obj.put("videoLink",videoObj.get(0).getVideoLink());
				ja.put(obj);			
			}
		}
		return ja.toString();
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/reportVideo/{videoId}/{userId}")
	public void postRequest(@PathVariable("videoId") int videoId, @PathVariable("userId") int userId) {
		ReportedVideos repvidObj=new ReportedVideos();
		User uObj=rservice.fetchUserById(userId);
		Video vObj=vservice.getVideo(videoId);
		Set<ReportedVideos> setOfReportedVideoByUser=uObj.getReportedVid();
		Set<ReportedVideos> setOfReportedVideo=vObj.getReportedVid();
		int isReported;
		setOfReportedVideoByUser.removeAll(setOfReportedVideo);
		isReported=setOfReportedVideoByUser.size();		
		repvidservice.saveReportedVideos(repvidObj);
		rservice.updateReportedVideo(userId, repvidObj);
		vservice.updateReportedVideo(videoId,repvidObj);
	}

}
