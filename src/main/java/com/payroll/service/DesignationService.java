package com.payroll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.payroll.entities.Designation;

@Service
public interface DesignationService {

	boolean addDesignation(Designation designation);
	
	boolean deleteDesignation(int designationId);
	
	boolean updateDesignation(Designation designation);
	
	List<Designation> getAllDesignations();
	
	Designation getDesignation(int designationId);
	
	Designation getDesignation(String name);
	
	Designation getDesignationWithName(String name);
}
