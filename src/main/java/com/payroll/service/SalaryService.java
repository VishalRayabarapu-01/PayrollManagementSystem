package com.payroll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.payroll.entities.Salary;

@Service
public interface SalaryService {

	boolean addSalary(Salary salary);
	
	boolean deleteSalary(int salaryId);
	
	boolean updateSalary(int salaryId);
	
	List<Salary> getAllSalary();
	
	Salary getSalary(int salaryId);
}
