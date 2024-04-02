package com.kpdcl.inbound.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import com.kpdcl.inbound.entity.approval_hierarchy;
import com.kpdcl.inbound.entity.dataHierarchy;

@Repository
public interface dataRepository extends JpaRepository<dataHierarchy, Long> {

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM dataHierarchy d WHERE d.Division_code = :divisionCode")
    boolean existsByDivision_code(String divisionCode);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM dataHierarchy d WHERE d.circle_code = :circleCode")
    boolean existsBycircle_code(String circleCode);

    @Query("SELECT d.STD_email FROM dataHierarchy d WHERE d.Division_code = :divisionCode")
    String findSTDEmailByDivisionCode(String divisionCode);

    @Query("SELECT d.Division_email FROM dataHierarchy d WHERE d.Division_code = :divisionCode")
    String findDivisionEmailByDivisionCode(String divisionCode);

    @Query("SELECT d.circle_email FROM dataHierarchy d WHERE d.circle_code = :circleCode")
	String findcircleEmailBycircleCode(String circleCode);

    @Query("SELECT d.Wing_email FROM dataHierarchy d WHERE d.Wing_code = :wingCode")
	String findwingEmailBywingCode(String wingCode);


	


	
}

