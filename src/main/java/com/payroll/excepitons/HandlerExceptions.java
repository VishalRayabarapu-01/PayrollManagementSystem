package com.payroll.excepitons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.entities.Department;
import com.payroll.entities.Designation;

@ControllerAdvice
public class HandlerExceptions {
	
	@Autowired
	Department department;

	@Autowired
	Designation designation;
	
	//complete this classs
	@ExceptionHandler(EmployeeException.class)
    public ModelAndView exceptionHandler() {
        return new ModelAndView("");
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
		model.addAttribute("url", "/admin/editDesignationDetails");
		model.addAttribute("message",designationException.getTotalDesc());
		return new ModelAndView("admin/addDesignation");
    }
	
}
