package com.payroll.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.entities.Employee;
import com.payroll.entities.Leaves;
import com.payroll.service.EmployeeService;
import com.payroll.service.LeavesService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	LeavesService leavesService;
	
	@Autowired
	Leaves leaves;
	
	Employee getEmployeeFromDb(String username)
	{
		return employeeService.getEmployee(username);
	}
	@GetMapping("/index")
	public ModelAndView index(Model model,HttpSession session,Principal principal) {
		Employee e=getEmployeeFromDb(principal.getName());
		session.setAttribute("loggedUser", e);
		model.addAttribute("indexName","Home page");
		return new ModelAndView("employee/index");
	}
	@GetMapping("/profile")
	public ModelAndView profile(Model model,HttpSession session,Principal principal) {
		Employee e=getEmployeeFromDb(principal.getName());;
		model.addAttribute("obj",e);
		model.addAttribute("indexName","Profile");
		return new ModelAndView("employee/viewProfile");
	}
	@GetMapping("/salary")
	public ModelAndView salary(Model model,HttpSession session,Principal principal) {
		Employee e=getEmployeeFromDb(principal.getName());
		model.addAttribute("obj",e.getDesignation().getSalary());
		model.addAttribute("indexName","Salary");
		return new ModelAndView("employee/viewSalary");
	}
	@GetMapping("/attendence")
	public ModelAndView attendence(Model model,HttpSession session,Principal principal) {
		Employee e=getEmployeeFromDb(principal.getName());;
		if(e.getAttendence().size()>0) {
			model.addAttribute("listOfAttendence",e.getAttendence());
		}
		model.addAttribute("indexName","Attendence");
		return new ModelAndView("employee/viewAttendence");
	}
	@GetMapping("/overtime")
	public ModelAndView overtime(Model model,HttpSession session,Principal principal) {
		Employee e=getEmployeeFromDb(principal.getName());
		if(e.getOverTimeAttendence().size() > 0) {
			model.addAttribute("listOfAttendences",e.getOverTimeAttendence());
		}
		model.addAttribute("indexName","Overtime Attendence");
		return new ModelAndView("employee/viewOvertimeAttendence");
	}
	@GetMapping("/payslips")
	public ModelAndView payslips(Model model,HttpSession session,Principal principal) {
		Employee e=getEmployeeFromDb(principal.getName());
		if(e.getPayrolls().size() >0) {
			model.addAttribute("listOfPayrolls",e.getPayrolls());
		}
		model.addAttribute("indexName","Payslips");
		return new ModelAndView("employee/viewPayrolls");
	}
	
	@GetMapping("/addLeave")
	public ModelAndView addLeave(Model model) {
		model.addAttribute("indexName", "Add Leave");
		model.addAttribute("currentDesc", "Add Leave :-");
		model.addAttribute("obj", leaves);
		model.addAttribute("url", "/employee/addLeaveDetails");
		return new ModelAndView("employee/addLeave");
	}
	
	@PostMapping("/addLeaveDetails")
	public ModelAndView addLeaveDetails(Principal principal,Model model,@ModelAttribute Leaves leaves) {
		Employee e=getEmployeeFromDb(principal.getName());
		leaves.setEmployee(e);
		leaves.setLeaveStatus("Pending");
		if(leavesService.addLeaves(leaves)) {
			model.addAttribute("submitOk", "Department added successfully");
			model.addAttribute("obj", this.leaves);
		}else {
			model.addAttribute("obj", leaves);
		}
		model.addAttribute("indexName", "Add Leave");
		model.addAttribute("currentDesc", "Add Leave :-");
		model.addAttribute("url", "/employee/addLeaveDetails");
		return new ModelAndView("employee/addLeave");
	}
	
	@GetMapping("/viewLeaves")
	public ModelAndView showLeaves(Model model,Principal principal) {
		Employee e=getEmployeeFromDb(principal.getName());
		if(e.getLeaves().size() > 0) {
			model.addAttribute("listOfLeaves",e.getLeaves());
		}
		model.addAttribute("indexName","Viewing Leaves");
		return new ModelAndView("employee/viewLeaves");
	}
}
