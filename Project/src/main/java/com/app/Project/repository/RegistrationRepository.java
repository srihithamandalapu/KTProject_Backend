package com.app.Project.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Project.model.Comment;
import com.app.Project.model.Likes;
import com.app.Project.model.ReportedComments;
import com.app.Project.model.ReportedVideos;
import com.app.Project.model.User;
import com.app.Project.model.Video;

import org.springframework.stereotype.Repository;

import com.app.Project.model.Likes;
import com.app.Project.model.User;

@Repository
public interface RegistrationRepository extends JpaRepository<User, Integer> {

	public User findByEmailId(String emailId);
	public User findByEmailIdAndPassword(String emailId, String password);
	public User findById(int id);
	public User findByComment(Comment com);
	public List<User> findByReportedCom(ReportedComments com);
	public List<User> findByReportedVid(ReportedVideos vid);
	public User findByVidsPosted(Video vObj);

}
