package com.kpdcl.inbound.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kpdcl.inbound.entity.approval_hierarchy;
//import com.kpdcl.inbound.entity.approval_hierarchy;
import com.kpdcl.inbound.entity.hierarchy;

@Repository
public interface hierarchyRepository extends JpaRepository<hierarchy, Long>{
	@Query("SELECT h FROM hierarchy h WHERE h.case_id = :caseId")
    hierarchy findByCaseId(Long caseId);

	   @Query(value = "SELECT DISTINCT OFFICE_CODE " +
               "FROM DATA_HIERARCHY d " +
               "INNER JOIN SUB_DIVISION s ON d.DIVISION_NAME = s.DIVISION " +
               "WHERE s.SUB_DIVISION = :subDivision", nativeQuery = true)
String findOfficeCode(@Param("subDivision") String subDivision);

	   @Query(value="SELECT STD_CODE FROM DATA_HIERARCHY WHERE OFFICE_CODE = :office", nativeQuery= true)
	String findSTDCODE(String office);

	   
//	   @Query(value="SELECT h FROM HIERARCHY h WHERE h.divisionCode = :divisionCode AND h.sanctionedLoad >= :sanctionedLoadStart AND h.sanctionedLoad < :sanctionedLoadEnd", nativeQuery= true)
//	    List<hierarchy> findByDivisionCodeAndSanctionedLoadRange(@Param("divisionCode") String divisionCode, @Param("sanctionedLoadStart") int sanctionedLoadStart, @Param("sanctionedLoadEnd") int sanctionedLoadEnd);
//
//	    @Query(value="SELECT h FROM hierarchy h WHERE h.divisionCode = :divisionCode AND h.sanctionedLoad >= :sanctionedLoad", nativeQuery= true)
//	    List<hierarchy> findByDivisionCodeAndSanctionedLoadGreaterThanEqual(@Param("divisionCode") String divisionCode, @Param("sanctionedLoad") int sanctionedLoad);
//	    
//	    @Query(value="SELECT h FROM hierarchy h WHERE h.divisionCode = :divisionCode AND h.sanctionedLoad >= :sanctionedLoadStart AND h.sanctionedLoad < :sanctionedLoadEnd", nativeQuery= true)
//	    List<hierarchy> findBySTDCodeAndSanctionedLoadRange(@Param("divisionCode") String divisionCode, @Param("sanctionedLoadStart") int sanctionedLoadStart, @Param("sanctionedLoadEnd") int sanctionedLoadEnd);
//
//	    @Query(value="SELECT h FROM hierarchy h WHERE h.divisionCode = :divisionCode AND h.sanctionedLoad >= :sanctionedLoad", nativeQuery= true)
//	    List<hierarchy> findBySTDCodeAndSanctionedLoadGreaterThanEqual(@Param("divisionCode") String divisionCode, @Param("sanctionedLoad") int sanctionedLoad);
//	    
//	    @Query(value="SELECT h FROM hierarchy h WHERE h.divisionCode = :divisionCode AND h.sanctionedLoad >= :sanctionedLoadStart AND h.sanctionedLoad < :sanctionedLoadEnd", nativeQuery= true)
//	    List<hierarchy> findByWingCodeAndSanctionedLoadRange(@Param("divisionCode") String divisionCode, @Param("sanctionedLoadStart") int sanctionedLoadStart, @Param("sanctionedLoadEnd") int sanctionedLoadEnd);
//
//	    @Query(value="SELECT h FROM hierarchy h WHERE h.divisionCode = :divisionCode AND h.sanctionedLoad >= :sanctionedLoad", nativeQuery= true)
//	    List<hierarchy> findByWingCodeAndSanctionedLoadGreaterThanEqual(@Param("divisionCode") String divisionCode, @Param("sanctionedLoad") int sanctionedLoad);
//	    
//	    @Query(value="SELECT h FROM hierarchy h WHERE h.divisionCode = :divisionCode AND h.sanctionedLoad >= :sanctionedLoadStart AND h.sanctionedLoad < :sanctionedLoadEnd", nativeQuery= true)
//	    List<hierarchy> findBycircleCodeAndSanctionedLoadRange(@Param("divisionCode") String divisionCode, @Param("sanctionedLoadStart") int sanctionedLoadStart, @Param("sanctionedLoadEnd") int sanctionedLoadEnd);
//
//	    @Query(value="SELECT h FROM hierarchy h WHERE h.divisionCode = :divisionCode AND h.sanctionedLoad >= :sanctionedLoad", nativeQuery= true)
//	    List<hierarchy> findBycirlceCodeAndSanctionedLoadGreaterThanEqual(@Param("divisionCode") String divisionCode, @Param("sanctionedLoad") int sanctionedLoad);
//

//	   @Query(value = "SELECT h.* FROM hierarchy h " +
//               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
//               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
//               "WHERE sd.SUB_DIVISION = :subDivision " +
//               "AND dh.DIVISION_CODE = :divisionCode " +
//               "AND TO_NUMBER(h.SANCTIONED_LOAD) >= :sanctionedLoadStart " +
//               "AND TO_NUMBER(h.SANCTIONED_LOAD) < :sanctionedLoadEnd",
//       nativeQuery = true)
//	    List<hierarchy> findByDivisionCodeAndSanctionedLoadRange(@Param("divisionCode") String divisionCode,@Param("subDivision") String subDivision, @Param("sanctionedLoadStart") int sanctionedLoadStart, @Param("sanctionedLoadEnd") int sanctionedLoadEnd);
//=======================
//	   @Query(value = "SELECT h.* FROM hierarchy h " +
//               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
//               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
//               "WHERE sd.SUB_DIVISION = :subDivision " +
//               "AND dh.DIVISION_CODE = :divisionCode " +
//               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) >= :sanctionedLoadStart " +
//               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) < :sanctionedLoadEnd",
//       nativeQuery = true)
//List<hierarchy> findByDivisionCodeAndSanctionedLoadRange(@Param("divisionCode") String divisionCode,
//                                                         @Param("subDivision") String subDivision,
//                                                         @Param("sanctionedLoadStart") int sanctionedLoadStart,
//                                                         @Param("sanctionedLoadEnd") int sanctionedLoadEnd);
//
//	   
//	   	    @Query(value = "SELECT h.* FROM hierarchy h " +
//	               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
//	               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
//	               "WHERE sd.SUB_DIVISION = :subDivision " +
//	               "AND dh.DIVISION_CODE = :divisionCode " +
//	               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) >= :sanctionedLoad",
//	              
//	       nativeQuery = true)
//	    List<hierarchy> findByDivisionCodeAndSanctionedLoadGreaterThanEqual(@Param("divisionCode") String divisionCode,@Param("subDivision") String subDivision, @Param("sanctionedLoad") int sanctionedLoad);
//	    
//	    @Query(value = "SELECT h.* FROM hierarchy h " +
//	               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
//	               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
//	               "WHERE sd.SUB_DIVISION = :subDivision " +
//	               "AND dh.STD_CODE = :stdCode " +
//	               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) >= :sanctionedLoadStart " +
//                   "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) < :sanctionedLoadEnd",
//	       nativeQuery = true)
//	    List<hierarchy> findBySTDCodeAndSanctionedLoadRange(@Param("stdCode") String stdCode,@Param("subDivision") String subDivision, @Param("sanctionedLoadStart") int sanctionedLoadStart, @Param("sanctionedLoadEnd") int sanctionedLoadEnd);
//
//	    @Query(value = "SELECT h.* FROM hierarchy h " +
//	               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
//	               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
//	               "WHERE sd.SUB_DIVISION = :subDivision " +
//	               "AND dh.STD_CODE = :stdCode " +
//	               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) >= :sanctionedLoad",
//	       nativeQuery = true)
//	    List<hierarchy> findBySTDCodeAndSanctionedLoadGreaterThanEqual(@Param("stdCode") String stdCode,@Param("subDivision") String subDivision, @Param("sanctionedLoad") int sanctionedLoad);
//	    
//	    @Query(value = "SELECT h.* FROM hierarchy h " +
//	               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
//	               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
//	               "WHERE sd.SUB_DIVISION = :subDivision " +
//	               "AND dh.WING_CODE = :wingCode " +
//	               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) >= :sanctionedLoadStart " +
//                   "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) < :sanctionedLoadEnd",
//	       nativeQuery = true)
//	    List<hierarchy> findByWingCodeAndSanctionedLoadRange(@Param("wingCode") String wingCode,@Param("subDivision") String subDivision, @Param("sanctionedLoadStart") int sanctionedLoadStart, @Param("sanctionedLoadEnd") int sanctionedLoadEnd);
//
//	    @Query(value = "SELECT h.* FROM hierarchy h " +
//	               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
//	               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
//	               "WHERE sd.SUB_DIVISION = :subDivision " +
//	               "AND dh.WING_CODE = :wingCode " +
//	               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) >= :sanctionedLoad",
//	       nativeQuery = true)
//	    List<hierarchy> findByWingCodeAndSanctionedLoadGreaterThanEqual(@Param("wingCode") String wingCode,@Param("subDivision") String subDivision, @Param("sanctionedLoad") int sanctionedLoad);
//	    
//	    @Query(value = "SELECT h.* FROM hierarchy h " +
//	               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
//	               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
//	               "WHERE sd.SUB_DIVISION = :subDivision " +
//	               "AND dh.CIRCLE_CODE = :circleCode " +
//	               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) >= :sanctionedLoadStart " +
//                   "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) < :sanctionedLoadEnd",
//	       nativeQuery = true)
//	    List<hierarchy> findBycircleCodeAndSanctionedLoadRange(@Param("circleCode") String circleCode,@Param("subDivision") String subDivision, @Param("sanctionedLoadStart") int sanctionedLoadStart, @Param("sanctionedLoadEnd") int sanctionedLoadEnd);
//
//	    @Query(value = "SELECT h.* FROM hierarchy h " +
//	               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
//	               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
//	               "WHERE sd.SUB_DIVISION = :subDivision " +
//	               "AND dh.CIRCLE_CODE = :circleCode " +
//	               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) >= :sanctionedLoad",
//	       nativeQuery = true)
//	    List<hierarchy> findBycirlceCodeAndSanctionedLoadGreaterThanEqual(@Param("circleCode") String circleCode,@Param("subDivision") String subDivision, @Param("sanctionedLoad") int sanctionedLoad);
//
//	    
//	    @Query(value = "SELECT h.* FROM hierarchy h " +
//	               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
//	               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
//	               "WHERE sd.SUB_DIVISION = :subDivision " +
//	               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) > :sanctionedLoad",
//	       nativeQuery = true)
//		List<hierarchy> findByMDAndKPTCLANDSanctionedLoadGreaterThanEqual(@Param("subDivision") String subDivision, @Param("sanctionedLoad") int sanctionedLoad);


		
	   @Query(value = "SELECT h.* FROM hierarchy h " +
               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
               "WHERE sd.SUB_DIVISION = :subDivision " +
               "AND dh.DIVISION_CODE = :divisionCode " +
               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) >= :sanctionedLoad",
       nativeQuery = true)
