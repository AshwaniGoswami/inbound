//package com.kpdcl.inbound.controller;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.kpdcl.inbound.token.createToken;
//
//@RestController
//public class service {
//
//    //@Autowired
//    //private createToken tokenGenerator;
//
//    @PostMapping("/api/data")
//    public ResponseEntity<String> createData(@RequestBody Map<String, Object> jsonData) {
//        try {
//        	//System.out.println(jsonData);
//            // Generate a JWT token using the JSON data
//            //String token = tokenGenerator.generateToken(jsonData);
//            //System.out.println(token);
//            // Return the token along with a success status
//            return new ResponseEntity<>(token, HttpStatus.CREATED);
//        } catch (Exception e) {
//            // If there is an error, return an appropriate HTTP status code
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}


//package com.kpdcl.inbound.controller;
//
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//@RestController
//public class service {
//	   @PostMapping("/api/data")
//	    public ResponseEntity<Map<String, Object>> createData(@RequestBody Map<String, Object> jsonData) {
//	        try {
//	            // You can directly return the received JSON data
//	            return new ResponseEntity<>(jsonData, HttpStatus.CREATED);
//	        } catch (Exception e) {
//	        	
//	            // If there is an error, return an appropriate HTTP status code
//	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//	        }
//	    }
//}


package com.kpdcl.inbound.controller;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kpdcl.inbound.entity.Approval;
//import com.billsahuliyatbackendspring.entity.UserRequest;
import com.kpdcl.inbound.entity.approval_hierarchy;
import com.kpdcl.inbound.entity.dataHierarchy;
import com.kpdcl.inbound.entity.hierarchy;
//import com.Utilities_Backend.entity.receivingStation;
//import com.kpdcl.inbound.entity.hierarchy;
import com.kpdcl.inbound.repository.approvalHierarchyRepository;
import com.kpdcl.inbound.repository.approvalRepository;
import com.kpdcl.inbound.repository.dataRepository;
import com.kpdcl.inbound.repository.hierarchyRepository;




    
//    @Autowired
//    public service(YourService yourService) {
//        this.yourService = yourService;
//    }
//    @Autowired
//    public service(createToken createtoken, EmailService emailService) {
//        this.createtoken = createtoken;
//        this.emailService = emailService;
//    }

    
//    public service(EmailService emailService) {
////        this.createtoken = createtoken;
//        this.emailService = emailService;
//    }
//    
//    @PostMapping("/api/data")
//    public ResponseEntity<ResponseObject> createData(@RequestBody Map<String, Object> jsonData) {
//        try {
//          
//            hierarchy hierarchy1 = parseHierarchy(jsonData); // Implement this method
//            System.out.println(hierarchy1);
//            // Save the entity to the database
//            hierarchy savedHierarchy = hierarchyRepo.save(hierarchy1);
//
//            // Generate token using TokenService
//            String token = createtoken.generateToken(jsonData);
//            
//                            
//            // Create a response object including the token and any other necessary data
//            ResponseObject responseObject = new ResponseObject();
//            responseObject.setToken(token);
//            responseObject.setData(savedHierarchy); // Assuming you want to return the saved hierarchy
//                      
//            // Send email(s)
//            String emailSubject = "Your Subject Here";
//            String emailBody = "Your Email Body Here";
//    
//            // Assuming jsonData contains a list of emails under key "emails"
//            if (jsonData.containsKey("emails") && jsonData.get("emails") instanceof List<?>) {
//                @SuppressWarnings("unchecked")
//                List<String> recipientEmails = (List<String>) jsonData.get("emails");
//                emailService.sendBulkEmail(recipientEmails, emailSubject, emailBody);
//            } else {
//                // If a single email address is provided
//                if (jsonData.containsKey("email") && jsonData.get("email") instanceof String) {
//                    String recipientEmail = (String) jsonData.get("email");
//                    emailService.sendEmail(recipientEmail, emailSubject, emailBody);
//                }
//            }
//    
//            // Return the response with HTTP status 201 (CREATED)
//            return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
//        } catch (Exception e) {
//            // If there is an error, return an appropriate HTTP status code
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//  ----------------------------------------------------------------------------------------------------------------------------  
//    @PostMapping("/api/data")
//    public ResponseEntity<?> createData(@RequestBody hierarchy jsonData) {
//        try {
//            // Extract data from the incoming JSON object
//        	Long caseId=jsonData.getCase_id();
//            Optional<hierarchy> existingHierarchyOptional = hierarchyRepo.findById(caseId);
//            if (existingHierarchyOptional.isPresent()) {
//                // Case ID already exists, return a conflict response
//                 return ResponseEntity.status(400).body("Case ID already exists.");
//            }
//            
//            String nameOfApplicant = jsonData.getName_of_applicant();
//            String load = jsonData.getSanctidet_load();
//            String category = jsonData.getCategory();
//            String description = jsonData.getDescription();
////            String statusRemark = jsonData.getStatus_remark();
////            String url = jsonData.getURL();
////            boolean status = jsonData.isStatus();
//
//            // Create a new instance of the Hierarchy entity
//            hierarchy newHierarchy = new hierarchy();
//            newHierarchy.setCase_id(caseId);
//            newHierarchy.setName_of_applicant(nameOfApplicant);
//            newHierarchy.setSanctidet_load(load);
//            newHierarchy.setCategory(category);
//            newHierarchy.setDescription(description);
////            newHierarchy.setStatus_remark(statusRemark);
////            newHierarchy.setURL(url);
////            newHierarchy.setStatus(status);
//
//            // Save the entity to the database
//            hierarchy savedHierarchy = hierarchyRepo.save(newHierarchy);
//
//            // Generate token using TokenService
////            String token = createtoken.generateToken(newHierarchy);
////            System.out.println(token);
//            // Create a response object including the token and any other necessary data
////            ResponseObject responseObject = new ResponseObject();
////            responseObject.setToken(token);
////            responseObject.setData(savedHierarchy);
//
//
//            // Return the response with HTTP status 201 (CREATED)
//            return ResponseEntity.ok().body(savedHierarchy);
//        } catch (Exception e) {
//            // If there is an error, return an appropriate HTTP status code
//        	return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }-------------------------------------------------------------------------------------------------------------------------------

    
    // Implement this method to parse data from the x file and create 'hierarchy' entity
