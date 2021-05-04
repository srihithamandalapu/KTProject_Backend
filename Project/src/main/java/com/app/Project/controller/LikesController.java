package com.app.Project.controller;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.Project.model.Likes;
import com.app.Project.model.User;
import com.app.Project.model.Video;
import com.app.Project.service.LikesService;
import com.app.Project.service.RegistrationService;
import com.app.Project.service.VideoService;

@RestController
public class LikesController {
	
	@Autowired
	LikesService lservice;
	
	@Autowired
	VideoService vservice;
	
	@Autowired
	RegistrationService rservice;
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/like/{videoId}/{userId}")
	public int postRequest(@PathVariable("videoId") int videoId, @PathVariable("userId") int userId) {
		Likes like=new Likes();
		User uObj=rservice.fetchUserById(userId);
		Video vObj=vservice.getVideo(videoId);
		Set<Likes> setOfLikesUser=uObj.getLike();
		Set<Likes> setOfLikesVideo=vObj.getLike();
		setOfLikesUser.retainAll(setOfLikesVideo);
		int isLiked=setOfLikesUser.size();
		if(isLiked==0) {
			lservice.likeVideo(like);
			rservice.updateLikes(userId, like);
			vservice.updateLike(videoId, like);
		}
		else {
			Likes removeThisLike=setOfLikesUser.iterator().next();
			lservice.removeLike(removeThisLike);
			rservice.removeLike(userId, removeThisLike);
			vservice.removeLike(videoId, removeThisLike);
		}
		Video v1Obj=vservice.getVideo(videoId);
		Set<Likes> setOfLikesVideo1=v1Obj.getLike();
		return setOfLikesVideo1.size();
	}

}
