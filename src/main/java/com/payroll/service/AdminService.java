package com.payroll.service;

import org.springframework.stereotype.Service;

import com.payroll.entities.Admin;

@Service
public interface AdminService {

	boolean addAdmin(Admin admin);
	
	boolean updateAdmin(String username);
	
	
}
