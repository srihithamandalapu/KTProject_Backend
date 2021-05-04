package com.app.Project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ReportedComments {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reportedId;
	
	/*@ManyToOne(targetEntity = User.class)
    private User user;
*/
	public int getReportedID() {
		return reportedId;
	}

	public void setReportedID(int reportedId) {
		this.reportedId = reportedId;
	}
		
	
}
