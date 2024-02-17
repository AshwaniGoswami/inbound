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

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
=======

import com.kpdcl.inbound.token.createToken;
>>>>>>> 362428b218da9a90627956b9ac23fd4b9d59a7aa

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import com.kpdcl.inbound.service.EmailService;
import com.kpdcl.inbound.token.createToken;
=======
import java.util.Map;
>>>>>>> 362428b218da9a90627956b9ac23fd4b9d59a7aa

@RestController
public class service {
	
	  @Autowired
	    private createToken tokenGenerator;

<<<<<<< HEAD
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
=======
    private final createToken createtoken;

    @Autowired
    public service(createToken createtoken) {
        this.createtoken = createtoken;
    }

    @PostMapping("/api/data")
    public ResponseEntity<ResponseObject> createData(@RequestBody Map<String, Object> jsonData) {
        try {
            // Generate token using TokenService
            String token = createtoken.generateToken(jsonData);
            System.out.println(token);
            // Create a response object including the token and any other necessary data
            ResponseObject responseObject = new ResponseObject();
            responseObject.setToken(token);
            responseObject.setData(jsonData); // Assuming you want to return the JSON data as well

            // Return the response with HTTP status 201 (CREATED)
            return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
        } catch (Exception e) {
            // If there is an error, return an appropriate HTTP status code
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Define a ResponseObject class to include token and data
    private static class ResponseObject {
        private String token;
        private Map<String, Object> data;

        // Getter and setter methods
        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Map<String, Object> getData() {
            return data;
        }

        public void setData(Map<String, Object> data) {
            this.data = data;
        }
    }
}
>>>>>>> 362428b218da9a90627956b9ac23fd4b9d59a7aa



