package com.kpdcl.inbound.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "new_connection")
public class NEW_CONNECTION {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	@Getter @Setter
	private UUID user_id;
	@Getter @Setter
	private Long case_id;
	@Getter @Setter
	private String email_id;
	@Getter @Setter
	private String mobile_number;
	
    @Getter @Setter
	private Long dsa_amount;
	    
	@Getter @Setter
    private Long msa_amount;
	

}
