 package com.kpdcl.inbound.service;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kpdcl.inbound.entity.hierarchy;


@Service	
public class EmailService {
	
	 private final JavaMailSender emailSender;
	 private final methods method;
	    public EmailService(JavaMailSender emailSender,methods method) {
	        this.emailSender = emailSender;
	        this.method = method;
	    }
	 
	 // Method to send bulk emails
	    public void sendBulkEmail(List<String> toList, String subject, String text) {
	        for (String to : toList) {
	            sendEmail(to, subject, text);
	        }
	    }

	    // Method to send email to a single recipient
	    public void sendEmail(String to, String subject, String text) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(text);
	        emailSender.send(message);
	    }
	    
	    // method for inbound Approval
	    public void sendEmails(String to, String subject, String text, hierarchy jsonData,String office, String officeType) {
	        if (to == null || to.isEmpty() || subject == null || subject.isEmpty() || text == null || text.isEmpty() || jsonData == null) {
	            throw new IllegalArgumentException("Invalid email parameters.");
	        }
	        String sanctionedLoadStr = jsonData.getSanctionedLoad();
            double sanctionedLoad = method.extractNumericValue(sanctionedLoadStr);
            String subDivision=jsonData.getSubDivision();
	        //String officeCode=jsonData.getOfficeCode();
	        StringBuilder emailBody = new StringBuilder();
	        emailBody.append(text).append("\n");
	        emailBody.append("Case ID: ").append(jsonData.getCase_id()).append("\n");
	        emailBody.append("user: ").append(jsonData.getUser1()).append("\n");
	        emailBody.append("Sanctioned Load: ").append(jsonData.getSanctionedLoad()).append("\n");
	        emailBody.append("Category: ").append(jsonData.getCategory()).append("\n");
	        emailBody.append("subDivision: ").append(jsonData.getSubDivision()).append("\n");
	        emailBody.append("Please click the URL to Approve/Reject request: ").append("https://services.kpdcl.net?"+officeType+"="+office+"/"+"l="+sanctionedLoad+"/"+"s="+subDivision).append("\n") ;
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(emailBody.toString());
	        emailSender.send(message);
	    }
}

	