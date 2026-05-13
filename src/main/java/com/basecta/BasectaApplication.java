package com.basecta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BasectaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasectaApplication.class, args);
	}

}
