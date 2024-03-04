  package com.kpdcl.inbound.service;



//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Properties;


@Service	
public class EmailService {
	 private final JavaMailSender javaMailSender;
	 

	    public EmailService() {
	        // Instantiate JavaMailSenderImpl here without @Bean
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com"); // Set your SMTP host here
	        mailSender.setPort(587); // Set your port
	        mailSender.setUsername("warneo790@gmail.com"); // Set your email
	        mailSender.setPassword("wzknazfslnmuefpo"); // Set your email password

	        // Configure properties
	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");

	        this.javaMailSender = mailSender;
	    }

	    public void sendBulkEmail(List<String> recipientEmails, String subject, String body) {
	        for (String email : recipientEmails) {
	            sendEmail(email, subject, body);
	        }
	    }

	    public void sendEmail(String to, String subject, String body) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject("Your Email");
	        message.setText("Your Email is " + body);
	        javaMailSender.send(message);
	        System.out.println("Email sent Successfully to: " + to);
	    }
	}