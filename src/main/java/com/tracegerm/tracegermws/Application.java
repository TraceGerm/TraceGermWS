package com.tracegerm.tracegermws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.tracegerm.tracegermws.*"})
@EnableJpaRepositories("com.tracegerm.tracegermws.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}