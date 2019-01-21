package com.gjsm.projectmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gjsm.projectmanager.jpaentity.UserEntity;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	@Query("from UserEntity u where u.userId=(:userId)")
	UserEntity findByUserId(@Param("userId") int userId);
	
}
