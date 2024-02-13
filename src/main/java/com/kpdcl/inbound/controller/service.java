package com.kpdcl.inbound.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class service {
	   @PostMapping("/api/data")
	    public ResponseEntity<Map<String, Object>> createData(@RequestBody Map<String, Object> jsonData) {
	        try {
	            // You can directly return the received JSON data
	            return new ResponseEntity<>(jsonData, HttpStatus.CREATED);
	        } catch (Exception e) {
	        	
	            // If there is an error, return an appropriate HTTP status code
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
