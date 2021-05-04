package com.app.Project.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int commentId;
	private String commentDesc;
	
	public Comment() {
	}
	
	public Comment(int commentId, String commentDesc,Set<ReportedComments> reportedCom) {
		super();
		this.commentId = commentId;
		this.commentDesc = commentDesc;
		this.reportedCom=reportedCom;
		//this.user = user;
	}
	
	   //one comment can be reported multiple times
		@OneToMany(cascade=CascadeType.ALL)
		@JoinColumn(name="commentId", referencedColumnName="commentId")
		@Fetch(FetchMode.JOIN)
		Set<ReportedComments> reportedCom =new HashSet<>();
		
//      @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	    @JoinTable(name = "ReportedComments",
//		joinColumns = { @JoinColumn(name = "commentId")},
//		inverseJoinColumns = { @JoinColumn (name = "userId")})
//	Set<User> user=new HashSet<>();
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentDesc() {
		return commentDesc;
	}
	public void setCommentDesc(String commentDesc) {
		this.commentDesc = commentDesc;
	}

	public Set<ReportedComments> getReportedCom() {
		return reportedCom;
	}

	public void setReportedCom(Set<ReportedComments> reportedCom) {
		this.reportedCom = reportedCom;
	}

	@Override
	public String toString() {
		return "Comment [ commentDesc=" + commentDesc + ", reportedCom=" + reportedCom
				+ "]";
	}
	
	
//	public Set<User> getUser() {
//		return user;
//	}
//	public void setUser(Set<User> user) {
//		this.user = user;
//	}
	
	
	
}
