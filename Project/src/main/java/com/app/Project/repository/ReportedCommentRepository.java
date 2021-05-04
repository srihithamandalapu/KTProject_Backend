package com.app.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Project.model.ReportedComments;
import com.app.Project.model.Video;

@Repository
public interface ReportedCommentRepository extends JpaRepository<ReportedComments,Integer> {
	public ReportedComments save(ReportedComments repcom);
}
