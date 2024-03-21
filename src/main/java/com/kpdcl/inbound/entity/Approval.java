package com.kpdcl.inbound.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Approval",
uniqueConstraints = {@UniqueConstraint(columnNames = {"ED_code", "STD_code"}),@UniqueConstraint(columnNames = {"ED_code","office_code"})})
public class Approval {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

		    @Getter @Setter
		    private String subDivision;

		    @Getter @Setter
		    private String division;

		    @Getter @Setter
		    private String circle;

		    @Getter @Setter
		    private String STD;
		    
		    @Getter @Setter
		    private Long ED_code;
		    
		    @Getter @Setter
		    private Long STD_code;
		    
		    @Getter @Setter
		    private Long office_code;
	}

