package com.kpdcl.inbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kpdcl.inbound.entity.Approval;
import com.kpdcl.inbound.entity.approval_hierarchy;
@Repository
public interface approvalRepository extends JpaRepository<Approval,Long>{

	

}
