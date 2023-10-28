package com.payroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.payroll.entities.Attendence;
import com.payroll.entities.Department;
import com.payroll.entities.Designation;
import com.payroll.entities.Employee;
import com.payroll.entities.OverTimeAttendence;
import com.payroll.entities.Salary;

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
	
	@Bean
	public Employee getEmployee() {
		return new Employee();
	}
	
	@Bean
	public Attendence getAttendence() {
		return new Attendence();
	}
	@Bean
	public Salary getSal() {
		return new Salary();
	}
	@Bean
	public OverTimeAttendence getOverAttendence() {
		return new OverTimeAttendence();
	}
	
}
