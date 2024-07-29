package com.kpdcl.inbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kpdcl.inbound.entity.APPROVAL_CONNECTION;


@Repository
public interface approvalConnectionRepository extends JpaRepository<APPROVAL_CONNECTION, Long>{
	 @Query(value="SELECT * FROM PAYMENT_UPDATE WHERE CASE_ID = :caseId", nativeQuery=true)
	 	APPROVAL_CONNECTION findByCaseId(Long caseId);
	

}

