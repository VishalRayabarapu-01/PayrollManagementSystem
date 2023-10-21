package com.payroll.service;

import org.springframework.stereotype.Service;

import com.payroll.entities.OverTimeAttendence;

import java.util.List;
@Service
public interface OverTimeAttendenceService {

	boolean addOverTimeAttendence(OverTimeAttendence overTimeAttendence);
	
	boolean updateOverTimeAttendence(int overtimeId);
	
	boolean deleteOverTimeAttendence(int overtimeId);
	
	List<OverTimeAttendence> getAllOvertimeAttendenceDetails();
	
}
