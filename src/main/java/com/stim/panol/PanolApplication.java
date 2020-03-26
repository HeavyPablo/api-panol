package com.stim.panol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.stim.panol")
public class PanolApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanolApplication.class, args);
	}

}
