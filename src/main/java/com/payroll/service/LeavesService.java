package com.payroll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.payroll.entities.Leaves;

@Service
public interface LeavesService {

	boolean addLeaves(Leaves leave,String employeeId);
	
	boolean deleteLeave(int leaveId);
	
	boolean updateLeave(int leaveId);
	
	List<Leaves> getAllLeaves();
	
	Leaves getLeave(int leaveId);
	
}
