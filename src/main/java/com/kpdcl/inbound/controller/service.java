package com.kpdcl.inbound.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kpdcl.inbound.token.createToken;

@RestController
public class service {

    @Autowired
    private createToken tokenGenerator;

    @PostMapping("/api/data")
    public ResponseEntity<String> createData(@RequestBody Map<String, Object> jsonData) {
        try {
            // Generate a JWT token using the JSON data
            String token = tokenGenerator.generateToken(jsonData);
            
            // Return the token along with a success status
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        } catch (Exception e) {
            // If there is an error, return an appropriate HTTP status code
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}





