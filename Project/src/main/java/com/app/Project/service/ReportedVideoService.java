package com.app.Project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Project.model.ReportedComments;
import com.app.Project.model.ReportedVideos;
import com.app.Project.repository.ReportedVideoRepository;

@Service
public class ReportedVideoService {
	@Autowired
	private ReportedVideoRepository repvidrepo;
	public ReportedVideos saveReportedVideos(ReportedVideos repvid) {
		return repvidrepo.save(repvid);
	}
	
	public List<ReportedVideos> getReportedVideos(){
		return repvidrepo.findAll();
	}
	
}
