package com.kpdcl.inbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kpdcl.inbound.entity.divisionEmail;
@Repository
public interface divisionEmailRepository extends JpaRepository<divisionEmail,Long>{

}
