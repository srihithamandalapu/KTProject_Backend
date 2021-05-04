package com.app.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Project.model.ReportedVideos;

@Repository
public interface ReportedVideoRepository extends JpaRepository<ReportedVideos,Integer> {
	
}
