package com.payroll.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.entities.Employee;
import com.payroll.entities.OverTimeAttendence;
import com.payroll.excepitons.AttendenceException;
import com.payroll.excepitons.EmployeeException;
import com.payroll.repository.EmployeeRepository;
import com.payroll.repository.OverTimeAttendenceRepository;
import com.payroll.service.OverTimeAttendenceService;
@Service
public class OverTimeAttendenceServiceImpl implements OverTimeAttendenceService{
	
	@Autowired
	OverTimeAttendenceRepository  repository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public boolean addOverTimeAttendence(OverTimeAttendence overTimeAttendence, String employeeId) {

		boolean flag = validateAttendence(overTimeAttendence.getOvertimeId());

		if (!flag) {
			Optional<Employee> optional = employeeRepository.findById(employeeId);
			if (optional.isEmpty()) {
				throw new EmployeeException("Invalid employee id :" + employeeId, "Adding Overtime attendence");
			}
			Employee employee = optional.get();
			overTimeAttendence.setOverTimeAttendence(employee);
			employee.getOverTimeAttendence().add(overTimeAttendence);
			Employee save = employeeRepository.save(employee);
			if(save.toString().equals(employee.toString())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateOverTimeAttendence(int overtimeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOverTimeAttendence(int overtimeId) {
		OverTimeAttendence attendence = repository.findById(overtimeId).orElseThrow(()->new AttendenceException("Invalid Attendence id ","Occured while deleting attendence !!!!"));
		attendence.setOverTimeAttendence(null);
		repository.delete(attendence);
		return true;
	}

	@Override
	public List<OverTimeAttendence> getAllOvertimeAttendenceDetails() {
		return repository.findAll();
	}
	
	@Override
	public OverTimeAttendence getOvertimeAttendence(int attendenceId) {
		Optional<OverTimeAttendence> optional = repository.findById(attendenceId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public boolean validateAttendence(int id) {
		if (repository.findById(id).isPresent()) {
			return true;
		}
		return false;
	}

}
