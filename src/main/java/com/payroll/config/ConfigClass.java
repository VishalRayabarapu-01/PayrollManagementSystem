package com.payroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.payroll.entities.Department;
import com.payroll.entities.Designation;

@Configuration
public class ConfigClass {

	@Bean
	public Department getDepartment() {
		return new Department();
	}
	
	@Bean
	public Designation getDesignation() {
		return new Designation();
	}
}
