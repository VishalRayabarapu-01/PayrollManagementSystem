package com.payroll.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.entities.Attendence;
import com.payroll.entities.Employee;
import com.payroll.excepitons.AttendenceException;
import com.payroll.excepitons.EmployeeException;
import com.payroll.repository.AttendenceRepository;
import com.payroll.repository.EmployeeRepository;
import com.payroll.service.AttendenceService;

@Service
public class AttendenceServiceImpl implements AttendenceService {

	@Autowired
	AttendenceRepository attendenceRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public boolean addAttendence(Attendence attendence, String employeeId) {

		boolean flag = validateAttendence(attendence.getAttendanceId());

		if (!flag) {
			Optional<Employee> optional = employeeRepository.findById(employeeId);
			if (optional.isEmpty()) {
				throw new EmployeeException("Invalid employee id :" + employeeId, "Adding attendence");
			}
			Employee employee = optional.get();
			employee.getAttendence().add(attendence);
			Employee save = employeeRepository.save(employee);
			if(save.toString().equals(employee.toString())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteAttendence(int attendenceId) {
		Attendence attendence = attendenceRepository.findById(attendenceId).orElseThrow(()->new AttendenceException("Invalid Attendence id ","Occured while deleting attendence !!!!"));
		attendence.setEmployeeAttendence(null);
		attendenceRepository.delete(attendence);
		return true;
	}

	@Override
	public boolean updateAttendence(int attendenceId, Attendence attendence) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Attendence> getAllAttendences() {
		return attendenceRepository.findAll();
	}

	@Override
	public Attendence getAttendence(int attendenceId) {
		Optional<Attendence> optional = attendenceRepository.findById(attendenceId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public boolean validateAttendence(int id) {
		if (attendenceRepository.findById(id).isPresent()) {
			return true;
		}
		return false;
	}

}
