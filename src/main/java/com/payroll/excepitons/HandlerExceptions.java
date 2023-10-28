package com.payroll.excepitons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.entities.Department;
import com.payroll.entities.Designation;
import com.payroll.entities.Employee;
import com.payroll.entities.Salary;

@ControllerAdvice
public class HandlerExceptions {
	
	@Autowired
	Department department;

	@Autowired
	Designation designation;
	
	@Autowired
	Employee employee;
	
	@Autowired
	Salary salary;
	
	//complete this classs
	@ExceptionHandler(EmployeeException.class)
    public ModelAndView exceptionHandler(Model model,EmployeeException employeeException) {
		model.addAttribute("indexName","Add Employee");
		model.addAttribute("currentDesc","Add Employee :-");
		model.addAttribute("obj",employee);
		model.addAttribute("message",employeeException.getTotalDesc());
		return new ModelAndView("admin/addEmployee");
    }
	
	@ExceptionHandler(DepartmentException.class)
    public ModelAndView exceptionHandle(Model model,DepartmentException departmentException) {
		model.addAttribute("indexName","Add department");
		model.addAttribute("currentDesc","Add Department :-");
		model.addAttribute("obj",department);
		model.addAttribute("message",departmentException.getTotalDesc());
		return new ModelAndView("admin/addDepartment");
    }
	
	@ExceptionHandler(DesignationException.class)
    public ModelAndView desigException(Model model,DesignationException designationException) {
		model.addAttribute("indexName","Add Designation");
		model.addAttribute("currentDesc","Add Designation :-");
		model.addAttribute("obj",designation);
		model.addAttribute("url", "/admin/addDesignationDetails");
		model.addAttribute("message",designationException.getTotalDesc());
		return new ModelAndView("admin/addDesignation");
    }
	
	@ExceptionHandler(SalaryException.class)
    public ModelAndView desigException(Model model,SalaryException salaryException) {
		model.addAttribute("indexName","Add Salary");
		model.addAttribute("currentDesc","Add Salary/Manage Salary :-");
		model.addAttribute("obj",salary);
		model.addAttribute("url", "/admin/addSalaryDetails");
		model.addAttribute("message",salaryException.getTotalDesc());
		return new ModelAndView("admin/addSalary");
    }
	
}
