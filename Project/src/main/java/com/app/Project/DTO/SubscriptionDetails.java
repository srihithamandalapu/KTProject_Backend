package com.app.Project.DTO;

public class SubscriptionDetails {
	private String catName;
	private boolean isSubs;
	
	
	public SubscriptionDetails() {

	}
	
	public SubscriptionDetails(String catName, boolean isSubs) {
		super();
		this.catName = catName;
		this.isSubs = isSubs;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public boolean isSubs() {
		return isSubs;
	}
	public void setSubs(boolean isSubs) {
		this.isSubs = isSubs;
	}

	@Override
	public String toString() {
		return "SubscriptionDetails [catName=" + catName + ", isSubs=" + isSubs + "]";
	}
	
	

}
