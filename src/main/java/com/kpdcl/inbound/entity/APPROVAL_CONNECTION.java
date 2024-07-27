package com.kpdcl.inbound.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "paymentUpdate")
public class APPROVAL_CONNECTION {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Getter @Setter
    private Long user_id;
    
    @Getter @Setter
    private Long case_id;
    
    @Getter @Setter
    private Date newconnection_date; // Change data type to java.util.Date
    
    @Getter @Setter
    private Boolean payment_status;
    
    @Getter @Setter
    private Long dsa_amount;
    
    @Getter @Setter
    private Long msa_amount;
    
    @Getter @Setter
    private Long dsa_amount_id;
    
    @Getter @Setter
    private Long msa_amount_id;
    
    @Getter @Setter
    private String mode;
    
    @Getter @Setter
    private String receipt_url;
    
    @Column(name = "uploadDate", nullable = true)
    private Date uploadDate;
    
	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date date) {
		this.uploadDate = date;
	}
}

