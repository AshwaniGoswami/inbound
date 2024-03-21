package com.kpdcl.inbound.entity;



import java.util.UUID;

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
	@GeneratedValue(generator = "UUID") 
	private UUID user_id;
    
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
   
//    @Getter @Setter
//    @ManyToOne
//    @JoinColumn(name = "officeCode", referencedColumnName = "officeCode")
//    private hierarchy Hierarchy;
    
}
