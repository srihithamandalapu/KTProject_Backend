package com.app.Project.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Likes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int likeId;
	
	public Likes() {
	}

	public Likes(int likeId) {
		super();
		this.likeId = likeId;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	
	

}
