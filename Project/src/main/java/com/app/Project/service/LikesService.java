package com.app.Project.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Project.model.Likes;
import com.app.Project.repository.LikesRepository;

@Service
public class LikesService {
	
	@Autowired
	private LikesRepository lrepo;
	
	public void likeVideo(Likes like) {
		lrepo.save(like);
	}
	public void removeLike(Likes like) {
		lrepo.deleteLikes(like.getLikeId());
	}

}
