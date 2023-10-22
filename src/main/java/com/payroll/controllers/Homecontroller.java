package com.payroll.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Homecontroller {

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("home");
	}
}
