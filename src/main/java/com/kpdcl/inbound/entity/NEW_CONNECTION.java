package com.kpdcl.inbound.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Getter @Setter
	private String applicantEmailId;
	
	 @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "create_date", updatable = false)
	    private Date createDate;

	    @PrePersist
	    protected void onCreate() {
	        createDate = new Date();
	    }

	    public Date getCreateDate() {
	        return createDate;
	    }
	

}
