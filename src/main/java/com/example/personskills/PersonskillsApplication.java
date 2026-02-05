package com.example.personskills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for Person Skills Management.
 * This class serves as the entry point for the application.
 */
@SpringBootApplication
public final class PersonskillsApplication {

    /**
     * Private constructor to satisfy Checkstyle
     * HideUtilityClassConstructor rule.
     */
    private PersonskillsApplication() {
    }

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(PersonskillsApplication.class, args);
    }

}
