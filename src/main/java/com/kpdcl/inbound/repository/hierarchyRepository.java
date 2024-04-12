package com.kpdcl.inbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import com.kpdcl.inbound.entity.approval_hierarchy;
import com.kpdcl.inbound.entity.hierarchy;

@Repository
public interface hierarchyRepository extends JpaRepository<hierarchy, Long>{
	@Query("SELECT h FROM hierarchy h WHERE h.case_id = :caseId")
    hierarchy findByCaseId(Long caseId);

}