List<hierarchy> findByDivisionCodeAndSanctionedLoadGreaterThanEqual(@Param("divisionCode") String divisionCode,
                                                                    @Param("subDivision") String subDivision,
                                                                    @Param("sanctionedLoad") int sanctionedLoad);

@Query(value = "SELECT h.* FROM hierarchy h " +
               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
               "WHERE sd.SUB_DIVISION = :subDivision " +
               "AND dh.STD_CODE = :stdCode " +
               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) >= :sanctionedLoad",
       nativeQuery = true)
List<hierarchy> findBySTDCodeAndSanctionedLoadGreaterThanEqual(@Param("stdCode") String stdCode,
                                                               @Param("subDivision") String subDivision,
                                                               @Param("sanctionedLoad") int sanctionedLoad);

@Query(value = "SELECT h.* FROM hierarchy h " +
               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
               "WHERE sd.SUB_DIVISION = :subDivision " +
               "AND dh.WING_CODE = :wingCode " +
               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) > :sanctionedLoad",
       nativeQuery = true)
List<hierarchy> findByWingCodeAndSanctionedLoadGreaterThanEqual(@Param("wingCode") String wingCode,
                                                                @Param("subDivision") String subDivision,
                                                                @Param("sanctionedLoad") int sanctionedLoad);

