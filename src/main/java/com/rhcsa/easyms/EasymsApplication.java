package com.rhcsa.easyms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@EntityScan("com.rhcsa.easyms.model")
@EnableJpaRepositories("com.rhcsa.easyms.repository") 
public class EasymsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasymsApplication.class, args);
	}

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        
		return new OpenAPI()
				  .info(new Info()
				  .title("Easy-MicroService-App")
				  .version(appVersion)
				  .description("Spring Boot v3 based RestAPI with OAS3 Apps")
            );
    }

}
