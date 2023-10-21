package com.payroll.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.payroll.entities.Department;
import com.payroll.entities.Designation;
import com.payroll.entities.Employee;
import com.payroll.service.DepartmentService;
import com.payroll.service.DesignationService;
import com.payroll.service.EmployeeService;

@RestController
public class TestingController {

	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	DesignationService designationService;
	
	@Autowired
	EmployeeService employeeService;
	
	
	@DeleteMapping("/deleteDepartment/{deptId}")
	public ResponseEntity<?> deleteUser(@PathVariable int deptId) {
		boolean deleteDepartment = departmentService.deleteDepartment(deptId);
		return new ResponseEntity<>(deleteDepartment, HttpStatus.OK);
	}

	@GetMapping("/getDept")
	public ResponseEntity<?> getUser(@RequestParam int deptID) {
		Department department = departmentService.getDepartment(deptID);
		return new ResponseEntity<>(department, HttpStatus.OK);
	}

	@GetMapping("/getDepts")
	public ResponseEntity<?> getUsers() {
		List<Department> allDepartments = departmentService.getAllDepartments();
		return new ResponseEntity<>(allDepartments, HttpStatus.OK);
	}
	
	@PostMapping("/addDept")
	public ResponseEntity<?> addUsers(@RequestBody Department department) {
		System.out.println(department);
		boolean addDepartment = departmentService.addDepartment(department);
		return new ResponseEntity<>(addDepartment, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteDesig")
	public ResponseEntity<?> deleteser(@RequestParam int desigId) {
		boolean deleteDesignation = designationService.deleteDesignation(desigId);
		return new ResponseEntity<>(deleteDesignation, HttpStatus.OK);
	}

	@GetMapping("/getDesig")
	public ResponseEntity<?> getUsr(@RequestParam String designationName) {
		Designation designation = designationService.getDesignation(designationName);
		return new ResponseEntity<>(designation, HttpStatus.OK);
	}

	@GetMapping("/getDesigs")
	public ResponseEntity<?> getUser() {
		List<Designation> allDesignations = designationService.getAllDesignations();
		return new ResponseEntity<>(allDesignations, HttpStatus.OK);
	}
	
	@PostMapping("/addDesig")
	public ResponseEntity<?> adUsers(@RequestBody Designation designation) {
		boolean addDesignation = designationService.addDesignation(designation);
		return new ResponseEntity<>(addDesignation, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteEmp")
	public ResponseEntity<?> eleteser(@RequestParam String empid) {
		boolean deleteEmployee = employeeService.deleteEmployee(empid);
		return new ResponseEntity<>(deleteEmployee, HttpStatus.OK);
	}

	@GetMapping("/getEmp")
	public ResponseEntity<?> etUsr(@RequestParam String  id) {
		Employee employee = employeeService.getEmployee(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@GetMapping("/getEmps")
	public ResponseEntity<?> etUser() {
		List<Employee> employees = employeeService.getEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@PostMapping("/addEmp")
	public ResponseEntity<?> dUsers(@RequestBody Employee employee,@RequestParam int deptId,@RequestParam String name) {
		System.out.println(employee+"   "+deptId+"  "+name);
		String addEmployee = employeeService.addEmployee(employee,deptId,name);
		return new ResponseEntity<>(addEmployee, HttpStatus.OK);
	}
}
