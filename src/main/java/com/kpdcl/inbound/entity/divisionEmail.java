package com.kpdcl.inbound.entity;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "divisionEmail")
public class divisionEmail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	    
	@Getter @Setter
	    private String email;

	    
	@Getter @Setter
	    private String division;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "ED_code", referencedColumnName = "ED_code")
	private Approval approval;
	    
}
