package com.payroll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.payroll.entities.Employee;

@Service
public interface EmployeeService {

	boolean addEmployee(Employee employee,int departmentId,String designationName);
	
	boolean addEmployeeWithoutAnyCheck(Employee employee);
	
	boolean deleteEmployee(String employeeId);
	
	boolean updateEmployee(Employee employee);
	
	List<Employee> getEmployees();
	
	Employee getEmployee(String employeeId);
	
}
