package com.kpdcl.inbound.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kpdcl.inbound.service.EmailService;
import com.kpdcl.inbound.token.createToken;

@RestController
public class service {
	
	  @Autowired
	    private createToken tokenGenerator;

	    @Autowired
	    private EmailService emailService;

	    @PostMapping("/api/data")
	    public ResponseEntity<String> createData(@RequestBody Map<String, Object> jsonData) {
	        try {
	            // Generate a JWT token using the JSON data
	            String token = tokenGenerator.generateToken(jsonData);
	            
	            // Extract email addresses from the JSON data
	            List<String> recipientEmails = extractEmails(jsonData);
	            
	            // Send bulk email
	            emailService.sendBulkEmail(recipientEmails, "Subject", "Body");

	            // Send single email
	            emailService.sendEmail("recipient@example.com", "Subject", "Body");

	            // Return the token along with a success status
	            return new ResponseEntity<>(token, HttpStatus.CREATED);
	        } catch (Exception e) {
	            // If there is an error, return an appropriate HTTP status code
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    // Method to extract email addresses from JSON data
	    @SuppressWarnings("unchecked")
		private List<String> extractEmails(Map<String, Object> jsonData) {
	        List<String> emails = new ArrayList<>();
	        // Implement logic to extract email addresses from the JSON data
	        // For example:
	        if (jsonData.containsKey("emails")) {
	            emails = (List<String>) jsonData.get("emails");
	        }
	        return emails;

//    @Autowired
//    private createToken tokenGenerator;
//
//    @PostMapping("/api/data")
//    public ResponseEntity<String> createData(@RequestBody Map<String, Object> jsonData) {
//        try {
//            // Generate a JWT token using the JSON data
//            String token = tokenGenerator.generateToken(jsonData);
//            
//            // Return the token along with a success status
//            return new ResponseEntity<>(token, HttpStatus.CREATED);
//        } catch (Exception e) {
//            // If there is an error, return an appropriate HTTP status code
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}}





