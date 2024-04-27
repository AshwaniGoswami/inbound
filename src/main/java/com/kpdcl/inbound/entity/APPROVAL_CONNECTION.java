package com.kpdcl.inbound.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "approvalConnection")
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
}

