package com.kpdcl.inbound.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table(name = "user2")
public class UserRequest {

	@Id
	@GeneratedValue(generator = "UUID") 
	private UUID user_id;

	@Getter
	@Setter
	private String firstName;
	@Getter
	@Setter
	private String lastName;
	@Getter
	@Setter
//	@Column(unique = true)
	private String email;
	@Getter
	@Setter
//	@Column(unique = true)
	private String mobile;
	@Getter
	@Setter
	private String password;
	@Getter
	@Setter
	private String email_OTP;
	@Getter
	@Setter
	private long mobile_OTP;
@Getter
@Setter
	@Column(nullable = false, columnDefinition = "NUMBER(1,0) default 1")
	private boolean phone_verification;
@Getter
@Setter
	@Column(nullable = false, columnDefinition = "NUMBER(1,0) default 1")
	private boolean email_verification;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false, updatable = false)
	private Date createDate;

	@PrePersist
	protected void onCreate() {
		createDate = new Date();
	}

	public UUID getUserId() {
		return user_id;
	}

	public void setUserId(UUID userId) {
		this.user_id = userId;
	}
}
