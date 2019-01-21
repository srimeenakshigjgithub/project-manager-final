package com.gjsm.projectmanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gjsm.projectmanager.jpaentity.TaskEntity;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

	@Query("from TaskEntity t where t.projectEntity.projectId=(:projectId)")
    List<TaskEntity> findByProjectId(@Param("projectId") int projectId);
	
	@Modifying
	@Query(value = "DELETE FROM TASK where project_id=(:projectId)", nativeQuery = true)
    void deleteByProjectId(@Param("projectId") int projectId);
}