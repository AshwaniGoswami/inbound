package com.kpdcl.inbound.service;

import org.springframework.stereotype.Service;

@Service
public class SMSService {

    // Method to send SMS
    public void sendSMS(String mobileNumber, String message) {
        // Logic to send SMS using the provided mobile number and message
        System.out.println("Sending SMS to: " + mobileNumber);
        System.out.println("Message: " + message);
        // Implement your SMS sending logic here
    }
}
