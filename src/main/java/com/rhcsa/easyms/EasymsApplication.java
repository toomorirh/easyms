package com.rhcsa.easyms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EntityScan("com.rhcsa.easyms.model")
@EnableJpaRepositories("com.rhcsa.easyms.repository") 
@OpenAPIDefinition(info = @Info(title = "Demo-Rest-App", 
        description = "Spring Boot v3 and spring-doc open api v2 sample Apps", 
        version = "v2.0.0")) 
public class EasymsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasymsApplication.class, args);
	}

}
