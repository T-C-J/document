package com.tcj.jpa.multiplesources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MultiplesourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiplesourcesApplication.class, args);
    }

}
