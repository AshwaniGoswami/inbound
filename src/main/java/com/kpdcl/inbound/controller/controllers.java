package com.kpdcl.inbound.controller;



import java.util.UUID;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpdcl.inbound.entity.APPROVAL_CONNECTION;
//import com.billsahuliyatbackendspring.entity.UserRequest;
//import com.billsahuliyatbackendspring.repository.UserRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.billsahuliyatbackendspring.entity.UserRequest;
//import com.billsahuliyatbackendspring.repository.UserRepository;
//import com.billsahuliyatbackendspring.entity.UserRequest;
//import com.billsahuliyatbackendspring.repository.UserRepository;
import com.kpdcl.inbound.entity.NEW_CONNECTION;
import com.kpdcl.inbound.entity.UserRequest;
//import com.kpdcl.inbound.entity.UserRequest;
import com.kpdcl.inbound.repository.newConnectionRepository;
import com.kpdcl.inbound.repository.UserRepository;
//import com.kpdcl.inbound.repository.UserRepository;
import com.kpdcl.inbound.repository.approvalConnectionRepository;
import com.kpdcl.inbound.service.EmailService;
import com.kpdcl.inbound.service.SMSService;
//import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/")
public class controllers {

	@Autowired
	private newConnectionRepository connectionRepo;
	
	@Autowired
    private approvalConnectionRepository approvalConnectionRepository;
	
	 private final EmailService emailService;
	 private final SMSService smsService;
	 
	  //@Autowired
	    public controllers(EmailService emailService, SMSService smsService) {
	        this.emailService = emailService;
	        this.smsService = smsService;
	    }
	  
	  @Autowired
	    private UserRepository userRepository;
	  

	  
//	  @PostMapping("/connections")
//	  public ResponseEntity<String> createConnection(@RequestBody NEW_CONNECTION connection) {
//		  
//		  Long caseId=connection.getCase_id();
//	      // Save the connection details to the database
//	      connectionRepo.save(connection);
//
//	      // Calculate total amount
//	      long totalAmount = connection.getDsa_amount() + connection.getMsa_amount();
//
//	      // Send email with total amount
//	      String Subject = "Payment";
//	      String URL="http://172.18.2.96:3000/ncp/" +caseId;
//	      String message = "Payment demand has been initiated by KPDCL for the total amount: " + totalAmount + "\n" + URL;
//	      emailService.sendEmail(connection.getEmail_id(), Subject, message);
//
//	      // Send SMS
//	      smsService.sendSMS(connection.getMobile_number(), message); // Replace "SMS Message" with your actual message
//	      
//	      return ResponseEntity.status(HttpStatus.SC_CREATED).body("Successful");
//	  }
	  
	  
//	  @PostMapping("/connections")
//	  public ResponseEntity<String> createConnection(@RequestBody NEW_CONNECTION connection) {
//	      
//	      Long caseId = connection.getCase_id();
//	      
//	      NEW_CONNECTION existingCaseId=connectionRepo.findByCaseId(caseId);
//	      if (existingCaseId != null) {
//  			return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("caseID already exist");
//  		}
//	      // Save the connection details to the database
//	      connectionRepo.save(connection);
//
//	      // Calculate total amount
//	      long totalAmount = connection.getDsa_amount() + connection.getMsa_amount();
//
//	      // Prepare URL
//	      String URL = "http://172.18.2.96:3000/ncp/" + caseId;
//
//	      // Prepare email message with styled text link
//	      String Subject = "Payment";
//	      String message = "Payment demand has been initiated by KPDCL for the total amount: " + totalAmount + "\n" +
//	              "To make the payment, click the following link: " + URL;
//
//	      // Send email with styled text link
//	      emailService.sendEmail(connection.getEmail_id(), Subject, message);
//
//	      // Send SMS
//	      String smsMessage = "Payment demand has been initiated by KPDCL for the total amount: " + totalAmount + "\n" + URL;
//	      smsService.sendSMS(connection.getMobile_number(), smsMessage);
//
//	      return ResponseEntity.status(HttpStatus.SC_CREATED).body("Successful");
//	  }

	  @PostMapping("/connections")
	    public ResponseEntity<String> createConnection(@RequestBody NEW_CONNECTION connection) {
	        // Fetch user details by user_id
		  
		  	UUID user_id=connection.getUser_id();
	        UserRequest user = userRepository.findByUserId(user_id);
	        if (user == null) {
	            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("User not found");
	        }
	        String email=user.getEmail();
	        String mobile=user.getMobile();
	        Long caseId=connection.getCase_id();
	        Long Dsa=connection.getDsa_amount();
	        Long Msa=connection.getMsa_amount();
	        
	       
	        NEW_CONNECTION connection1=new NEW_CONNECTION();
	        connection1.setEmail_id(email);
	        connection1.setMobile_number(mobile);
	        connection1.setCase_id(caseId);
	        connection1.setDsa_amount(Dsa);
	        connection1.setMsa_amount(Msa);
	        connection1.setUser_id(user_id);
	        

	        // Check if case_id already exists
	        NEW_CONNECTION existingCaseId = connectionRepo.findByCaseId(connection.getCase_id());
	        if (existingCaseId != null) {
	            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Case ID already exists");
	        }


	        // Calculate total amount
	        long totalAmount = connection.getDsa_amount() + connection.getMsa_amount();

	        // Prepare URL
	        String URL = "http://172.18.2.96:3000/ncp/" + connection.getCase_id();

	        // Prepare email message with styled text link
	        String subject = "Payment";
	        String message = "Payment demand has been initiated by KPDCL for the total amount: " + totalAmount + "\n" +
	                "To make the payment, click the following link: " + URL;

	        // Send email with styled text link
	        emailService.sendEmail(email, subject, message);

	        // Send SMS
	        String smsMessage = "Payment demand has been initiated by KPDCL for the total amount: " + totalAmount + "\n" + URL;
	        smsService.sendSMS(mobile, smsMessage);

	        // Save the new connection details
	        connectionRepo.save(connection1);
	        return ResponseEntity.status(HttpStatus.SC_CREATED).body("Successful");
	    }
  
	  
	   @PostMapping("/approval-connections")
	    public ResponseEntity<String> createApprovalConnection(@RequestBody APPROVAL_CONNECTION approvalConnection) {
	        // Save the approval connection details to the database
	        approvalConnectionRepository.save(approvalConnection);

	        // Respond with success message
	        return ResponseEntity.status(HttpStatus.SC_CREATED).body("Approval Connection created successfully");
	    }
	   
	   
}