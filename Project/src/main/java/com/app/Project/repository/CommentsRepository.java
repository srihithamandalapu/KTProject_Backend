package com.app.Project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Project.model.Comment;
import com.app.Project.model.ReportedComments;
import com.app.Project.model.User;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer>  {

	public Comment findById(int id);
	public Comment findByReportedCom(ReportedComments rcObj);


}
