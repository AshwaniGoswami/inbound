package com.kpdcl.inbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kpdcl.inbound.entity.approval_hierarchy;

@Repository
public interface approvalHierarchyRepository extends JpaRepository<approval_hierarchy, Long>{

	@Query("select ah from com.kpdcl.inbound.entity.approval_hierarchy ah WHERE ah.Hierarchy.case_id = :caseId")
	approval_hierarchy findByHierarchy_CaseId(@Param("caseId") long caseId);
	
}

