package com.payroll.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.entities.Employee;
import com.payroll.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	Employee getEmployeeFromDb(String username)
	{
		return employeeService.getEmployee(username);
	}
	@GetMapping("/index")
	public ModelAndView index(Model model) {
		model.addAttribute("indexName","Home page");
		return new ModelAndView("employee/index");
	}
	@GetMapping("/profile")
	public ModelAndView profile(Model model,HttpSession session,Principal principal) {
		Employee e;
		if(session.getAttribute("loggedUser")==null) {
			e=getEmployeeFromDb(principal.getName());
			session.setAttribute("loggedUser", e);
		}else {
			e=(Employee)session.getAttribute("loggedUser");
		}
		model.addAttribute("indexName","Profile");
		return new ModelAndView("employee/index");
	}
	@GetMapping("/salary")
	public ModelAndView salary(Model model,HttpSession session,Principal principal) {
		Employee e;
		if(session.getAttribute("loggedUser")==null) {
			e=getEmployeeFromDb(principal.getName());
			session.setAttribute("loggedUser", e);
		}else {
			e=(Employee)session.getAttribute("loggedUser");
		}
		model.addAttribute("indexName","Salary");
		return new ModelAndView("employee/index");
	}
	@GetMapping("/attendence")
	public ModelAndView attendence(Model model,HttpSession session,Principal principal) {
		Employee e;
		if(session.getAttribute("loggedUser")==null) {
			e=getEmployeeFromDb(principal.getName());
			session.setAttribute("loggedUser", e);
		}else {
			e=(Employee)session.getAttribute("loggedUser");
		}
		model.addAttribute("indexName","Attendence");
		return new ModelAndView("employee/index");
	}
	@GetMapping("/overtime")
	public ModelAndView overtime(Model model,HttpSession session,Principal principal) {
		Employee e;
		if(session.getAttribute("loggedUser")==null) {
			e=getEmployeeFromDb(principal.getName());
			session.setAttribute("loggedUser", e);
		}else {
			e=(Employee)session.getAttribute("loggedUser");
		}
		model.addAttribute("indexName","Overtime Attendence");
		return new ModelAndView("employee/index");
	}
	@GetMapping("/payslips")
	public ModelAndView payslips(Model model,HttpSession session,Principal principal) {
		Employee e;
		if(session.getAttribute("loggedUser")==null) {
			e=getEmployeeFromDb(principal.getName());
			session.setAttribute("loggedUser", e);
		}else {
			e=(Employee)session.getAttribute("loggedUser");
		}
		model.addAttribute("indexName","Payslips");
		return new ModelAndView("employee/index");
	}
}
