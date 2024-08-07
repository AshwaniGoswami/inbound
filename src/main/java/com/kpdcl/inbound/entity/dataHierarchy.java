package com.kpdcl.inbound.entity;



//import java.util.UUID;

import javax.persistence.*;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "data_hierarchy",uniqueConstraints = {@UniqueConstraint(columnNames = {"Division_code", "STD_code"})})
public class dataHierarchy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @Getter @Setter
    private String Wing_name;
    @Getter @Setter
    private String Wing_code;
    @Getter @Setter
    private String Wing_email;
    
    @Getter @Setter
    private String Division_name;
    
    @Getter @Setter
    private String Division_code;
    
    @Getter @Setter
    private String Division_email;
    
    @Getter @Setter
    private String circle_name;
    
    @Getter @Setter
    private String circle_code;
    
    @Getter @Setter
    private String circle_email;
    
    @Getter @Setter
    private String STD_name;
    
    @Getter @Setter
    private String STD_code;
    
    @Getter @Setter
    private String STD_email;
   
    @Getter @Setter
    private String MD_email;
    @Getter @Setter
    private String JKPTCL_email;
    
    @Getter @Setter
    private String office_code;

    
}
