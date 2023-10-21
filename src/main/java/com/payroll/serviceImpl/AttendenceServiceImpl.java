package com.payroll.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.entities.Attendence;
import com.payroll.repository.AttendenceRepository;
import com.payroll.repository.EmployeeRepository;
import com.payroll.service.AttendenceService;

@Service
public class AttendenceServiceImpl implements AttendenceService{

	@Autowired
	AttendenceRepository attendenceRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public boolean addAttendence(Attendence attendence,int employeeId) {
		
//		boolean flag=validateAttendence(attendence.getAttendanceId());
//		
//		if(!flag)
//		{
//			Optional<Employee> optional = employeeRepository.findById(employeeId);
//			
//			Employee employee = optional.get();
//			 employee.getAttendence().add(attendence);
//			 
//			 employeeRepository.save(employee);
//			 
//			 attendenceRepository.save(attendence);
//			 System.out.println(employee);
//			
//		}
		return false;
	}

	@Override
	public boolean deleteAttendence(int attendenceId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAttendence(int attendenceId,Attendence attendence) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Attendence> getAllAttendences() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attendence getAttendence(int attendenceId) {

		Attendence attendence = attendenceRepository.findById(attendenceId).get();
		
		
		
		return attendence;
	}
	
	public boolean validateAttendence(int id)
	{
		Optional<Attendence> optional = attendenceRepository.findById(id);
		
		if(optional.isPresent())
		{
			return true;
		}
		
		return false;
	}

}
