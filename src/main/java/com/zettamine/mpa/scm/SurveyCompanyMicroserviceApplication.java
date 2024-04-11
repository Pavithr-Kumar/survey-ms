package com.zettamine.mpa.scm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main class to bootstrap the Survey Company Microservice application.
 */
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class SurveyCompanyMicroserviceApplication {

    /**
     * Main method to start the Survey Company Microservice application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(SurveyCompanyMicroserviceApplication.class, args);
    }

}

