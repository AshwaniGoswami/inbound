//package com.kpdcl.inbound.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import com.kpdcl.inbound.entity.hierarchy;
//
//@Repository
//public interface hierarchyRepository extends JpaRepository<hierarchy, Long>{
//
////	hierarchy findBycase_id(Long case_id);
//	hierarchy findByCaseId(Long caseId);
//}


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

//	void save(approval_hierarchy hierarchy);
//	
}
