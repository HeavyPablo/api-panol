package com.stim.panol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.Executor;

@SpringBootApplication(scanBasePackages = "com.stim.panol")
@EnableAsync(proxyTargetClass=true)
public class PanolApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanolApplication.class, args);
	}

}