@Query(value = "SELECT h.* FROM hierarchy h " +
               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
               "WHERE sd.SUB_DIVISION = :subDivision " +
               "AND dh.CIRCLE_CODE = :circleCode " +
               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) > :sanctionedLoad",
       nativeQuery = true)
List<hierarchy> findByCircleCodeAndSanctionedLoadGreaterThanEqual(@Param("circleCode") String circleCode,
                                                                  @Param("subDivision") String subDivision,
                                                                  @Param("sanctionedLoad") int sanctionedLoad);

@Query(value = "SELECT h.* FROM hierarchy h " +
               "INNER JOIN SUB_DIVISION sd ON h.SUB_DIVISION = sd.SUB_DIVISION " +
               "INNER JOIN DATA_HIERARCHY dh ON sd.DIVISION = dh.DIVISION_NAME " +
               "WHERE sd.SUB_DIVISION = :subDivision " +
               "AND TO_NUMBER(REGEXP_SUBSTR(h.SANCTIONED_LOAD, '^[0-9]+')) > :sanctionedLoad",
       nativeQuery = true)
List<hierarchy> findByMDAndKPTCLANDSanctionedLoadGreaterThanEqual(@Param("subDivision") String subDivision,
                                                                  @Param("sanctionedLoad") int sanctionedLoad);

@Query(value = "SELECT * FROM Approval_Hierarchy WHERE CASE_ID IN (:caseIds)", nativeQuery = true)
List<Object[]> findApprovalHierarchyByCaseIds(@Param("caseIds") List<Long> caseIds);
}

 