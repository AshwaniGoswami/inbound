package com.kpdcl.inbound.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//import lombok.Getter;
//import lombok.Setter;
@Data
@Entity
@Table(name = "approval_lifecycle")
public class approval_lifecycle {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String current_status;
	
	private String new_status;
	
	private String owner;
}
