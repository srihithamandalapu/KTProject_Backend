package com.app.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.Project.model.Likes;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer>  {
	@Modifying
	@Transactional
	@Query("delete from Likes v where v.likeId=:id")
	void deleteLikes(@Param("id") int likeId);

}
