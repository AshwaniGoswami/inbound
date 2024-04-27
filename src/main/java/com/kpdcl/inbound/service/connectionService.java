package com.kpdcl.inbound.service;

import com.kpdcl.inbound.entity.NEW_CONNECTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class connectionService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SMSService smsService;

//    public void saveConnection(NEW_CONNECTION connection) {
//        // Logic to save the connection details to the database
//        // You can implement this logic based on your application's requirements
//    }

    public void sendEmail(String email) {
        // Logic to send email using the provided email
        emailService.sendEmail(email, "Subject", "Body"); // Replace "Subject" and "Body" with your actual subject and body
    }

    public void sendSMS(String mobileNumber) {
        // Logic to send SMS using the provided mobile number
        smsService.sendSMS(mobileNumber, "SMS Message"); // Replace "SMS Message" with your actual message
    }
}
