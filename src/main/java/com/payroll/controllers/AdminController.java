package com.payroll.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/index")
	public ModelAndView viewHome() {
		return new ModelAndView("admin/index");
	}
	
}
