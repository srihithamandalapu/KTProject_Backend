package com.app.Project.model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@SuppressWarnings("unused")
@Entity
public class Video {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int videoId;
	private String videoDesc;
	private String videoLink;
	private String isApproved;
	private String videoTitle;
	
	//One video many comments
	@OneToMany(cascade=CascadeType.REMOVE)
	@JoinColumn(name="videoId", referencedColumnName="videoId")
	@Fetch(FetchMode.JOIN)
	Set<Comment> comment=new HashSet<>();
	
	//one video many likes
	@OneToMany(cascade=CascadeType.REMOVE)
	@JoinColumn(name="videoId", referencedColumnName="videoId")
	@Fetch(FetchMode.JOIN)
	Set<Likes> like=new HashSet<>();
	
	//one video can be reported multiple times
	@OneToMany(cascade=CascadeType.REMOVE)
	@JoinColumn(name="videoId", referencedColumnName="videoId")
	@Fetch(FetchMode.JOIN)
	Set<ReportedVideos> reportedVid =new HashSet<>();

	
	
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "ReportedVideos", joinColumns = { @JoinColumn(name =
//    "videoId")}, inverseJoinColumns = { @JoinColumn (name = "userId")}) 
//    Set<User> user=new HashSet<>();
	 
	//Many to Many with Category
	@ManyToMany(fetch = FetchType.LAZY,  mappedBy = "vid")
	Set<Category> cat=new HashSet<>();
	
	public Video() {

	}
	
	
	public Video(int videoId, String videoDesc, String videoLink, String isApproved, String videoTitle,
			Set<Comment> comment, Set<Likes> like, Set<Category> cat,Set<ReportedVideos> reportedVid) {
		super();
		this.videoId = videoId;
		this.videoDesc = videoDesc;
		this.videoLink = videoLink;
		this.isApproved = isApproved;
		this.videoTitle = videoTitle;
		this.comment = comment;
		this.like = like;
		//this.user = user;
		this.cat = cat;
		this.reportedVid=reportedVid;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

//	public Set<User> getUser() {
//		return user;
//	}
//
//	public void setUser(Set<User> user) {
//		this.user = user;
//	}
//	


	public int getVideoId() {
		return videoId;
	}


	public Set<ReportedVideos> getReportedVid() {
		return reportedVid;
	}


	public void setReportedVid(Set<ReportedVideos> reportedVid) {
		this.reportedVid = reportedVid;
	}


	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}


	public String getVideoDesc() {
		return videoDesc;
	}


	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}


	public String getVideoLink() {
		return videoLink;
	}


	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
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


//	public Set<ReportedComments> getReportedCom() {
//		return reportedCom;
//	}
//
//
//	public void setReportedCom(Set<ReportedComments> reportedCom) {
//		this.reportedCom = reportedCom;
//	}

	
	public Set<Category> getCat() {
		return cat;
	}


	public void setCat(Set<Category> cat) {
		this.cat = cat;
	}


	@Override
	public String toString() {
		return "Video [videoDesc=" + videoDesc + ", videoLink=" + videoLink + ", isApproved=" + isApproved
				+ ", videoTitle=" + videoTitle + ", comment=" + comment + ", like=" + like + ", reportedVid="
				+ reportedVid + ", cat=" + cat + "]";
	}

	
	
	
}