//    private hierarchy parseHierarchy(Map<String, Object> jsonData) {
//        hierarchy hierarchy = new hierarchy();
//        hierarchy.setName_of_applicant((String) jsonData.get("Name_of_applicant"));
//        hierarchy.setSanctidet_load((String) jsonData.get("sanctidet_load"));
//        hierarchy.setCategory((String) jsonData.get("Category"));
//        hierarchy.setDescription((String) jsonData.get("description"));
//        hierarchy.setStatus((boolean) jsonData.get("status"));
//        hierarchy.setStatus_remark((String) jsonData.get("Status_remark"));
//        hierarchy.setURL((String) jsonData.get("URL"));
//        return hierarchy;
//    }

    
    
    // Define a ResponseObject class to include token and data
//    private static class ResponseObject {
//        private String token;
//        private hierarchy data;
//    
//        // Getter and setter methods
//        public String getToken() {
//            return token;
//        }
//    
//        public void setToken(String token) {
//            this.token = token;
//        }
//    
//        public hierarchy getData() {
//            return data;
//        }
//    
//        public void setData(hierarchy savedHierarchy) {
//            this.data = savedHierarchy;
//        }
//    }
    
    
//    @PostMapping("/api/hierarchy")
//    public ResponseEntity<?> createData(@RequestBody hierarchy jsonData) {
//        try {
//        	Long caseId=jsonData.getCase_id();
//            hierarchy existingHierarchyOptional = hierarchyRepo.findByCaseId(caseId);
//            if (existingHierarchyOptional!=null) {
//                // Case ID already exists, return a conflict response
//                 return ResponseEntity.status(400).body("Case ID already exists.");
//            }
//            // Save the entity to the database
//            hierarchy savedHierarchy = hierarchyRepo.save(jsonData);
//
//// Return the response with HTTP status 201 (CREATED)
//            return ResponseEntity.ok().body(savedHierarchy);
//        } catch (Exception e) {
//            // If there is an error, return an appropriate HTTP status code
//        	return ResponseEntity.status(500).body("Internal Server Error");
//        }
//    }
//    
//    @PostMapping("/api/approval_hierarchy")
//    public ResponseEntity<?> createApprovalHierarchy(@RequestBody approval_hierarchy approvalHierarchyData) {
//        try {
//            // Extract case_id from the hierarchy object
//            Long caseId = approvalHierarchyData.getHierarchy().getCase_id();
//
//            // Find hierarchy entity by case_id
//            hierarchy hierarchy1 = hierarchyRepo.findByCaseId(caseId);
//            if (hierarchy1 == null) {
//                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Hierarchy with given case_id not found.");
//            }
//
//            // Set the found hierarchy object in the approval hierarchy data
//            approvalHierarchyData.setHierarchy(hierarchy1);
//
//            // Save the approval hierarchy entity to the database
//            approval_hierarchy savedApprovalHierarchy = approvalHierarchyRepo.save(approvalHierarchyData);
//
//            // Return the response with HTTP status 201 (CREATED)
//            return ResponseEntity.status(HttpStatus.SC_CREATED).body(savedApprovalHierarchy);
//        } catch (Exception e) {
//            // If there is an error, return an appropriate HTTP status code and message
//            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
//        }
//    }
    
 
    
    
    
    
//    @PostMapping("/api/hierarchy")
//    public ResponseEntity<?> createData(@RequestBody hierarchy jsonData) {
//        try {
//
//            Long caseId = jsonData.getCase_id();
//            String category = jsonData.getCategory();
//            String sanctionedLoadStr = jsonData.getSanctioned_load();
//            double sanctionedLoad = extractNumericValue(sanctionedLoadStr);
//      
//          hierarchy existingHierarchyOptional = hierarchyRepo.findByCaseId(caseId);
//          if (existingHierarchyOptional!=null) {
//              // Case ID already exists, return a conflict response
//               return ResponseEntity.status(400).body("Case ID already exists.");
//          }
//
//            // Check if the category is domestic or commercial
//            if (!category.equalsIgnoreCase("domestic") && !category.equalsIgnoreCase("commercial")) {
//                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Invalid category. Must be domestic or commercial.");
//            }
//            // Check the sanctioned load range
//            if (sanctionedLoad >= 25.0 && sanctionedLoad <= 50.0) { // Compare as double or float
//                // Send email to STD (mandatory)
//                sendEmail("varpit340@gmail.com", "Approval Request", "Approval request for case ID: " , jsonData);
//
//                // Send email to EE (mandatory)
//                sendEmail("thesecret8936@gmail.com", "Approval Request", "Approval request for case ID: " , jsonData);
//                hierarchy savedHierarchy = hierarchyRepo.save(jsonData);
//
//                // After approval from STD and EE and document upload
//                // Save URLs and update statuses in approve_hierarchy entity
//                // For simplicity, let's assume URLs are retrieved from jsonData
////                String stdUrl = jsonData.getStdUrl();
////                String eeUrl = jsonData.getEeUrl();
////                
////                // Update statuses
////                jsonData.setStdStatus("Approved");
////                jsonData.setEeStatus("Approved");
//
//                // Save the entity to the database
//
//                // Return the response with HTTP status 201 (CREATED)
//                return ResponseEntity.status(HttpStatus.SC_CREATED).body(savedHierarchy);
//            }
//            else if (sanctionedLoad > 50.0 && sanctionedLoad <= 100.0) { // Compare as double or float
//            	hierarchy savedHierarchy = hierarchyRepo.save(jsonData);
//                // Send email to STD (mandatory)
//                sendEmail("varpit340@gmail.com", "Approval Request", "Approval request for case ID: " , savedHierarchy);
//
//                // Send email to EE (mandatory)
//                sendEmail("thesecret8936@gmail.com", "Approval Request", "Approval request for case ID: " , savedHierarchy);
//                
//                sendEmail("ashwanigoswami@higher.co.in", "Approval Request", "Approval request for case ID: " , savedHierarchy);
//                
//                return ResponseEntity.status(HttpStatus.SC_CREATED).body(savedHierarchy);
//            }
//            
//            else if (sanctionedLoad > 100.0 && sanctionedLoad <= 500.0) { // Compare as double or float
//            	hierarchy savedHierarchy = hierarchyRepo.save(jsonData);
//                // Send email to STD (mandatory)
//                sendEmail("varpit340@gmail.com", "Approval Request", "Approval request for case ID: " , savedHierarchy);
//
//                // Send email to EE (mandatory)
//                sendEmail("thesecret8936@gmail.com", "Approval Request", "Approval request for case ID: " , savedHierarchy);
//                
//                sendEmail("ashwanigoswami@gmail.com", "Approval Request", "Approval request for case ID: " , savedHierarchy);
//                
//                sendEmail("kungayounthen21@gmail.com", "Approval Request", "Approval request for case ID: " , savedHierarchy);
//                
//                return ResponseEntity.status(HttpStatus.SC_CREATED).body(savedHierarchy);
//            }
//            
//            else if (sanctionedLoad > 500.0) { // Compare as double or float
//                // Send email to STD (mandatory)
//            	hierarchy savedHierarchy = hierarchyRepo.save(jsonData);
//                sendEmail("varpit340@gmail.com", "Approval Request", "Approval Data: " , savedHierarchy);
//
//                // Send email to EE (mandatory)
//                sendEmail("thesecret8936@gmail.com", "Approval Request", "Approval Data: " , savedHierarchy);
//                
//                sendEmail("ashwanigoswami@gmail.com", "Approval Request", "Approval Data: " , savedHierarchy);
//                
//                sendEmail("kungayounthen21@gmail.com", "Approval Request", "Approval Data: " ,savedHierarchy);
//                
//                sendEmail("nehapanwar540@gmail.com", "Approval Request", "Approval Data: " , savedHierarchy);
//                
//                return ResponseEntity.status(HttpStatus.SC_CREATED).body(savedHierarchy);
//            }
//                // If the sanctioned load does not fall within the required range,
//                // return a bad request response
//            return ResponseEntity.status(HttpStatus.SC_CREATED).body("created");
//        } catch (NumberFormatException e) {
//            // If the sanctioned load cannot be parsed as a double, return a bad request response
//            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Invalid sanctioned load. Must be a numeric value.");
//        } catch (Exception e) {
//            // If there is an error, return an appropriate HTTP status code
//            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Internal Server Error111");
//        }
//    }
    
    
@RestController
public class service {
	//private final createToken createtoken;
	// private final EmailService emailService;
	
