package com.kpdcl.inbound.controller;

import java.util.*;
import java.util.stream.Collectors;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpdcl.inbound.entity.approval_hierarchy;
import com.kpdcl.inbound.entity.dataHierarchy;
import com.kpdcl.inbound.entity.hierarchy;
import com.kpdcl.inbound.repository.approvalHierarchyRepository;
import com.kpdcl.inbound.repository.dataRepository;
import com.kpdcl.inbound.repository.hierarchyRepository;
import com.kpdcl.inbound.service.ApprovalHierarchyService;
//import com.kpdcl.inbound.service.DateUtil;
import com.kpdcl.inbound.service.EmailService;
import com.kpdcl.inbound.service.HierarchyService;
import com.kpdcl.inbound.service.dataHierarchyService;
import com.kpdcl.inbound.service.methods;





@RestController
public class Approval {
	
	@Autowired
	private hierarchyRepository hierarchyRepo;
	
	@Autowired
	private approvalHierarchyRepository approvalHierarchyRepo;
	
	  @Autowired
	    private HierarchyService hierarchyService;
	  
	  @Autowired
	    private ApprovalHierarchyService approvalhierarchyService;
	  
	  @Autowired
	    private dataHierarchyService service;
	  
	
	@Autowired
	private dataRepository dataRepo;
	
	private final EmailService emailService;
	private final methods method;
	 public Approval(EmailService emailService,methods method) {
	        this.emailService = emailService;
	        this.method = method;
	        }
	 
	 
    
