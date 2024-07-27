package com.kpdcl.inbound.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name="subDivision")
public class subDivision {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	 private String Division;
	 
	 private String subDivision;
}
