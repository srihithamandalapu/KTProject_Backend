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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int categoryId;
	private String categoryName;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "VideoCategory",
		joinColumns = { @JoinColumn(name = "categoryId")},
		inverseJoinColumns = { @JoinColumn (name = "VideoId")})
	@JsonIgnore 
	Set<Video> vid=new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Subscription",
		joinColumns = { @JoinColumn(name = "categoryId")},
		inverseJoinColumns = { @JoinColumn (name = "userId")})
	@JsonIgnore 
	Set<User> user=new HashSet<>();
	
	public Category() {
		
	}
	
	public Category(int categoryId, String categoryName, Set<Video> vid,Set<User> user) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.vid = vid;
		this.user=user;
	}

	public Category(String string) {
		this.categoryName=string;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Video> getVid() {
		return vid;
	}

	public void setVid(Set<Video> vid) {
		this.vid = vid;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Category [categoryName=" + categoryName + ", vid=" + vid + ", user="
				+ user + "]";
	}
	
	

	
}
