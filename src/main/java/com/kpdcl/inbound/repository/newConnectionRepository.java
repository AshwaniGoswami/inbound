package com.kpdcl.inbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.kpdcl.inbound.entity.NEW_CONNECTION;


@Repository
public interface newConnectionRepository extends JpaRepository<NEW_CONNECTION, Long>{
	@Query("SELECT h FROM NEW_CONNECTION h WHERE h.case_id = :caseId")
	NEW_CONNECTION findByCaseId(Long caseId);
	

}
