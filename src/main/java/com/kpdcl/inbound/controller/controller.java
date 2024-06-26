package com.kpdcl.inbound.controller;

import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.billsahuliyatbackendspring.entity.UserRequest;
//import com.billsahuliyatbackendspring.repository.UserRepository;
//import com.billsahuliyatbackendspring.entity.UserRequest;
//import com.billsahuliyatbackendspring.repository.UserRepository;
import com.kpdcl.inbound.entity.NEW_CONNECTION;
import com.kpdcl.inbound.repository.newConnectionRepository;
import com.kpdcl.inbound.service.EmailService;
import com.kpdcl.inbound.service.SMSService;


@RestController
@RequestMapping("/")
public class controller {

	@Autowired
	private newConnectionRepository connectionRepo;
	
	 private final EmailService emailService;
	 private final SMSService smsService;
	 
	  @Autowired
	    public controller(EmailService emailService, SMSService smsService) {
	        this.emailService = emailService;
	        this.smsService = smsService;
	    }
	  
//	  @Autowired
//	    private UserRepository userRepository;
	  
	  
	  @PostMapping("/connections")
	  public ResponseEntity<String> createConnection(@RequestBody NEW_CONNECTION connection) {
		  
		  Long caseId=connection.getCase_id();
	      // Save the connection details to the database
	      connectionRepo.save(connection);

	      // Calculate total amount
	      long totalAmount = connection.getDsa_amount() + connection.getMsa_amount();

	      // Send email with total amount
	      String Subject = "Payment";
	      String URL="http://172.18.2.96:3000/ncp/" +caseId;
	      String message = "Payment demand has been initiated by KPDCL for the total amount: " + totalAmount + "\n" + URL;
	      emailService.sendEmail(connection.getEmail_id(), Subject, message);

	      // Send SMS
	      smsService.sendSMS(connection.getMobile_number(), message); // Replace "SMS Message" with your actual message
	      
	      return ResponseEntity.status(HttpStatus.SC_CREATED).body("Successful");
	  }
//	  
//	  @PostMapping("/connections")
//	  public ResponseEntity<String> createConnection(@RequestBody NEW_CONNECTION connection) {
//		  System.out.println(connection);
//	      // Fetch user details from UserRe`quest entity based on user_id
//	      Optional<UserRequest> userRequestOptional = userRepository.findById(connection.getUser_id());
//	      System.out.println(userRequestOptional.isPresent());
//	      if(!userRequestOptional.isPresent()) {
//	          return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("User not found");
//	      }
//
//	      UserRequest userRequest = userRequestOptional.get();
//	      
//	      // Proceed with saving connection and sending email/SMS
//	      String email = userRequest.getEmail();
//	      String mobileNumber = userRequest.getMobile();
//
//	      // Save the connection details to the database
//	      connectionRepo.save(connection);
//	      
//	      // Calculate total amount
//	      long totalAmount = connection.getDsa_amount() + connection.getMsa_amount();
//
//	      // Send email with total amount
//	      String subject = "Payment";
//	      String message = "Payment demand has been initiated by KPDCL for the total amount: " + totalAmount;
//	      emailService.sendEmail(email, subject, message);
//
//	      // Send SMS
//	      smsService.sendSMS(mobileNumber, message);
//
//	      return ResponseEntity.status(HttpStatus.SC_CREATED).body("Successful");
//	  }
//
	  
//	  @PostMapping("/connection")
//	  public ResponseEntity<String> createConnection(@RequestBody NEW_CONNECTION connection) {
//	      try {
//	    	  ObjectMapper objectMapper = new ObjectMapper();
//         String json = objectMapper.writeValueAsString(connection);
//	         System.out.println("Received request in saveLinkConsumer method. Request Body: " + json);
//	          Long caseId = connection.getCase_id();
//
//	          // Save the connection details to the database
//	          connectionRepo.save(connection);
//
//	          // Calculate total amount
//	          long totalAmount = connection.getDsa_amount() + connection.getMsa_amount();
//
//	          // Construct URL with caseId
//	          String URL = "http://172.18.2.96:3000/ncp/" + caseId;
//
//	          // Construct HTML message with PAY button
//	          String Subject = "Payment";
//	          String message = "Payment demand has been initiated by KPDCL for the total amount: " + totalAmount  + URL ;
//
//	          // Send email with HTML content
//	          emailService.sendEmail(connection.getEmail_id(), Subject, message); // true indicates HTML content
//
//	          // Send SMS
//	          smsService.sendSMS(connection.getMobile_number(), message); // Replace "SMS Message" with your actual message
//
//	          return ResponseEntity.status(HttpStatus.SC_CREATED).body("Successful");
//	      } catch (Exception e) {
//	          // Log the exception or handle it as needed
//	          e.printStackTrace();
//	          return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error occurred while processing the request.");
//	      }
	 // }


}
