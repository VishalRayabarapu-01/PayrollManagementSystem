package com.payroll.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.entities.User;
import com.payroll.repository.LoginUserRepository;

@RestController
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	LoginUserRepository repository;
	
	@GetMapping("/signin")
	public ModelAndView loginmethod(Model model) {
		return new ModelAndView("Login");
	}

	@GetMapping("/validateLoginUser")
	public ModelAndView vaidate(Principal principal,Model model) {
		User user=null;
		Optional<User> optionalUser = repository.findById(principal.getName());
		if(optionalUser.isPresent()) {
			user=optionalUser.get();
		}
		if(user.getRole().equals("ROLE_ADMIN")) {
			return new ModelAndView("redirect:/admin/index");
		}else {
			return new ModelAndView("redirect:/employee/index");
		}
//		else if(user.getRole().equals("ROLE_EMPLOYEE")) {
//			return new ModelAndView("redirect:/employee/index");
//		}
		
	}
}
