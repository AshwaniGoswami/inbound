package com.kpdcl.inbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kpdcl.inbound.entity.APPROVAL_CONNECTION;


@Repository
public interface approvalConnectionRepository extends JpaRepository<APPROVAL_CONNECTION, Long>{
	

}
