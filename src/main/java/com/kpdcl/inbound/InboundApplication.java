package com.kpdcl.inbound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.kpdcl.inbound.entity")
@EnableJpaRepositories("com.kpdcl.inbound.repository")
public class InboundApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(InboundApplication.class, args);
	}

}
