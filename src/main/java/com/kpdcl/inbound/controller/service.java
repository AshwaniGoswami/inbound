package com.kpdcl.inbound.controller;

import java.util.*;
import org.apache.hc.core5.http.HttpStatus;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.kpdcl.inbound.entity.approval_hierarchy;
import com.kpdcl.inbound.entity.dataHierarchy;
import com.kpdcl.inbound.entity.hierarchy;
import com.kpdcl.inbound.repository.approvalHierarchyRepository;
import com.kpdcl.inbound.repository.dataRepository;
import com.kpdcl.inbound.repository.hierarchyRepository;




    
@RestController
public class service {
	
	@Autowired
	private hierarchyRepository hierarchyRepo;
	
	@Autowired
	private approvalHierarchyRepository approvalHierarchyRepo;
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private dataRepository dataRepo;
    
	@PostMapping("/api/hierarchy")
    public ResponseEntity<?> createData(@RequestBody hierarchy jsonData) {
        try {
            // Extracting necessary information from jsonData

            String sanctionedLoadStr = jsonData.getSanctioned_load();
            double sanctionedLoad = extractNumericValue(sanctionedLoadStr);
            String office = jsonData.getOfficeCode();
           
            // Extracting the first two digits and the next two digits from the office code
            String wingCode = office.substring(0, 2);
            String circleCode = office.substring(2, 4);
            String divisionCode = office.substring(4, 6);
  
            // Check if circle code and division code are valid
            boolean circleCodeExists = circleCodeExists(circleCode);
            boolean divisionCodeExists = divisionCodeExists(divisionCode);

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

            if (sanctionedLoad >= 25.0 && sanctionedLoad <= 50.0) {
                // Fetch STD email
            	stdEmail = dataRepo.findSTDEmailByDivisionCode(divisionCode);
                divisionEmail = dataRepo.findDivisionEmailByDivisionCode(divisionCode);

                // Fetch division email
                sendEmail(stdEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(divisionEmail, "Approval Request", "Approval request for case ID: " , jsonData);
              Map<String, Object> responseBody = new HashMap<>();
              responseBody.put("stdEmail", stdEmail);
              responseBody.put("divisionEmail", divisionEmail);
           }
            
            else if (sanctionedLoad > 50.0 && sanctionedLoad <= 100.0) {
                
            	 stdEmail = dataRepo.findSTDEmailByDivisionCode(divisionCode);
                 divisionEmail = dataRepo.findDivisionEmailByDivisionCode(divisionCode);
                 circleEmail=dataRepo.findcircleEmailBycircleCode(divisionCode);
                 System.out.println(circleEmail);
                
	             sendEmail(divisionEmail, "Approval Request", "Approval request for case ID: " , jsonData);
	             sendEmail(stdEmail, "Approval Request", "Approval request for case ID: " , jsonData);
	             sendEmail(circleEmail, "Approval Request", "Approval request for case ID: " , jsonData);
	             
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

                sendEmail(divisionEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(stdEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(circleEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(wingEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                
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

                sendEmail(divisionEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(stdEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(circleEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(wingEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(mdEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                sendEmail(jkptclEmail, "Approval Request", "Approval request for case ID: " , jsonData);
                
                Map<String, Object> responseBody = new HashMap<>();
	              responseBody.put("stdEmail", stdEmail);
	              responseBody.put("divisionEmail", divisionEmail);
	              responseBody.put("divisionEmail", circleEmail);
	              responseBody.put("divisionEmail", wingEmail);
            }


            hierarchyRepo.save(jsonData);
            return ResponseEntity.status(HttpStatus.SC_CREATED).body("data send Successfully!!");

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
    		
    		// Check if the case_id exists in the hierarchy table
    		hierarchy existingHierarchy = hierarchyRepo.findByCaseId(caseId);
    		if (existingHierarchy == null) {
    			return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Hierarchy with case_id " + caseId + " does not exist");
    		}
    	
    		// Check if the case_id exists in the approval_hierarchy table
    		approval_hierarchy existingApprovalHierarchy = approvalHierarchyRepo.findByHierarchy_CaseId(caseId);
    	
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
    	}
    }


//================================methods====================================================================================s
    private void updateExistingHierarchy(approval_hierarchy existingHierarchy, approval_hierarchy newHierarchy) {
        // Update EE_URL, EE_status, and EE_Remark if provided
        if (newHierarchy.getEE_URL() != null || newHierarchy.isEE_status() || newHierarchy.getEE_Remark() != null) {
            existingHierarchy.setEE_URL(newHierarchy.getEE_URL());
            existingHierarchy.setEE_status(newHierarchy.isEE_status());
            existingHierarchy.setEE_Remark(newHierarchy.getEE_Remark());
        }
        // Update STD_URL, STD_status, and STD_Remark if provided
        if (newHierarchy.getSTD_URL() != null && newHierarchy.isSTD_status() || newHierarchy.getSTD_Remark() != null) {
            existingHierarchy.setSTD_URL(newHierarchy.getSTD_URL());
            existingHierarchy.setSTD_status(newHierarchy.isSTD_status());
            existingHierarchy.setSTD_Remark(newHierarchy.getSTD_Remark());
        }
        // Update SE_URL, SE_status, and SE_Remark if provided
        if (newHierarchy.getSE_URL() != null && newHierarchy.isSE_status() || newHierarchy.getSE_Remark() != null) {
            existingHierarchy.setSE_URL(newHierarchy.getSE_URL());
    existingHierarchy.setSE_status(newHierarchy.isSE_status());
            existingHierarchy.setSE_Remark(newHierarchy.getSE_Remark());
        }
        // Update CE_URL, CE_status, and CE_Remark if provided
        if (newHierarchy.getCE_URL() != null || newHierarchy.isCE_status() || newHierarchy.getCE_Remark() != null) {
            existingHierarchy.setCE_URL(newHierarchy.getCE_URL());
            existingHierarchy.setCE_status(newHierarchy.isCE_status());
            existingHierarchy.setCE_Remark(newHierarchy.getCE_Remark());
        }
        // Update KPTCL_URL, KPTCL_status, and KPTCL_Remark if provided
        if (newHierarchy.getKPTCL_URL() != null || newHierarchy.isKPTCL_status() || newHierarchy.getKPTCL_Remark() != null) {
            existingHierarchy.setKPTCL_URL(newHierarchy.getKPTCL_URL());
            existingHierarchy.setKPTCL_status(newHierarchy.isKPTCL_status());
            existingHierarchy.setKPTCL_Remark(newHierarchy.getKPTCL_Remark());
        }
        // Update MD_URL, MD_status, and MD_Remark if provided
        if (newHierarchy.getMD_URL() != null || newHierarchy.isMD_status() || newHierarchy.getMD_Remark() != null) {
            existingHierarchy.setMD_URL(newHierarchy.getMD_URL());
            existingHierarchy.setMD_status(newHierarchy.isMD_status());
            existingHierarchy.setMD_Remark(newHierarchy.getMD_Remark());
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
    

    public void sendEmail(String to, String subject, String text, hierarchy jsonData) {
        if (to == null || to.isEmpty() || subject == null || subject.isEmpty() || text == null || text.isEmpty() || jsonData == null) {
            throw new IllegalArgumentException("Invalid email parameters.");
        }

        StringBuilder emailBody = new StringBuilder();
        emailBody.append(text).append("\n");
        emailBody.append("Case ID: ").append(jsonData.getCase_id()).append("\n");
        emailBody.append("Name of Applicant: ").append(jsonData.getName_of_applicant()).append("\n");
        emailBody.append("Sanctioned Load: ").append(jsonData.getSanctioned_load()).append("\n");
        emailBody.append("Category: ").append(jsonData.getCategory()).append("\n");
        emailBody.append("Description: ").append(jsonData.getDescription()).append("\n");
        emailBody.append("Approval URL: ").append("https://aws.amazon.com/").append("\n");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(emailBody.toString());
        emailSender.send(message);
    }
}

    
    
	





