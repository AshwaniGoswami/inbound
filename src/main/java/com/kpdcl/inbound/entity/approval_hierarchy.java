package com.kpdcl.inbound.entity;




import java.io.Serializable;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "approval_hierarchy")
public class approval_hierarchy implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Getter @Setter
    private String EE_URL;
    @Getter @Setter
    @Column( columnDefinition = "NUMBER(1,0) default null")
    	private boolean EE_status;
    @Getter @Setter
    private String EE_Remark;
    
    @Getter @Setter
    private String STD_URL;
    @Getter @Setter
    @Column( columnDefinition = "NUMBER(1,0) default null")
    	private boolean STD_status;
    @Getter @Setter
    private String STD_Remark;
    
    @Getter @Setter
    private String SE_URL;
    @Getter @Setter
    @Column( columnDefinition = "NUMBER(1,0) default null")
    	private boolean SE_status;
    @Getter @Setter
    private String SE_Remark;
    
    @Getter @Setter
    private String CE_URL;
    @Getter @Setter
    @Column( columnDefinition = "NUMBER(1,0) default null")
    	private boolean CE_status;
    @Getter @Setter
    private String CE_Remark;
    @Getter @Setter
    private String KPTCL_URL;
    @Getter @Setter
    @Column( columnDefinition = "NUMBER(1,0) default null")
    	private boolean KPTCL_status;
    @Getter @Setter
    private String KPTCL_Remark;
    
    @Getter @Setter
    private String MD_URL;
    @Getter @Setter
    @Column( columnDefinition = "NUMBER(1,0) default null")
    	private boolean MD_status;
    @Getter @Setter
    private String MD_Remark;
    
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "case_id", referencedColumnName = "case_id")
    private hierarchy Hierarchy;

}
