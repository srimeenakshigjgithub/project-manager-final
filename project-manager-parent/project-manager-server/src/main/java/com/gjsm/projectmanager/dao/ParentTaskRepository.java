package com.gjsm.projectmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gjsm.projectmanager.jpaentity.ParentEntity;

@Repository
@Transactional
public interface ParentTaskRepository extends JpaRepository<ParentEntity, Integer> {

		@Query("from ParentEntity pt where pt.parentId=(:parentId)")
		ParentEntity findByParentTaskId(@Param("parentId") int parentId);
		
		@Modifying
		@Query(value = "DELETE FROM PARENT_TASK where project_id=(:projectId)", nativeQuery = true)
	    void deleteByProjectId(@Param("projectId") int projectId);
}
