package com.stim.panol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication(scanBasePackages = "com.stim.panol")
public class PanolApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanolApplication.class, args);
	}

}
