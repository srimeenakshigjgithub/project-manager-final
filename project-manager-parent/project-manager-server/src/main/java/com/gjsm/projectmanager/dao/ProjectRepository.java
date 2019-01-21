package com.gjsm.projectmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gjsm.projectmanager.jpaentity.ProjectEntity;

@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {

	@Query("from ProjectEntity p where p.projectId=(:projectId)")
	ProjectEntity findByProjectId(@Param("projectId") int projectId);
	
	@Query("from ProjectEntity p where p.userEntity.userId=(:userId)")
	ProjectEntity findByUserId(@Param("userId") int userId);
	
	@Modifying
	@Query(value = "DELETE FROM PROJECT where user_id=(:userId)", nativeQuery = true)
    void deleteByUserId(@Param("userId") int userId);
}