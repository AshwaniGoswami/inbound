//package com.kpdcl.inbound.service;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//
//@Service	
//public class EmailService {
//	 @Autowired
//	    private JavaMailSender javaMailSender;
//
//	    public void sendBulkEmail(List<String> recipientEmails, String subject, String body) {
//	        for (String email : recipientEmails) {
//	            sendEmail(email, subject, body);
//	        }
//	    }
//
//	    public void sendEmail(String to, String subject, String body) {
//	        SimpleMailMessage message = new SimpleMailMessage();
//	        message.setTo(to);
//	        message.setSubject(subject);
//	        message.setText(body);
//	        javaMailSender.send(message);
//	        System.out.println("Email send Successfully");
//	    }
//
//}
