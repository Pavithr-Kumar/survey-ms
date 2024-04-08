package com.zettamine.mpa.scm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class SurveyCompanyMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveyCompanyMicroserviceApplication.class, args);
	}

}
