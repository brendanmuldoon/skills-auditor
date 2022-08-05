package com.example.employeebc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.example.employeebc")
@SpringBootApplication
public class EmployeeBcApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeBcApplication.class, args);
	}

}
