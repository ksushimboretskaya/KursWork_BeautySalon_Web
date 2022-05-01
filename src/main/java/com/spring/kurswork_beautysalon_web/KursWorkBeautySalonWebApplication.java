package com.spring.kurswork_beautysalon_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class KursWorkBeautySalonWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(KursWorkBeautySalonWebApplication.class, args);
    }
}