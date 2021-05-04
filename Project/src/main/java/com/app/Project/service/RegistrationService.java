package com.app.Project.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Project.model.Category;
import com.app.Project.model.Comment;
import com.app.Project.model.Likes;

import com.app.Project.model.ReportedComments;
import com.app.Project.model.ReportedVideos;

import com.app.Project.model.User;
import com.app.Project.model.Video;
import com.app.Project.repository.RegistrationRepository;

@Service
public class RegistrationService {

	@Autowired
	private RegistrationRepository repo;

	public User saveUser(User user) {
		return repo.save(user);
	}
	
	public User fetchUserByEmailId(String email) {
		return repo.findByEmailId(email);
	}
	
	public User fetchUserByEmailIdAndPassword(String email, String password) {
		return repo.findByEmailIdAndPassword(email, password);
	}
	
	public User fetchUserById(int id) {
		return repo.findById(id);
	}
	
	public List<User> getUser(){
		return repo.findAll();
	}
	
	public void updateUser(int id, Video v) {
		User targetUser=repo.findById(id);
		Set<Video> s=targetUser.getVidsPosted();
		s.add(v);
		targetUser.setVidsPosted(s);
		repo.save(targetUser);
	}

	public void updateComment(int id, Comment cmnt) {
		User targetUser=repo.findById(id);
		if(targetUser!=null) {
			Set<Comment> s= targetUser.getComment();
			s.add(cmnt);
			targetUser.setComment(s);
			repo.save(targetUser);
		}
	}
	
	public void updateLikes(int id, Likes like) {
		User targetUser=repo.findById(id);
		if(targetUser!=null) {
			Set<Likes> s= targetUser.getLike();
			s.add(like);
			targetUser.setLike(s);
			repo.save(targetUser);
		}
	}
	public void removeLike(int id, Likes like) {
		User targetUser=repo.findById(id);
		if(targetUser!=null) {
			Set<Likes> s= targetUser.getLike();
			s.remove(like);
			targetUser.setLike(s);
			repo.save(targetUser);
		}
	}

	public void updateReportedComment(int id, ReportedComments repcom) {
		User targetUser=repo.findById(id);
		if(targetUser!=null) {
			Set<ReportedComments> s= targetUser.getReportedCom();
			s.add(repcom);
			targetUser.setReportedCom(s);
			repo.save(targetUser);
		}
	}
	
	public void updateReportedVideo(int userId, ReportedVideos repvid) {
		User targetUser=repo.findById(userId);
		if(targetUser!=null) {
			Set<ReportedVideos> s= targetUser.getReportedVid();
			s.add(repvid);
			targetUser.setReportedVid(s);
			repo.saveAndFlush(targetUser);
		}
	}
	
	public User getUserByCom(Comment com) {
		return repo.findByComment(com);
	}
	
	public List<User> getUserByRepCom(ReportedComments com) {
		return repo.findByReportedCom(com);
	}

	public List<User> getUserByRepVid(ReportedVideos vid) {
		return repo.findByReportedVid(vid);
	}

	public User getUserByVideo(Video vObj) {
		return repo.findByVidsPosted(vObj);
	}

}
