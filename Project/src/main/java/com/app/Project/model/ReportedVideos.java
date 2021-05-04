package com.app.Project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReportedVideos {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ReportedVid;

	public int getReportedVid() {
		return ReportedVid;
	}

	public void setReportedVid(int reportedVid) {
		ReportedVid = reportedVid;
	}
	
	

}
