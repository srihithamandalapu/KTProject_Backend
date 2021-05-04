package com.app.Project.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Project.model.Comment;
import com.app.Project.model.Likes;

import com.app.Project.model.ReportedVideos;
import com.app.Project.model.User;
import com.app.Project.model.Video;
import com.app.Project.repository.VideoRepository;

@Service
public class VideoService {
	
	
	@Autowired
	private VideoRepository vrepo;
	public Video saveVideo(Video video) {
		return vrepo.save(video);
	}
	
	public List<Video> getAllVideos(){
		return vrepo.findAll();
	}
	
	public List<Video> getApprovedVideos(){
		return vrepo.findByIsApproved("Yes");
	}

	public List<Video> getUnapprovedVideos(){
		return vrepo.findByIsApproved("No");
	}
	
	
	public Video getVideo(int id) {
		List<Video> lTemp= vrepo.findAll();
//		lTemp.forEach(l->{
//			System.out.println(l.getVideoId());
//		});
		Video vTemp= vrepo.findById(id);
		return vTemp;
	}
	
	public void updateCommentOnVideo(int id, Comment cmnt) {
		Video targetVideo=vrepo.findById(id);
		if(targetVideo!=null) {
			Set<Comment> s=targetVideo.getComment();
			s.add(cmnt);
			targetVideo.setComment(s);
			vrepo.save(targetVideo);
		}	
	}
	public void updateLike(int id, Likes like) {
		Video targetVideo=vrepo.findById(id);
		if(targetVideo!=null) {
			Set<Likes> s=targetVideo.getLike();
			s.add(like);
			targetVideo.setLike(s);
			vrepo.save(targetVideo);
		}
	}

	public void removeLike(int id, Likes like) {
		Video targetVideo=vrepo.findById(id);
		if(targetVideo!=null) {
			Set<Likes> s=targetVideo.getLike();
			s.remove(like);
			targetVideo.setLike(s);
			vrepo.save(targetVideo);
		}
	}
	public void updateReportedVideo(int id, ReportedVideos repvid) {
		Video targetVideo=vrepo.findById(id);
		if(targetVideo!=null) {
			Set<ReportedVideos> s= targetVideo.getReportedVid();
			s.add(repvid);
			targetVideo.setReportedVid(s);
			vrepo.save(targetVideo);
		}
	}

	public Video getVideoByComment(Comment comObj) {
		return vrepo.findByComment(comObj);
	}

	public List<Video> getVideoByRepVid(ReportedVideos reportedVid) {
		return vrepo.findByReportedVid(reportedVid);
	}
	
	public void deleteVideo(Video video) {
		vrepo.delete(video);
	}

	
	public void deleteVideoById(int id) {
		vrepo.deleteById(id);
	}

}
