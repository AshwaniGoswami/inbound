package com.kpdcl.inbound.entity;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hierarchy")
public class hierarchy implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Getter @Setter
	@Column(name = "case_id")
	    private Long case_id;

	    @Getter @Setter
	    private String Name_of_applicant;

	    @Getter @Setter
	    private String sanctioned_load;

	    @Getter @Setter
	    private String Category;

	    @Getter @Setter
	    private String description;
	    
	    @Getter @Setter
	    private String Division;
	    
	    @Getter @Setter
	    private String officeCode;
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "create_date", nullable = false, updatable = false)
	    private Date createDate;

	    @PrePersist
	    protected void onCreate() {
	        createDate = new Date();
	    }

		public Object getCreateDate() {
			// TODO Auto-generated method stub
			return createDate;
		}


	    
}

	    

