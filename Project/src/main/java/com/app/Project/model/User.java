package com.app.Project.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class User {
	@Override
	public String toString() {
		return "User [emailId=" + emailId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", isadmin=" + isadmin + ", vidsPosted=" + vidsPosted + ", comment="
				+ comment + ", like=" + like + ", reportedCom=" + reportedCom + ", reportedVid=" + reportedVid
				+ ", cat=" + cat + "]";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String emailId;
	private String firstName;
	private String lastName;
	private String password;
	private String isadmin;
	
	//One User Many Videos
	@OneToMany
	//(mappedBy="user")
	@JoinColumn(name="postedBy", referencedColumnName="id")
	@Fetch(FetchMode.JOIN)
	Set<Video> vidsPosted=new HashSet<>();
	
	//One User Many Comments
	@OneToMany(cascade=CascadeType.ALL)
	//(mappedBy="user")
	@JoinColumn(name="postedBy", referencedColumnName="id")
	@Fetch(FetchMode.JOIN)
	Set<Comment> comment=new HashSet<>();
	
	//One User many Likes
	@OneToMany(cascade=CascadeType.ALL)
	//(mappedBy="user")
	@JoinColumn(name="postedBy", referencedColumnName="id")
	@Fetch(FetchMode.JOIN)
	Set<Likes> like=new HashSet<>();
	
	//One User many reported Comments
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="postedBy", referencedColumnName="id")
	@Fetch(FetchMode.JOIN)
	Set<ReportedComments> reportedCom =new HashSet<>();
	
	//One User can report many videos
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="postedBy", referencedColumnName="id")
	@Fetch(FetchMode.JOIN)
	Set<ReportedVideos> reportedVid =new HashSet<>();
	
	//Many User Many Subscriptions
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	Set<Category> cat=new HashSet<>();
	
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
//	Set<Comment> commentsReported=new HashSet<>();
	
	//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	//Set<Video> vidsReported=new HashSet<>();
	
	public User() {
	}
	
	public User(int id, String emailId, String firstName, String lastName, String password, String isadmin,Set<Video> vid,Set<Comment> comment,Set<Likes> like,Set<ReportedVideos> reportedVid,Set<ReportedComments> reportedCom,Set<Category> cat) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.firstName=firstName;
		this.lastName=lastName;
		this.password = password;
		this.isadmin = isadmin;
		this.vidsPosted=vid;
		this.comment=comment;
		this.like=like;
		this.reportedCom=reportedCom;
		this.reportedVid=reportedVid;
		this.cat=cat;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}

	
	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}
	
	public Set<Likes> getLike() {
		return like;
	}

	public void setLike(Set<Likes> like) {
		this.like = like;
	}

	public Set<Video> getVidsPosted() {
		return vidsPosted;
	}

	public void setVidsPosted(Set<Video> vidsPosted) {
		this.vidsPosted = vidsPosted;
	}

	public Set<Category> getCat() {
		return cat;
	}

	public void setCat(Set<Category> cat) {
		this.cat = cat;
	}

	public Set<ReportedComments> getReportedCom() {
		return reportedCom;
	}

	public void setReportedCom(Set<ReportedComments> reportedCom) {
		this.reportedCom = reportedCom;
	}

	public Set<ReportedVideos> getReportedVid() {
		return reportedVid;
	}

	public void setReportedVid(Set<ReportedVideos> reportedVid) {
		this.reportedVid = reportedVid;
	}

//	public Set<Comment> getCommentsReported() {
//		return commentsReported;
//	}
//
//	public void setCommentsReported(Set<Comment> commentsReported) {
//		this.commentsReported = commentsReported;
//	}

	
	
	
}
