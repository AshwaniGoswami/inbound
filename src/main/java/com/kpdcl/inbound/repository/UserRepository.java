package com.kpdcl.inbound.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kpdcl.inbound.entity.UserRequest;



@Repository
public interface UserRepository extends JpaRepository<UserRequest, UUID> {
	@Query("SELECT h FROM UserRequest h WHERE h.user_id = :user_id")
	UserRequest findByUserId(UUID user_id);

	UserRequest findByEmail(String email);
}
