package com.payroll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.payroll.entities.Attendence;

@Service
public interface AttendenceService {

	boolean addAttendence(Attendence attendence,String employeeId);
	
	boolean deleteAttendence(int attendenceId);
	
	boolean updateAttendence(int attendenceId,Attendence att);
	
	List<Attendence> getAllAttendences();
	
	Attendence getAttendence(int attendenceId);
}
