package com.tcj.lock.zookeeperlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ZookeeperlockApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperlockApplication.class, args);
    }

}
