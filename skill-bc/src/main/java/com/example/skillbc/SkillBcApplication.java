package com.example.skillbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.example.skillbc")
@SpringBootApplication
public class SkillBcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkillBcApplication.class, args);
    }

}
