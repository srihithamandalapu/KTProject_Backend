package com.app.Project.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private int id;
	private String emailId;
	private String userName;
	private String password;
	private String isadmin;
	
	public User() {
	}
	
	public User(int id, String emailId, String userName, String password, String isadmin) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.userName = userName;
		this.password = password;
		this.isadmin = isadmin;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String isadmin() {
		return isadmin;
	}
	public void setAdmin(String isadmin) {
		this.isadmin = isadmin;
	}
}
