package com.kpdcl.inbound.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kpdcl.inbound.entity.approval_hierarchy;
import com.kpdcl.inbound.entity.hierarchy;

@Repository
public interface approvalHierarchyRepository extends JpaRepository<approval_hierarchy, Long>{

	@Query("select ah from com.kpdcl.inbound.entity.approval_hierarchy ah WHERE ah.Hierarchy.case_id = :caseId")
	approval_hierarchy findByHierarchy_CaseId(@Param("caseId") long caseId);
	  
//	@Query(value = "SELECT DISTINCT a.NEW_STATUS " +
//            "FROM APPROVAL_LIFECYCLE a " +
//            "INNER JOIN APPROVAL_HIERARCHY ah ON a.CURRENT_STATUS = ah.STATUS " +
//            "WHERE ah.STATUS = :approvalHierarchyStatus", 
//    nativeQuery = true)
//	approval_hierarchy findByNEW_STATUS(String approvalHierarchyStatus);
	
	@Query(value = "SELECT DISTINCT a.NEW_STATUS " +
            "FROM APPROVAL_LIFECYCLE a " +
            "INNER JOIN APPROVAL_HIERARCHY ah ON a.CURRENT_STATUS = ah.STATUS " +
            "WHERE ah.STATUS = :approvalHierarchyStatus", 
    nativeQuery = true)
String findNewStatusByCurrentStatus(@Param("approvalHierarchyStatus") String approvalHierarchyStatus);
	
	
	@Query(value = "SELECT DISTINCT a.NEW_STATUS " +
            "FROM APPROVAL_LIFECYCLE a " +
            "INNER JOIN APPROVAL_HIERARCHY ah ON a.CURRENT_STATUS = ah.STATUS " +
            "WHERE ah.STATUS = :newstatus", 
    nativeQuery = true)
	String findStatusByCurrentStatus(@Param("newstatus") String newstatus);

//	@Query(value="SELECT status FROM APPROVAL_HIERARCHY WHERE CASE_ID=:caseId" ,nativeQuery= true)
//	String findStatus(@Param("caseId") long caseId);
//	
	@Query(value = "SELECT * FROM APPROVAL_HIERARCHY WHERE TO_NUMBER(REGEXP_SUBSTR(SANCTIONED_LOAD, '^[0-9]+')) > :sanctionedLoad OR EE_URL IS NOT NULL", nativeQuery = true)
    List<approval_hierarchy> findBySanctionedLoadAndEE(@Param("sanctionedLoad") int sanctionedLoad);

    @Query(value = "SELECT * FROM APPROVAL_HIERARCHY WHERE TO_NUMBER(REGEXP_SUBSTR(SANCTIONED_LOAD, '^[0-9]+')) > :sanctionedLoad OR STD_URL IS NOT NULL", nativeQuery = true)
    List<approval_hierarchy> findBySanctionedLoadAndSTD(@Param("sanctionedLoad") int sanctionedLoad);

    @Query(value = "SELECT * FROM APPROVAL_HIERARCHY WHERE TO_NUMBER(REGEXP_SUBSTR(SANCTIONED_LOAD, '^[0-9]+')) > :sanctionedLoad OR SE_URL IS NOT NULL", nativeQuery = true)
    List<approval_hierarchy> findBySanctionedLoadAndSE(@Param("sanctionedLoad") int sanctionedLoad);

    @Query(value = "SELECT * FROM APPROVAL_HIERARCHY WHERE TO_NUMBER(REGEXP_SUBSTR(SANCTIONED_LOAD, '^[0-9]+')) > :sanctionedLoad OR CE_URL IS NOT NULL", nativeQuery = true)
    List<approval_hierarchy> findBySanctionedLoadAndCE(@Param("sanctionedLoad") int sanctionedLoad);

    @Query(value = "SELECT * FROM APPROVAL_HIERARCHY WHERE TO_NUMBER(REGEXP_SUBSTR(SANCTIONED_LOAD, '^[0-9]+')) > :sanctionedLoad", nativeQuery = true)
    List<approval_hierarchy> findBySanctionedLoadAndMD(@Param("sanctionedLoad") int sanctionedLoad);

    @Query(value = "SELECT * FROM APPROVAL_HIERARCHY WHERE TO_NUMBER(REGEXP_SUBSTR(SANCTIONED_LOAD, '^[0-9]+')) > :sanctionedLoad OR KPTCL_URL IS NOT NULL", nativeQuery = true)
    List<approval_hierarchy> findBySanctionedLoadAndKPTCL(@Param("sanctionedLoad") int sanctionedLoad);
}
	


