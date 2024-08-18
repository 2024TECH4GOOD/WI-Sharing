package com.example.tech4good_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class Tech4GoodServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tech4GoodServerApplication.class, args);
    }

}
