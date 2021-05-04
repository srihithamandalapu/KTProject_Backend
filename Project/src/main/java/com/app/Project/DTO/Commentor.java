package com.app.Project.DTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.app.Project.model.Category;
import com.app.Project.model.Comment;
import com.app.Project.model.Likes;
import com.app.Project.model.ReportedVideos;

public class Commentor {
	private int videoId;
	private String videoDesc;
	private String videoLink;
	private String isApproved;
	private String videoTitle;
	private List<Integer> cID;
	private List<String> commentMade;
	private List<String> name;
	
	public Commentor() {
		
	}
	
	
	public Commentor(int videoId, String videoDesc, String videoLink, String isApproved, String videoTitle, List<Integer> cID,
			List<String> commentMade, List<String> name, Set<Likes> like, Set<ReportedVideos> reportedVid, Set<Category> cat) {
		super();
		this.videoId = videoId;
		this.videoDesc = videoDesc;
		this.videoLink = videoLink;
		this.isApproved = isApproved;
		this.videoTitle = videoTitle;
		this.cID = cID;
		this.commentMade = commentMade;
		this.name = name;
		this.like = like;
		this.reportedVid = reportedVid;
		this.cat = cat;
	}
	public int getVideoId() {
		return videoId;
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
	public List<Integer> getcID() {
		return cID;
	}
	public void setcID(List<Integer> cID) {
		this.cID = cID;
	}
	public List<String> getCommentMade() {
		return commentMade;
	}
	public void setCommentMade(List<String> commentMade) {
		this.commentMade = commentMade;
	}
	public List<String> getName() {
		return name;
	}
	public void setName(List<String> name) {
		this.name = name;
	}
	public Set<Likes> getLike() {
		return like;
	}
	public void setLike(Set<Likes> like) {
		this.like = like;
	}
	public Set<ReportedVideos> getReportedVid() {
		return reportedVid;
	}
	public void setReportedVid(Set<ReportedVideos> reportedVid) {
		this.reportedVid = reportedVid;
	}
	public Set<Category> getCat() {
		return cat;
	}
	public void setCat(Set<Category> cat) {
		this.cat = cat;
	}
	Set<Likes> like=new HashSet<>();
	Set<ReportedVideos> reportedVid =new HashSet<>();
	Set<Category> cat=new HashSet<>();
	

}
