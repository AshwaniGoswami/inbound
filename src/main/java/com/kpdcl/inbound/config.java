package com.kpdcl.inbound;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

//import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
//import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
//import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
//import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
//import org.springframework.boot.actuate.endpoint.web.EndpointLinksResolver;
//import org.springframework.boot.actuate.endpoint.web.EndpointMapping;
//import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
//import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
//import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;

@Configuration
public class config {


	@Bean
	JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.email.ap-mumbai-1.oci.oraclecloud.com");
		mailSender.setPort(587);
		mailSender.setUsername(
				"ocid1.user.oc1..aaaaaaaantklqvhd7joiij5vi6jcg33vp6qvdketsba2vdfeo5jndvnk6qva@ocid1.tenancy.oc1..aaaaaaaaypjf3k32nhvubi4h7dzplwdsxdwys5sq7ygfjjx36wqbphpeagwa.vi.com");
		mailSender.setPassword("!!pE8JqDrpd8qEypNj4y");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.starttls.enable", "true"); // Ensure TLS is enabled
		props.put("mail.smtp.from", "bill@kpdcl.in"); // Set the "from" address
		props.put("mail.smtp.ssl.enable", "false");
		// props.put("mail.smtp.starttls.enable", "true");

		return mailSender;

	}
}
