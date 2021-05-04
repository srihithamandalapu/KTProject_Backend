package com.app.Project.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Project.model.Comment;
import com.app.Project.model.ReportedComments;
import com.app.Project.model.User;
import com.app.Project.model.Video;
import com.app.Project.repository.CommentsRepository;
import com.app.Project.repository.RegistrationRepository;
import com.app.Project.repository.VideoRepository;

@SuppressWarnings("unused")
@Service
public class CommentService {
	
	@Autowired
	private CommentsRepository crepo;
	
	@Autowired
	private RegistrationRepository repo;
	
	public Comment saveComment(Comment comment) {
		return crepo.save(comment);
	}
	
	public Comment fetchCommentbyId(int id) {
		return crepo.findById(id);
	}
	
	public void updateReportedComment(int id, ReportedComments repcom) {
		Comment targetComment=crepo.findById(id);
		if(targetComment!=null) {
			Set<ReportedComments> s= targetComment.getReportedCom();
			s.add(repcom);
			targetComment.setReportedCom(s);
			crepo.save(targetComment);
		}
	}

	public Comment findCommentByRep(ReportedComments rcObj) {
		return crepo.findByReportedCom(rcObj);
	}

	
	public List<Comment> fetchAllComment() {
		return crepo.findAll();
	}
	
	public Comment getCommentByRepComId(ReportedComments com) {
		return crepo.findByReportedCom(com);

	}

}