	@Autowired
	private hierarchyRepository hierarchyRepo;
	
	@Autowired
	private approvalHierarchyRepository approvalHierarchyRepo;
	
	@Autowired
	private JavaMailSender emailSender;
	
//    @Autowired
//    private divisionEmailRepository divisionEmailRepo;
	@Autowired
	private approvalRepository approvalRepo;
	@Autowired
	private dataRepository dataRepo;
    
	@PostMapping("/api/hierarchy")
    public ResponseEntity<?> createData(@RequestBody hierarchy jsonData) {
        try {
            // Extracting necessary information from jsonData
            Long caseId = jsonData.getCase_id();
            String category = jsonData.getCategory();
            String sanctionedLoadStr = jsonData.getSanctioned_load();
            double sanctionedLoad = extractNumericValue(sanctionedLoadStr);
            String description = jsonData.getDescription();
            String applicant = jsonData.getName_of_applicant();
            String division = jsonData.getDivision();
            String office = jsonData.getOfficeCode();
           
            // Extracting the first two digits and the next two digits from the office code
            String wingCode = office.substring(0, 2);
            String circleCode = office.substring(2, 4);
            String divisionCode = office.substring(4, 6);
            System.out.println(circleCode);
            System.out.println(divisionCode);
            // Check if circle code and division code are valid
            boolean circleCodeExists = circleCodeExists(circleCode);
            boolean divisionCodeExists = divisionCodeExists(divisionCode);
            System.out.println(circleCodeExists);
            System.out.println(divisionCodeExists);
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
            System.out.println("i an here");

            if (sanctionedLoad >= 25.0 && sanctionedLoad <= 50.0) {
                // Fetch STD email
            	   stdEmail = dataRepo.findSTDEmailByDivisionCode(divisionCode);
                   divisionEmail = dataRepo.findDivisionEmailByDivisionCode(divisionCode);

//                stdEmail = dataRepo.findSTDEmailByDivisionCode(divisionCode);
//                System.out.println(stdEmail);
                // Fetch division email
            	System.out.println("i am here");
//                divisionEmail = dataRepo.findDivisionEmailByDivisionCode(divisionCode);
                sendEmail(stdEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                
//                                // Send email to EE (mandatory)
                sendEmail(divisionEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                                //hierarchy savedHierarchy = hierarchyRepo.save(jsonData);
//                System.out.println("i am here1");
                System.out.println(divisionEmail);
                
            }
            
            else if (sanctionedLoad > 50.0 && sanctionedLoad <= 100.0) {
                // Fetch STD email
//            	System.out.println("i am here");
            	 stdEmail = dataRepo.findSTDEmailByDivisionCode(divisionCode);
                 divisionEmail = dataRepo.findDivisionEmailByDivisionCode(divisionCode);
                 circleEmail=dataRepo.findcircleEmailBycircleCode(circleCode);
//            	stdEmail = dataRepo.findSTDEmailByDivisionCode(divisionCode);
//            	divisionEmail = dataRepo.findDivisionEmailByDivisionCode(divisionCode);
//                circleEmail=dataRepo.findcircleEmailByCircleCode(circleCode);
//                System.out.println(stdEmail);
//                System.out.println(divisionEmail);
//                System.out.println(circleEmail);
                // Fetch division email
//            	System.out.println("i am here");
                sendEmail(divisionEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(stdEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(circleEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                //
//                                // Send email to EE (mandatory)
                                //hierarchy savedHierarchy = hierarchyRepo.save(jsonData);
//                System.out.println("i am here1");
                //System.out.println(divisionEmail);
            }
            else if (sanctionedLoad > 100.0 && sanctionedLoad <= 500.0) {
                // Fetch STD email
                stdEmail = dataRepo.findSTDEmailByDivisionCode(divisionCode);
                divisionEmail = dataRepo.findDivisionEmailByDivisionCode(divisionCode);
                //circleEmail=dataRepo.findcircleEmailBycircleCode(circleCode);
                wingEmail=dataRepo.findwingEmailBywingCode(wingCode);
//            	divisionEmail = dataRepo.findDivisionEmailByDivisionCode(divisionCode);
//                stdEmail = dataRepo.findSTDEmailByDivisionCode(divisionCode);
//                circleEmail=dataRepo.findcircleEmailByCircleCode(circleCode);
//                wingEmail=dataRepo.findwingEmailBywingCode(wingCode);
//                System.out.println(stdEmail);
                // Fetch division email
//            	System.out.println("i am here");
                sendEmail(divisionEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(stdEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(circleEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(wingEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                //
//                                // Send email to EE (mandatory)
                                //hierarchy savedHierarchy = hierarchyRepo.save(jsonData);
//                System.out.println("i am here1");
                //System.out.println(divisionEmail);
            }

            // Formulate the response body
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("stdEmail", stdEmail);
            responseBody.put("divisionEmail", divisionEmail);

            hierarchyRepo.save(jsonData);
            return ResponseEntity.status(HttpStatus.SC_CREATED).body(responseBody);

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
	        return ResponseEntity.ok().body(hierarchyList);
	    } catch (Exception e) {
	        // If there is an error, return an appropriate HTTP status code
	        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Internal Server Error");
	    }
	}



    @PostMapping("createData")
    public ResponseEntity<?> createApproval(@RequestBody Approval approval) {
    	Approval savedHierarchy = approvalRepo.save(approval);
    	return ResponseEntity.status(HttpStatus.SC_CREATED).body(savedHierarchy);
    }
    
    @PostMapping("data_hierarchy")
    public ResponseEntity<dataHierarchy> createDataHierarchy(@RequestBody dataHierarchy dataHierarchy) {
        dataHierarchy savedDataHierarchy = dataRepo.save(dataHierarchy);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(savedDataHierarchy);
    }

    
    @PostMapping("/approval-hierarchy")
    public ResponseEntity<String> createOrUpdateApprovalHierarchy(@RequestBody approval_hierarchy hierarchy1) {
    	try {
    		Long caseId = hierarchy1.getHierarchy().getCase_id();
    		String url = hierarchy1.getEE_URL();
    		boolean status = hierarchy1.isEE_status();
    		
    		// Check if the case_id exists in the hierarchy table
    		hierarchy existingHierarchy = hierarchyRepo.findByCaseId(caseId);
    		if (existingHierarchy == null) {
    			return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Hierarchy with case_id " + caseId + " does not exist");
    		}
    		System.out.println("it is here");
    		// Check if the case_id exists in the approval_hierarchy table
    		approval_hierarchy existingApprovalHierarchy = approvalHierarchyRepo.findByHierarchy_CaseId(caseId);
    		System.out.println("it is here1");
    		if (existingApprovalHierarchy == null) {
    			System.out.println("it is null");
    		}
    		if (existingApprovalHierarchy != null) {
    			// Update the existing entry
    			updateExistingHierarchy(existingApprovalHierarchy, hierarchy1);
    			approvalHierarchyRepo.save(existingApprovalHierarchy);
    			return ResponseEntity.ok("Approval Hierarchy updated successfully");
    		} else {
    			// Set the hierarchy in the approval_hierarchy object
    			hierarchy1.setHierarchy(existingHierarchy);
    			
    			// Insert the new entry
    			approval_hierarchy savedApprovalHierarchy = approvalHierarchyRepo.save(hierarchy1);
    			if (savedApprovalHierarchy != null) {
    				return ResponseEntity.status(HttpStatus.SC_CREATED).body("Approval Hierarchy created successfully");
    			} else {
    				return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to save Approval Hierarchy");
    			}
    		}
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
//        	return ResponseEntity.status(500).body("internal server error ");
    	}
    }
//    @PostMapping("/approval-hierarchy")
//    public ResponseEntity<String> createOrUpdateApprovalHierarchy(@RequestBody approval_hierarchy hierarchy1) {
//        try {
//            System.out.println("I am here");
//            Long caseId = hierarchy1.getHierarchy().getCase_id();
//            String url = hierarchy1.getEE_URL();
//            boolean status = hierarchy1.isEE_status();
//            System.out.println(caseId);
//            System.out.println(url);
//            System.out.println(status);
//            hierarchy hierarchyObj = hierarchy1.getHierarchy();
//            if (hierarchyObj == null || hierarchyObj.getCase_id() == null) {
//                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Hierarchy or case_id is null");
//            }
//
//            // Check if the entity with the given case_id exists
//            approval_hierarchy existingHierarchy = approvalHierarchyRepo.findByHierarchy_CaseId(hierarchyObj.getCase_id());
//            
//            if (existingHierarchy != null) {
//                // Update the existing entity
//                updateExistingHierarchy(existingHierarchy, hierarchy1);
//                approvalHierarchyRepo.save(existingHierarchy);
//                return ResponseEntity.ok("Approval Hierarchy updated successfully");
//            } else {
//                // Save the associated hierarchy entity first
//                // Save hierarchy entity
////                hierarchy savedHierarchy = hierarchyRepo.save(hierarchyObj);
//                
//                // Set the saved hierarchy entity back to the approval_hierarchy object
////                hierarchy1.setHierarchy(savedHierarchy);
//                
//                // Insert the new entity
//                approval_hierarchy savedApprovalHierarchy = approvalHierarchyRepo.save(hierarchy1);
//                if (savedApprovalHierarchy != null) {
//                    return ResponseEntity.status(HttpStatus.SC_CREATED).body("Approval Hierarchy created successfully");
//                } else {
//                    return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Failed to save Approval Hierarchy");
//                }
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
//        }
//    }


    
 //================================methods====================================================================================s
    private void updateExistingHierarchy(approval_hierarchy existingHierarchy, approval_hierarchy newHierarchy) {
        // Update EE_URL and EE_status if provided
        if (newHierarchy.getEE_URL() != null || newHierarchy.isEE_status()) {
            existingHierarchy.setEE_URL(newHierarchy.getEE_URL());
            existingHierarchy.setEE_status(newHierarchy.isEE_status());
        }
        // Update STD_URL and STD_status if provided
        if (newHierarchy.getSTD_URL() != null && newHierarchy.isSTD_status()) {
            existingHierarchy.setSTD_URL(newHierarchy.getSTD_URL());
            existingHierarchy.setSTD_status(newHierarchy.isSTD_status());
        }
        // Update SE_URL and SE_status if provided
        if (newHierarchy.getSE_URL() != null && newHierarchy.isSE_status()) {
            existingHierarchy.setSE_URL(newHierarchy.getSE_URL());
            existingHierarchy.setSE_status(newHierarchy.isSE_status());
        }
        // Update CE_URL and CE_status if provided
        if (newHierarchy.getCE_URL() != null || newHierarchy.isCE_status()) {
            existingHierarchy.setCE_URL(newHierarchy.getCE_URL());
            existingHierarchy.setCE_status(newHierarchy.isCE_status());
        }
        // Update KPTCL_URL and KPTCL_status if provided
        if (newHierarchy.getKPTCL_URL() != null || newHierarchy.isKPTCL_status()) {
            existingHierarchy.setKPTCL_URL(newHierarchy.getKPTCL_URL());
            existingHierarchy.setKPTCL_status(newHierarchy.isKPTCL_status());
        }
        // Update MD_URL and MD_status if provided
        if (newHierarchy.getMD_URL() != null || newHierarchy.isMD_status()) {
            existingHierarchy.setMD_URL(newHierarchy.getMD_URL());
            existingHierarchy.setMD_status(newHierarchy.isMD_status());
        }
    }
    // Method to check if circle code exists
    private boolean circleCodeExists(String circle_code) {
    	return dataRepo.existsBycircle_code(circle_code);
    }
    
    // Method to check if division code exists
    private boolean divisionCodeExists(String division_code) {
    	return dataRepo.existsByDivision_code(division_code);
    }
    private double extractNumericValue(String str) {
    	String numericPart = str.replaceAll("[^\\d.]", "");
    	return Double.parseDouble(numericPart);
    }
    // Method to send email
    private void sendEmail(String to, String subject,String text,  hierarchy jsonData) {
    	SimpleMailMessage message = new SimpleMailMessage();
    	message.setTo(to);
    	message.setSubject(subject);
    	
    	// Include specific user data in the email body
    	String emailBody =text+  "\n";
    	emailBody += "Case ID: " + jsonData.getCase_id() + "\n";
    	emailBody += "Name of Applicant: " + jsonData.getName_of_applicant() + "\n";
    	emailBody += "Sanctioned Load: " + jsonData.getSanctioned_load() + "\n";
    	emailBody += "Category: " + jsonData.getCategory() + "\n";
    	emailBody += "Description: " + jsonData.getDescription() + "\n";
    	
    	message.setText(emailBody);
    	emailSender.send(message);
    } 
    
}

    
    
	





