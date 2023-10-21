package com.payroll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.payroll.entities.Department;

@Service
public interface DepartmentService {

	boolean addDepartment(Department department);
	
	boolean deleteDepartment(int departmentId);
	
	boolean updateDepartment(Department departmentId);
	
	List<Department> getAllDepartments();
	
	Department getDepartment(int departmentId);
	
}
