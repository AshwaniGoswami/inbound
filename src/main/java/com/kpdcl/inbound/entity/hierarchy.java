//package com.kpdcl.inbound.entity;
//
//
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.*;
//
////import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Table(name = "hierarchy")
//public class hierarchy implements Serializable{
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//	
//	@Getter @Setter
//	@Column(name = "case_id")
//	    private Long case_id;
//	
//	    @Getter @Setter
//	    private String user;
//
//
//	    @Getter @Setter
//	    private String sanctioned_load;
//
//	    @Getter @Setter
//	    private String Category;
//
//	    
//	    
//	    @Getter @Setter
//	    private String subDivision;
//	    
//	
//	    
//	    @Temporal(TemporalType.TIMESTAMP)
//	    @Column(name = "create_date", nullable = false, updatable = false)
//	    private Date createDate;
//
//	    @PrePersist
//	    protected void onCreate() {
//	        createDate = new Date();
//	    }
//
//		public Object getCreateDate() {
//			// TODO Auto-generated method stub
//			return createDate;
//		}
//
//
//	    
//}
//
//	    
//

package com.kpdcl.inbound.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

//import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hierarchy")

public class hierarchy implements Serializable {
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @Setter
    @Column(name = "case_id",unique=false)
    private Long case_id;
    @Getter @Setter
    @Column(name = "user1", nullable = true)
    private String user1;
    @Getter @Setter
    @Column(name = "sanctionedLoad", nullable = true)
    private String sanctionedLoad;
    @Getter @Setter
    @Column(name = "category", nullable = true)
    private String category;
    @Getter @Setter
    @Column(name = "subDivision", nullable = true)
    private String subDivision;
    
    @Getter @Setter
    @Column(name = "ApplicantMobileNumber", nullable = true)
    private Long ApplicantMobileNumber;
    
    @Getter @Setter 
    @Column(name = "ApplicantEmailId", nullable = true)
    private String ApplicantEmailId;
    
    @Getter @Setter
    @Column(name = "caseType", nullable = true)
    private String caseType;
    
    @Getter @Setter
    @Column(name = "caseStatus", nullable = true)
    private String caseStatus;
    
    @Column(name = "loadApprovalDate", nullable = true)
    private Date loadApprovalDate;
    
    
    @Getter @Setter
    @Column(name = "BSPEmailId")
    private String BSP_EmailId;
    
    @Getter @Setter
    @Column(name = "BSPMobileNumber")
    private Long BSP_MobileNumber;
    
    

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
    
	public Date getLoadApprovalDate() {
		return loadApprovalDate;
	}

	public void setLoadApprovalDate(Date date) {
		this.loadApprovalDate = date;
	}
}

