package com.payroll.service;

import org.springframework.stereotype.Service;

import com.payroll.entities.OverTimeAttendence;

import java.util.List;
@Service
public interface OverTimeAttendenceService {

	boolean addOverTimeAttendence(OverTimeAttendence overTimeAttendence, String employeeId);
	
	boolean updateOverTimeAttendence(int overtimeId);
	
	boolean deleteOverTimeAttendence(int overtimeId);
	
	OverTimeAttendence getOvertimeAttendence(int id);
	
	List<OverTimeAttendence> getAllOvertimeAttendenceDetails();
	
}
