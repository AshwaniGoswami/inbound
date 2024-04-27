package com.kpdcl.inbound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import(config.class) 
@ComponentScan(basePackages = {"com.billsahuliyatbackendspring.repository"})
public class InboundApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(InboundApplication.class, args);
	}

}
