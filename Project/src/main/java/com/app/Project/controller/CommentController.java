package com.app.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Project.model.Comment;
import com.app.Project.model.Video;
import com.app.Project.service.CommentService;
import com.app.Project.service.RegistrationService;
import com.app.Project.service.VideoService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService cservice;
	
	@Autowired
	private VideoService vservice;
	
	@Autowired
	private RegistrationService rservice;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/comment/{videoId}/{userId}")
	public Comment postComment(@RequestBody Comment comment, @PathVariable("videoId") int videoId, @PathVariable("userId") int userId ) {
		Comment cObj=null;
		cObj=cservice.saveComment(comment);
		vservice.updateCommentOnVideo(videoId, cObj);
		rservice.updateComment(userId, cObj);
		return cObj;
	}

}
