package com.kpdcl.inbound.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//
//import com.kpdcl.inbound.entity.NEW_CONNECTION;
//
//
//@Repository
//public interface newConnectionRepository extends JpaRepository<NEW_CONNECTION, Long>{
//	@Query("SELECT h FROM NEW_CONNECTION h WHERE h.case_id = :caseId")
//	NEW_CONNECTION findByCaseId(Long caseId);
//
//	
//	@Query(value="SELECT EMAIL_ID "+
//			"FROM HIERARCHY "+ 
//			"where CASE_ID= :caseId", nativeQuery=true)
//	NEW_CONNECTION findByEmail(Long caseId);
//
//	@Query(value="SELECT MOBILE_NUMBER FROM HIERARCHY where CASE_ID= :caseId", nativeQuery=true)
//	NEW_CONNECTION findByMobileNumber(Long caseId);
//
//	@Query(value="SELECT * FROM HIERARCHY  WHERE CASE_ID= :caseId", nativeQuery=true)
//	NEW_CONNECTION findByExistingCaseId(Long caseId);
//	
//	
//	
//
//}

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.kpdcl.inbound.entity.NEW_CONNECTION;
import com.kpdcl.inbound.entity.hierarchy;

@Repository
public interface newConnectionRepository extends JpaRepository<NEW_CONNECTION, Long> {

    @Query("SELECT h FROM hierarchy h WHERE h.case_id = :caseId")
    hierarchy findByCaseId(@Param("caseId") Long caseId);

    @Query(value = "SELECT BSPEMAIL_ID FROM HIERARCHY WHERE CASE_ID = :caseId", nativeQuery = true)
    String findEmailByCaseId(@Param("caseId") Long caseId);

    @Query(value = "SELECT BSPMOBILE_NUMBER FROM HIERARCHY WHERE CASE_ID = :caseId", nativeQuery = true)
    String findMobileNumberByCaseId(@Param("caseId") Long caseId);

    @Query(value = "SELECT * FROM HIERARCHY WHERE CASE_ID = :caseId", nativeQuery = true)
    NEW_CONNECTION findByExistingCaseId(@Param("caseId") Long caseId);
}

