package com.app.Project.DTO;

import java.util.List;

public class CommentById {
	private int videoId;
	private List<Integer> cId;
	private List<String> cDesc;
	private List<String> uName;
	
	public CommentById() {
	}
	
	public CommentById(int videoId, List<Integer> cId, List<String> cDesc, List<String> uName) {
		super();
		this.videoId = videoId;
		this.cId = cId;
		this.cDesc = cDesc;
		this.uName = uName;
	}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public List<Integer> getcId() {
		return cId;
	}
	public void setcId(List<Integer> cId) {
		this.cId = cId;
	}
	public List<String> getcDesc() {
		return cDesc;
	}
	public void setcDesc(List<String> cDesc) {
		this.cDesc = cDesc;
	}
	public List<String> getuName() {
		return uName;
	}
	public void setuName(List<String> uName) {
		this.uName = uName;
	}
	
	

}
