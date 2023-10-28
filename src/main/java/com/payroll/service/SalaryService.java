package com.payroll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.payroll.entities.Designation;
import com.payroll.entities.Salary;

@Service
public interface SalaryService {

	boolean addSalary(Salary salary,Designation designation);
	
	boolean deleteSalary(int salaryId);
	
	boolean updateSalary(Salary sal);
	
	List<Salary> getAllSalary();
	
	Salary getSalary(int salaryId);
}
