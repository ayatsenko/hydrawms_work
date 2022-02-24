package com.idltd.hydramob;

import com.idltd.hydramob.repository.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class HydraWmsNewApplication {

	public static void main(String[] args) {
//        ApiContextInitializer.init();
		SpringApplication.run(HydraWmsNewApplication.class, args);
	}

	@PostConstruct
	public void init() {
		ApiContextInitializer.init();
	}
}