	@PostMapping("/api/hierarchy")
    public ResponseEntity<?> createData(@RequestBody hierarchy jsonData) {
        try {
        	
        	    ObjectMapper objectMapper = new ObjectMapper();
              String json = objectMapper.writeValueAsString(jsonData);
               System.out.println("Received request in saveLinkConsumer method. Request Body: " + json);
            // Extracting necessary information from jsonData
        	//System.out.println("Received JSON Data: " + jsonData);
               String sanctionedLoadStr = jsonData.getSanctionedLoad();
               double sanctionedLoad = method.extractNumericValue(sanctionedLoadStr);
             if(sanctionedLoad>25.0) {
        	long caseId=jsonData.getCase_id();
        	String user1=jsonData.getUser1();
        	String category=jsonData.getCategory();
        	String subDivision=jsonData.getSubDivision();
        	String applicantId=jsonData.getApplicantEmailId();
        	Long applicantMobile=jsonData.getApplicantMobileNumber();
        	//hierarchy caseID=hierarchyRepo.findByCaseId(caseId);
        	//come from approval_hierarchy entity
        	
        	 hierarchy existingRecord = hierarchyRepo.findByCaseId(caseId);

//        	if(existingRecord==null)
//        	{
//        		return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("caseId not exists");
//        	}
        	
        	//String subDivision=jsonData.getSubDivision();
        	
        	String office=hierarchyRepo.findOfficeCode(subDivision);
//        	System.out.println(office);
        	
//            System.out.println(sanctionedLoad);
          
            //String office = jsonData.getOfficeCode();
           
            // Extracting the first two digits and the next two digits from the office code
            String wingCode = office.substring(0, 2);
            String circleCode = office.substring(2, 4);
            String divisionCode = office.substring(4, 6);
            String stdCode=hierarchyRepo.findSTDCODE(office);
//            System.out.println(stdCode);
//            System.out.println(wingCode);
//            System.out.println(divisionCode);
//            System.out.println(sanctionedLoad);
        
            // Check if circle code and division code are valid
            boolean circleCodeExists = method.circleCodeExists(circleCode);
            boolean divisionCodeExists = method.divisionCodeExists(divisionCode);

            if (!circleCodeExists) {
                // Return a bad request response if either circle code or division code is invalid
                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Invalid circle code");
            }
            if (!divisionCodeExists) {
                // Return a bad request response if either circle code or division code is invalid
                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Invalid division code.");
            }

            String stdEmail = "";
            String divisionEmail = "";
            String circleEmail="";
            String wingEmail="";
            String mdEmail="";
            String jkptclEmail="";

            if (sanctionedLoad > 25.0 && sanctionedLoad <= 50.0) {
                // Fetch STD email
            	stdEmail = dataRepo.findSTDEmailByDivisionCode(divisionCode);
                divisionEmail = dataRepo.findDivisionEmailByDivisionCode(divisionCode);

                // Fetch division email
                emailService.sendEmails(stdEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,stdCode,"sc");
                emailService.sendEmails(divisionEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,divisionCode,"dc");
              Map<String, Object> responseBody = new HashMap<>();
              responseBody.put("stdEmail", stdEmail);
              responseBody.put("divisionEmail", divisionEmail);
           }
            
            else if (sanctionedLoad > 50.0 && sanctionedLoad <= 100.0) {
                
            	 stdEmail = dataRepo.findSTDEmailByDivisionCode(divisionCode);
                 divisionEmail = dataRepo.findDivisionEmailByDivisionCode(divisionCode);
                 circleEmail=dataRepo.findcircleEmailBycircleCode(divisionCode);
                 //System.out.println(circleEmail); 
                
                 emailService.sendEmails(divisionEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,divisionCode,"dc");
                 emailService.sendEmails(stdEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,stdCode,"sc");
                 emailService.sendEmails(circleEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,circleCode,"cc");
	             
	             Map<String, Object> responseBody = new HashMap<>();
	              responseBody.put("stdEmail", stdEmail);
	              responseBody.put("divisionEmail", divisionEmail);
	              responseBody.put("divisionEmail", circleEmail);
                
            }
            else if (sanctionedLoad > 100.0 && sanctionedLoad <= 500.0) {
                // Fetch STD email
                stdEmail = dataRepo.findSTDEmailByDivisionCode(divisionCode);
                divisionEmail = dataRepo.findDivisionEmailByDivisionCode(divisionCode);
                circleEmail=dataRepo.findcircleEmailBycircleCode(divisionCode);
                wingEmail=dataRepo.findwingEmailBywingCode(divisionCode);

                emailService.sendEmails(divisionEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,divisionCode,"dc");
                emailService.sendEmails(stdEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,stdCode,"sc");
                emailService.sendEmails(circleEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,circleCode,"cc");
                emailService.sendEmails(wingEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,wingCode,"wc");
                
                Map<String, Object> responseBody = new HashMap<>();
	              responseBody.put("stdEmail", stdEmail);
	              responseBody.put("divisionEmail", divisionEmail);
	              responseBody.put("divisionEmail", circleEmail);
	              responseBody.put("divisionEmail", wingEmail);
            }

            else if (sanctionedLoad > 500.0) {
                // Fetch STD emai
                stdEmail = dataRepo.findSTDEmailByDivisionCode(divisionCode);
                divisionEmail = dataRepo.findDivisionEmailByDivisionCode(divisionCode);
                circleEmail=dataRepo.findcircleEmailBycircleCode(divisionCode);
                wingEmail=dataRepo.findwingEmailBywingCode(divisionCode);
                mdEmail=dataRepo.findmdEmailBywingCode(divisionCode);
                jkptclEmail=dataRepo.findjkptclEmailBywingCode(divisionCode);

                emailService.sendEmails(divisionEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,divisionCode,"dc");
                emailService.sendEmails(stdEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,stdCode,"sc");
                emailService.sendEmails(circleEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,circleCode,"cc");
                emailService.sendEmails(wingEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,wingCode,"wc");
                emailService.sendEmails(mdEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,office,"as");
                emailService.sendEmails(jkptclEmail, "Approval Request", "Approval request for the Sanctioned Load : " , jsonData,office,"zx");
                
                Map<String, Object> responseBody = new HashMap<>();
	              responseBody.put("stdEmail", stdEmail);
	              responseBody.put("divisionEmail", divisionEmail);
	              responseBody.put("divisionEmail", circleEmail);
	              responseBody.put("divisionEmail", wingEmail);
            }
            // Update the fields in the existing record
//            existingRecord.setCase_id(caseId);
//            existingRecord.setUser1(user1);
//            existingRecord.setSubDivision(subDivision);
//            existingRecord.setSanctionedLoad(sanctionedLoad1);
//            existingRecord.setCategory(category);
//            existingRecord.setLoadApprovalDate(new Date());
            // Create or update the record
            hierarchy hierarchy = existingRecord != null ? existingRecord : new hierarchy();
            hierarchy.setCase_id(caseId);
            hierarchy.setUser1(user1);
            hierarchy.setSubDivision(subDivision);
            hierarchy.setSanctionedLoad(sanctionedLoadStr);
            hierarchy.setCategory(category);
            hierarchy.setLoadApprovalDate(new Date());
            hierarchy.setApplicantEmailId(applicantId);
            hierarchy.setApplicantMobileNumber(applicantMobile);
            
            hierarchyRepo.save(hierarchy);
            return ResponseEntity.status(HttpStatus.SC_CREATED).body("Successful");
             }
             else {
            	 hierarchyRepo.save(jsonData);
            	 return ResponseEntity.status(HttpStatus.SC_CREATED).body("Successful");
             }
        } catch (NumberFormatException e) {
            // If the sanctioned load cannot be parsed as a double, return a bad request response
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Invalid sanctioned load. Must be a numeric value.");
        } catch (Exception e) {
            // If there is an error, return an appropriate HTTP status code
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

	@GetMapping("/view_hierarchy")
	public ResponseEntity<?> getAllData() {
	    try {
	        List<hierarchy> hierarchyList = hierarchyRepo.findAll();
	        if (hierarchyList.isEmpty()) {
                return ResponseEntity.status(404).body("No data found");
            }
	        else {
	        	List<Map<String,Object>> responseData=hierarchyList.stream().
	        			map(hierarchy->{
	        				Map<String,Object> hierarchyData= new HashMap<>();
	        				hierarchyData.put("case_id", hierarchy.getCase_id());
	        				hierarchyData.put("sanctioned_load", hierarchy.getSanctionedLoad());
	        				//hierarchyData.put("description", hierarchy.getDescription());
	        				//hierarchyData.put("officeCode", hierarchy.getOfficeCode());
	        				hierarchyData.put("category", hierarchy.getCategory());
	        				//hierarchyData.put("name_of_applicant", hierarchy.getName_of_applicant());
	        				hierarchyData.put("division", hierarchy.getSubDivision());
	        				hierarchyData.put("createDate", hierarchy.getCreateDate());
	        				return hierarchyData;
	        			}).collect(Collectors.toList());
	        
	        return ResponseEntity.ok().body(responseData);
	        }
	    } catch (Exception e) {
	        // If there is an error, return an appropriate HTTP status code
	        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Internal Server Error");
	    }
	}
	



//    @PostMapping("/approval-hierarchy")
//    public ResponseEntity<String> createOrUpdateApprovalHierarchy(
//            @RequestBody Map<String, Object> hierarchyData, 
//            @RequestParam String ColumnName) {
//        try {
//            @SuppressWarnings("unchecked")
//			Map<String, Object> hierarchyMap = (Map<String, Object>) hierarchyData.get("hierarchy");
//            Long caseId = ((Number) hierarchyMap.get("case_id")).longValue();
//            String sanctionedLoad = (String) hierarchyMap.get("sanctionedLoad");
//            String category = (String) hierarchyMap.get("category");
//            String updatedBy = (String) hierarchyData.get("UPDATED_BY");
//            String status = (String) hierarchyData.get("status");
//
//            hierarchy existingHierarchy = hierarchyRepo.findByCaseId(caseId);
//            if (existingHierarchy == null) {
//                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                        .body("Hierarchy with case_id " + caseId + " does not exist");
//            }
//
//            approval_hierarchy existingApprovalHierarchy = approvalHierarchyRepo.findByHierarchy_CaseId(caseId);
//            double sanctionedLoadValue = method.extractNumericValue(sanctionedLoad);
//
//            if (existingApprovalHierarchy != null) {
//            	String columnStatusField = method.getColumnStatusField(ColumnName);
//                String currentStatus = method.getStatusField(existingApprovalHierarchy, columnStatusField);
//                if ((ColumnName + " approved").equals(currentStatus) || (ColumnName + " rejected").equals(currentStatus)) {
//                    return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                            .body("This approval has already been completed by " + ColumnName);
//                }
//                return method.updateApprovalHierarchy(existingApprovalHierarchy, sanctionedLoadValue, ColumnName, hierarchyData, updatedBy, status);
//            } else {
//                return method.createApprovalHierarchy(existingHierarchy, sanctionedLoad, category, ColumnName, hierarchyData, updatedBy, status, sanctionedLoadValue);
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
//                    .body("Error occurred: " + e.getMessage());
//        }
//    }
	@PostMapping("/approval-hierarchy")
	public ResponseEntity<String> createOrUpdateApprovalHierarchy(
	        @RequestBody Map<String, Object> hierarchyData,
	        @RequestParam String ColumnName) {
	    try {
	        ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(hierarchyData);
             System.out.println("Received request in saveLinkConsumer method. Request Body: " + json);
	        Map<String, Object> hierarchyMap = (Map<String, Object>) hierarchyData.get("hierarchy");
	        Long caseId = ((Number) hierarchyMap.get("case_id")).longValue();
	        String sanctionedLoad = (String) hierarchyMap.get("sanctionedLoad");
	        String category = (String) hierarchyMap.get("category");
	        String updatedBy = (String) hierarchyData.get("UPDATED_BY");
	        String status = (String) hierarchyData.get("status");
	       
	        hierarchy existingHierarchy = hierarchyRepo.findByCaseId(caseId);
	        if (existingHierarchy == null) {
	            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
	                    .body("Hierarchy with case_id " + caseId + " does not exist");
	        }

	        approval_hierarchy existingApprovalHierarchy = approvalHierarchyRepo.findByHierarchy_CaseId(caseId);
	        double sanctionedLoadValue = method.extractNumericValue(sanctionedLoad);

	        if (existingApprovalHierarchy != null) {
	            // Check if the current column is already approved or rejected
	            String columnStatusField = method.getColumnStatusField(ColumnName);
	            String currentStatus = method.getStatusField(existingApprovalHierarchy, columnStatusField);
	            if ((ColumnName + " approved").equals(currentStatus) || (ColumnName + " rejected").equals(currentStatus) || ("approved").equals(currentStatus) || ("rejected").equals(currentStatus)) {
	                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
	                        .body("This approval has already been completed by " + ColumnName);
	            }
	            return method.updateApprovalHierarchy(existingApprovalHierarchy, sanctionedLoadValue, ColumnName, hierarchyData, updatedBy, status);
	        } else {
	            return method.createApprovalHierarchy(existingHierarchy, sanctionedLoad, category, ColumnName, hierarchyData, updatedBy, status, sanctionedLoadValue);
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
	                .body("Error occurred: " + e.getMessage());
	    }
	}

        @GetMapping("/hierarchy")
    public Map<String, Object> getHierarchy(
            @RequestParam String columnName,
            @RequestParam String code,
            @RequestParam String subDivision,
            @RequestParam String sanctionedLoad) {
        return hierarchyService.getHierarchyData(columnName, code, subDivision, sanctionedLoad);
    }
    
        

        
        @GetMapping("/approval_hierarchy")
        public ResponseEntity<?> getApprovalHierarchy(
                @RequestParam String sanctionedLoad,
                @RequestParam String columnName) {
            return approvalhierarchyService.getApprovalHierarchy(sanctionedLoad, columnName);
        }

        @PostMapping("data_hierarchy")
        public ResponseEntity<dataHierarchy> createDataHierarchy(@RequestBody dataHierarchy dataHierarchy) {
            dataHierarchy savedDataHierarchy = dataRepo.save(dataHierarchy);
            return ResponseEntity.status(HttpStatus.SC_CREATED).body(savedDataHierarchy);
        }
        
        @PostMapping("/newConnection_Hierarchy")
        public ResponseEntity<?> getNewConnectionHierarchy(@RequestBody hierarchy newHierarchy){
        	try {
        	ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(newHierarchy);
             System.out.println("Received request in saveLinkConsumer method. Request Body: " + json);
        	Long caseId=newHierarchy.getCase_id();
        	String BSPEmailId=newHierarchy.getBSP_EmailId();
        	Long BSPMobileNumber=newHierarchy.getBSP_MobileNumber();
        	String caseType=newHierarchy.getCaseType();        	
        	 
        	hierarchy hierarchy =  new hierarchy();
        	 hierarchy.setCase_id(caseId);
        	 hierarchy.setBSP_EmailId(BSPEmailId);
        	 hierarchy.setBSP_MobileNumber(BSPMobileNumber);
        	 hierarchy.setCaseType(caseType);
        	 
        	 
        	hierarchy caseID=hierarchyRepo.findByCaseId(caseId);
        	
        	if(caseID!=null)
        	{
        		return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("caseId already exists");
        	}
        	
        	hierarchyRepo.save(hierarchy);
        	return ResponseEntity.status(HttpStatus.SC_CREATED).body("newConnection data Create successfully");
        }catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
	                .body("Error occurred: " + e.getMessage());
	    }
        }
        
        
        
        
        @PostMapping("/find")
        public ResponseEntity<String> findHierarchy(@RequestParam String officeCode, @RequestParam String code, @RequestParam String officeName) {
            return service.findByOfficeCode(officeCode, code, officeName);
        }
        
}      
//    	@GetMapping("/view_hierarchy")
//    	public ResponseEntity<?> getAllData() {
//    	    try {
//    	        List<hierarchy> hierarchyList = hierarchyRepo.findAll();
    //
//    	        // Create a list to hold the data to be returned
//    	        List<Map<String, Object>> responseData = new ArrayList<>();
    //
//    	        // Iterate through each hierarchy object and extract the necessary data
//    	        for (hierarchy hierarchy : hierarchyList) {
//    	            Map<String, Object> hierarchyData = new HashMap<>();
//    	            hierarchyData.put("id", hierarchy.getCase_id());
////    	            hierarchyData.put("name", hierarchy.getName());
//    	            hierarchyData.put("createDate", hierarchy.getCreateDate()); // Assuming getCreateDate() returns the createDate
    //
//    	            responseData.add(hierarchyData);
//    	        }
    //
//    	        return ResponseEntity.ok().body(responseData);
//    	    } catch (Exception e) {
//    	        // If there is an error, return an appropriate HTTP status code
//    	        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Internal Server Error");
//    	    }
//    	}



       

        
//        @PostMapping("/approval-hierarchy")
//        public ResponseEntity<String> createOrUpdateApprovalHierarchy(@RequestBody approval_hierarchy hierarchy1) {
//        	try {
//        		Long caseId = hierarchy1.getHierarchy().getCase_id();
//        		String url = hierarchy1.getEE_URL();
//        		
//        		// Check if the case_id exists in the hierarchy table
//        		hierarchy existingHierarchy = hierarchyRepo.findByCaseId(caseId);
//        		if (existingHierarchy == null) {
//        			return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Hierarchy with case_id " + caseId + " does not exist");
//        		}
//        	
//        		// Check if the case_id exists in the approval_hierarchy table
//        		approval_hierarchy existingApprovalHierarchy = approvalHierarchyRepo.findByHierarchy_CaseId(caseId);
//        	
//        		if (existingApprovalHierarchy != null) {
//        			// Update the existing entry
//        			method.updateExistingHierarchy(existingApprovalHierarchy, hierarchy1);
//        			approvalHierarchyRepo.save(existingApprovalHierarchy);
//        			return ResponseEntity.ok("Approval Hierarchy updated successfully");
//        		} else {
//        			// Set the hierarchy in the approval_hierarchy object
//        			hierarchy1.setHierarchy(existingHierarchy);
//        			
//        			// Insert the new entry
//        			approval_hierarchy savedApprovalHierarchy = approvalHierarchyRepo.save(hierarchy1);
//        			if (savedApprovalHierarchy != null) {
//        				return ResponseEntity.status(HttpStatus.SC_CREATED).body("Approval Hierarchy created successfully");
//        			} 
//        			else {
//        				return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to save Approval Hierarchy");
//        			}
//        		}
//        	} catch (Exception e) {
//        		return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
//        	}
//        }
        
        
        
//        @PostMapping("/approval-hierarchy")
//        public ResponseEntity<String> createOrUpdateApprovalHierarchy(@RequestBody approval_hierarchy hierarchy1, @RequestParam String ColumnName) {
//            try {
//                Long caseId = hierarchy1.getHierarchy().getCase_id();
//                
//                String url = hierarchy1.getEE_URL(); // Assuming this URL is used somewhere
//                String updatedBy=hierarchy1.getUPDATED_BY();
//                // Check if the case_id exists in the hierarchy table
//                hierarchy existingHierarchy = hierarchyRepo.findByCaseId(caseId);
//                if (existingHierarchy == null) {
//                    return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Hierarchy with case_id " + caseId + " does not exist");
//                }
    //
    //
//                
//                // Check if the case_id exists in the approval_hierarchy table
//                approval_hierarchy existingApprovalHierarchy = approvalHierarchyRepo.findByHierarchy_CaseId(caseId);
    //
//                
//                if (existingApprovalHierarchy != null) {
//                    // Update the existing entry
////                    method.updateExistingHierarchy(existingApprovalHierarchy, hierarchy1,ColumnName);
//                	
//                	method.updateExistingHierarchy(existingApprovalHierarchy,ColumnName);
//                    approvalHierarchyRepo.save(existingApprovalHierarchy);
//                    return ResponseEntity.ok("Approval Hierarchy updated successfully");
//                } else {
//                    // Set the hierarchy in the approval_hierarchy object
//                    hierarchy1.setHierarchy(existingHierarchy);
//                    String category=hierarchy1.getHierarchy().getCategory();
//                    String sanctionedLoadStr =  hierarchy1.getHierarchy().getSanctionedLoad();
//                    double sanctionedLoad = method.extractNumericValue(sanctionedLoadStr);
//                    // Set default values based on sanctionedLoad
//                    if (sanctionedLoad < 500) {
//                        hierarchy1.setKPTCL_status("null");
//                    } else {
//                        hierarchy1.setKPTCL_status("not approved");
//                    }
//                    hierarchy1.setSTD_status("not approved");
//                    hierarchy1.setStatus("AEE approved");
    //
//                    // Set updatedOn and updatedBy (assuming these are coming from the request or a security context)
//                    hierarchy1.setUpdatedOn(new Date());
//                    hierarchy1.setUPDATED_BY(updatedBy); // Replace with actual user
//                    hierarchy1.setCategory(category);
//                    hierarchy1.setSanctionedLoad(sanctionedLoadStr);
//                 // Fetch and set additional columns based on frontend input
//                    //String columnName = ColumnName; // Assuming columnName is sent in the request
    //
//                    
//                    // Insert the new entry
//                    approval_hierarchy savedApprovalHierarchy = approvalHierarchyRepo.save(method.updateExistingHierarchy(hierarchy1,ColumnName));
//                    return ResponseEntity.status(HttpStatus.SC_CREATED).body("Approval Hierarchy created successfully");
//                }
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
//            }
//        }

        
        
//        @PostMapping("/approval-hierarchy")
//        public ResponseEntity<String> createOrUpdateApprovalHierarchy(
//                @RequestBody Map<String, Object> hierarchyData, 
//                @RequestParam String ColumnName) {
//            try {
//                Map<String, Object> hierarchyMap = (Map<String, Object>) hierarchyData.get("hierarchy");
//                Long caseId = ((Number) hierarchyMap.get("case_id")).longValue();
//                String sanctionedLoad = (String) hierarchyMap.get("sanctionedLoad");
//                String category = (String) hierarchyMap.get("category");
//                String updatedBy = (String) hierarchyData.get("UPDATED_BY");
//                String status=(String) hierarchyData.get("status");
//                
//                // Check if the case_id exists in the hierarchy table
//                hierarchy existingHierarchy = hierarchyRepo.findByCaseId(caseId);
//                if (existingHierarchy == null) {
//                    return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                            .body("Hierarchy with case_id " + caseId + " does not exist");
//                }
    //
//                // Check if the case_id exists in the approval_hierarchy table
//                approval_hierarchy existingApprovalHierarchy = approvalHierarchyRepo.findByHierarchy_CaseId(caseId);
//                double sanctionedLoadValue = method.extractNumericValue(sanctionedLoad);
//                if (existingApprovalHierarchy != null) {
//                    // Update the existing entry
//                	String approvalHierarchyStatus = existingApprovalHierarchy.getStatus();
//                	//fetch new status based on the current status from the ApprovalLifecycle table
//                    String newStatus = approvalHierarchyRepo.findNewStatusByCurrentStatus(approvalHierarchyStatus);
//                	System.out.println(sanctionedLoadValue);
//                	   if (status != null && !status.equals(newStatus)) {
//                           return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                                   .body("not matched:"+status+"!="+newStatus);
//                       }
//                	
//                       
//                                	   
//                	   
//                    method.updateDynamicFields(existingApprovalHierarchy, hierarchyData, ColumnName);
//                    existingApprovalHierarchy.setUPDATED_BY(updatedBy);
//                    existingApprovalHierarchy.setUpdatedOn(new Date());
//                    
//                    if (sanctionedLoadValue >= 25.0 && sanctionedLoadValue < 50.0) {
//                        if ("approved".equals(existingApprovalHierarchy.getSTD_status()) && "EE approved".equals(existingApprovalHierarchy.getStatus())) {
//                            return ResponseEntity.status(HttpStatus.SC_CREATED).body("finally approved");
//                        }
//                    } 
//                    else if (sanctionedLoadValue >= 50.0 && sanctionedLoadValue < 100.0) {
//                        if ("approved".equals(existingApprovalHierarchy.getSTD_status()) && "SE approved".equals(existingApprovalHierarchy.getStatus())) {
//                            return ResponseEntity.status(HttpStatus.SC_CREATED).body("finally approved");
//                        }
//                    }
//                    
//                    else if (sanctionedLoadValue >= 100.0 && sanctionedLoadValue < 500.0) {
//                        if ("approved".equals(existingApprovalHierarchy.getSTD_status()) && "CE approved".equals(existingApprovalHierarchy.getStatus())) {
//                            return ResponseEntity.status(HttpStatus.SC_CREATED).body("finally approved");
//                        }
//                    }
//                    
//                    else if (sanctionedLoadValue >= 500.0) {
//                        if ("approved".equals(existingApprovalHierarchy.getSTD_status()) && "MD approved".equals(existingApprovalHierarchy.getStatus()) && "approved".equals(existingApprovalHierarchy.getKPTCL_status())) {
//                            return ResponseEntity.status(HttpStatus.SC_CREATED).body("finally approved");
//                        }
//                    }
//                    if (!"STD".equals(ColumnName) && !"KPTCL".equals(ColumnName)) {
//                        existingApprovalHierarchy.setStatus(status);
//                    }	
//                    approvalHierarchyRepo.save(existingApprovalHierarchy);
//                    return ResponseEntity.ok("Approval Hierarchy updated successfully");
//                } else {
//                    // Create new approval_hierarchy entry
//                    approval_hierarchy newHierarchy = new approval_hierarchy();
//                    newHierarchy.setHierarchy(existingHierarchy);
//                    newHierarchy.setSanctionedLoad(sanctionedLoad);
//                    newHierarchy.setCategory(category);
//                   
    //
//                    // Set default values based on sanctionedLoad
//                    
//                    if (sanctionedLoadValue < 500) {
//                        newHierarchy.setKPTCL_status("null");
//                    } else {
//                        newHierarchy.setKPTCL_status("not approved");
//                    }
//                    newHierarchy.setSTD_status("not approved");
//                    newHierarchy.setStatus("AEE approved");
//                    newHierarchy.setUpdatedOn(new Date());
//                    newHierarchy.setUPDATED_BY(updatedBy);
//                    String newstatus=newHierarchy.getStatus();
//                    
//                   
//                	//fetch new status based on the current status from the ApprovalLifecycle table
//                    String newStatus = approvalHierarchyRepo.findStatusByCurrentStatus(newstatus);
//                	System.out.println(newStatus);
//                	   if (status != null && !status.equals(newStatus)) {
//                           return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                                   .body("not matched:"+status+"!="+newStatus);
//                       }
//                    
    //
//                    // Fetch and set additional columns based on frontend input
//                    method.updateDynamicFields(newHierarchy, hierarchyData, ColumnName);
//                    if ("STD".equals(ColumnName) || "KPTCL".equals(ColumnName)) {
//                    	newHierarchy.setStatus("AEE approved");
//                    }
//                    else {
//                    newHierarchy.setStatus(status);
//                    }
//                    // Insert the new entry
//                    approval_hierarchy savedApprovalHierarchy = approvalHierarchyRepo.save(newHierarchy);
//                    return ResponseEntity.status(HttpStatus.SC_CREATED).body("Approval Hierarchy created successfully");
//                }
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
//                        .body("Error occurred: " + e.getMessage());
//            }
//        }
        
//        @PostMapping("/approval-hierarchy")
//        public ResponseEntity<String> createOrUpdateApprovalHierarchy(
//                @RequestBody Map<String, Object> hierarchyData, 
//                @RequestParam String ColumnName) {
//            try {
//                Map<String, Object> hierarchyMap = (Map<String, Object>) hierarchyData.get("hierarchy");
//                Long caseId = ((Number) hierarchyMap.get("case_id")).longValue();
//                String sanctionedLoad = (String) hierarchyMap.get("sanctionedLoad");
//                String category = (String) hierarchyMap.get("category");
//                String updatedBy = (String) hierarchyData.get("UPDATED_BY");
//                String status = (String) hierarchyData.get("status");
//                
//                // Check if the case_id exists in the hierarchy table
//                hierarchy existingHierarchy = hierarchyRepo.findByCaseId(caseId);
//                if (existingHierarchy == null) {
//                    return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                            .body("Hierarchy with case_id " + caseId + " does not exist");
//                }
    //
//                // Check if the case_id exists in the approval_hierarchy table
//                approval_hierarchy existingApprovalHierarchy = approvalHierarchyRepo.findByHierarchy_CaseId(caseId);
//                double sanctionedLoadValue = method.extractNumericValue(sanctionedLoad);
//                
//                if (existingApprovalHierarchy != null) {
//                    // Update the existing entry
//               // to check which authority approve     
//                	if (sanctionedLoadValue >= 25.0 && sanctionedLoadValue < 50.0) {
//                    	if ("SE".equals(ColumnName) || "CE".equals(ColumnName) || "MD".equals(ColumnName) || "KPTCL".equals(ColumnName)) {
//                    		return ResponseEntity.status(HttpStatus.SC_CREATED).body("They are not able to approve this");
//                    	}
//                    }
//                    else if (sanctionedLoadValue >= 50.0 && sanctionedLoadValue < 100.0) {
//                    	if ("CE".equals(ColumnName) || "MD".equals(ColumnName) || "KPTCL".equals(ColumnName)) {
//                    		return ResponseEntity.status(HttpStatus.SC_CREATED).body("They are not able to approve this");
//                    	}
//                    }
//                    else if (sanctionedLoadValue >= 100.0 && sanctionedLoadValue < 500.0) {
//                    	if ("MD".equals(ColumnName) || "KPTCL".equals(ColumnName)) {
//                    		return ResponseEntity.status(HttpStatus.SC_CREATED).body("They are not able to approve this");
//                    	}
//                    }
//                 
//              // Fetch new status based on the current status from the ApprovalLifecycle table
//                    String approvalHierarchyStatus = existingApprovalHierarchy.getStatus();
//                    String newStatus = approvalHierarchyRepo.findNewStatusByCurrentStatus(approvalHierarchyStatus);
//                    if (status != null && !status.equals(newStatus)) {
//                        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                                .body("you cannot approve this until previous authority approves");
//                    }
//                    
//                    method.updateDynamicFields(existingApprovalHierarchy, hierarchyData, ColumnName);
//                    existingApprovalHierarchy.setUPDATED_BY(updatedBy);
//                    existingApprovalHierarchy.setUpdatedOn(new Date());
    //
    //
//                    if (!"STD".equals(ColumnName) && !"KPTCL".equals(ColumnName)) {
//                        existingApprovalHierarchy.setStatus(status);
//                    }
//                    
//                    approval_hierarchy savedApprovalHierarchy=approvalHierarchyRepo.save(existingApprovalHierarchy);
//                    
//              // Check for final approval conditions
//                    if (sanctionedLoadValue >= 25.0 && sanctionedLoadValue < 50.0) {
//                    	if ("approved".equals(savedApprovalHierarchy.getSTD_status()) && "EE approved".equals(savedApprovalHierarchy.getStatus())) {
//                    		return ResponseEntity.status(HttpStatus.SC_CREATED).body("finally approved");
//                    	}
//                    } else if (sanctionedLoadValue >= 50.0 && sanctionedLoadValue < 100.0) {
//                    	if ("approved".equals(savedApprovalHierarchy.getSTD_status()) && "SE approved".equals(savedApprovalHierarchy.getStatus())) {
//                    		return ResponseEntity.status(HttpStatus.SC_CREATED).body("finally approved");
//                    	}
//                    } else if (sanctionedLoadValue >= 100.0 && sanctionedLoadValue < 500.0) {
//                    	if ("approved".equals(savedApprovalHierarchy.getSTD_status()) && "CE approved".equals(savedApprovalHierarchy.getStatus())) {
//                    		return ResponseEntity.status(HttpStatus.SC_CREATED).body("finally approved");
//                    	}
//                    } else if (sanctionedLoadValue >= 500.0) {
//                    	if ("approved".equals(savedApprovalHierarchy.getSTD_status()) && "MD approved".equals(savedApprovalHierarchy.getStatus()) && "approved".equals(existingApprovalHierarchy.getKPTCL_status())) {
//                    		return ResponseEntity.status(HttpStatus.SC_CREATED).body("finally approved");
//                    	}
//                    }
//                    return ResponseEntity.ok("Approval Hierarchy updated successfully");
//                } else {
//                    // Create new approval_hierarchy entry
//                    approval_hierarchy newHierarchy = new approval_hierarchy();
//                    newHierarchy.setHierarchy(existingHierarchy);
//                    newHierarchy.setSanctionedLoad(sanctionedLoad);
//                    newHierarchy.setCategory(category);
    //
//                    // Set default values based on sanctionedLoad
//                    if (sanctionedLoadValue < 500) {
//                        newHierarchy.setKPTCL_status("null");
//                    } else {
//                        newHierarchy.setKPTCL_status("not approved");
//                    }
//                    newHierarchy.setSTD_status("not approved");
//                    newHierarchy.setStatus("AEE approved");
//                    newHierarchy.setUpdatedOn(new Date());
//                    newHierarchy.setUPDATED_BY(updatedBy);
    //
//                    // Fetch new status based on the current status from the ApprovalLifecycle table
//                    //String newStatus = approvalHierarchyRepo.findStatusByCurrentStatus("AEE approved");
//                    if (status != null && !status.equals("EE approved")) {
//                        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                                .body("you cannot approve this until previous authority approves");
//                    }
    //
//                    // Fetch and set additional columns based on frontend input
//                    method.updateDynamicFields(newHierarchy, hierarchyData, ColumnName);
    //
//                    if (!"STD".equals(ColumnName) && !"KPTCL".equals(ColumnName)) {
//                        newHierarchy.setStatus(status);
//                    }
//                    
//                    // Insert the new entry
//                    approvalHierarchyRepo.save(newHierarchy);
//                    return ResponseEntity.status(HttpStatus.SC_CREATED).body("Approval Hierarchy created successfully");
//                }
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
//                        .body("Error occurred: " + e.getMessage());
//            }
//        }
//    @GetMapping("/approval_hierarchy")
//    public ResponseEntity<?> getApprovalHierarchy(@RequestParam Long caseId, @RequestParam String columnName) {
//        try {
//            approval_hierarchy approvalHierarchy = approvalHierarchyRepo.findByHierarchy_CaseId(caseId);
//            if (approvalHierarchy == null) {
//                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND)
//                        .body("Approval Hierarchy with case_id " + caseId + " does not exist");
//            }
//
//            // Create a map to hold the response data with formatted dates
//            Map<String, Object> response = new HashMap<>();
//            response.put("caseId", approvalHierarchy.getHierarchy().getCase_id());
//            response.put("sanctionedLoad", approvalHierarchy.getSanctionedLoad());
//            response.put("category", approvalHierarchy.getCategory());
//            response.put("updatedBy", approvalHierarchy.getUPDATED_BY());
//            
//            response.put("createDate", DateUtil.formatDate(approvalHierarchy.getHierarchy().getCreateDate().getTime()));
//            response.put("updatedOn", DateUtil.formatDate(approvalHierarchy.getUpdatedOn().getTime()));
//
//            if(!"STD".equals(columnName) && !"KPTCL".equals(columnName)) {
//            	response.put("status", approvalHierarchy.getStatus());
//            }
//            // Fetch and include the specific fields based on the columnName
//            switch (columnName) {
//                case "STD":
//                    response.put("STD_status", approvalHierarchy.getSTD_status());
//                    response.put("STD_URL", approvalHierarchy.getSTD_URL());
//                    break;
//                case "KPTCL":
//                    response.put("KPTCL_status", approvalHierarchy.getKPTCL_status());
//                    response.put("KPTCL_URL", approvalHierarchy.getKPTCL_URL());
//                    break;
//                case "MD":
//                    response.put("MD_URL", approvalHierarchy.getMD_URL());
//                    break;
//                case "EE":
//                    response.put("EE_URL", approvalHierarchy.getEE_URL());
//                    response.put("EE_Remark", approvalHierarchy.getEE_Remark());
//                    break;
//                case "SE":
//                    response.put("SE_URL", approvalHierarchy.getSE_URL());
//                    response.put("SE_Remark", approvalHierarchy.getSE_Remark());
//                    break;
//                case "CE":
//                    response.put("CE_URL", approvalHierarchy.getCE_URL());
//                    response.put("CE_Remark", approvalHierarchy.getCE_Remark());
//                    break;
//                default:
//                    return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
//                            .body("Invalid column name: " + columnName);
//            }
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
//                    .body("Error occurred: " + e.getMessage());
//        }
//    }

//      @GetMapping("/approval_hierarchy")
//      public ResponseEntity<?> getApprovalHierarchy(@RequestParam Long caseId, @RequestParam String columnName) {
//          try {
//              approval_hierarchy approvalHierarchy = approvalHierarchyRepo.findByHierarchy_CaseId(caseId);
//              if (approvalHierarchy == null) {
//                  return ResponseEntity.status(HttpStatus.SC_NOT_FOUND)
//                          .body("Approval Hierarchy with case_id " + caseId + " does not exist");
//              }
//
//              Map<String, Object> response = method.buildResponseMap(approvalHierarchy);
//              method.addColumnSpecificData(response, approvalHierarchy, columnName);
//
//              return ResponseEntity.ok(response);
//          } catch (Exception e) {
//              return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
//                      .body("Error occurred: " + e.getMessage());
//          }
//      }
//   
   




    
    
	





