package com.kpdcl.inbound.entity;



import java.io.Serializable;

import javax.persistence.*;
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
	
	//@Column(unique = true)
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
	    
}
//	    @Getter @Setter
//	    @ManyToOne
//	    private dataHierarchy data_hierarchy;

//		public List<?> get(String string) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		public boolean containsKey(String string) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		public Entry<String, Object>[] entrySet() {
//			// TODO Auto-generated method stub
//			return null;
//		}
	    

