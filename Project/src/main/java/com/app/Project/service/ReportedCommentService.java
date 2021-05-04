package com.app.Project.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Project.model.Category;
import com.app.Project.model.Comment;
import com.app.Project.model.Likes;
import com.app.Project.model.ReportedComments;
import com.app.Project.model.User;
import com.app.Project.model.Video;
import com.app.Project.repository.RegistrationRepository;
import com.app.Project.repository.ReportedCommentRepository;

@Service
public class ReportedCommentService {
	
	@Autowired
	private ReportedCommentRepository repcomrepo;
	
	public ReportedComments saveReportedComment(ReportedComments repcom) {
		return repcomrepo.save(repcom);
	}
	
	public List<ReportedComments> getReportedComments(){
		return repcomrepo.findAll();
	}
	
	
}
