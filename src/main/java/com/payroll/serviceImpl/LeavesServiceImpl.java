package com.payroll.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.entities.Employee;
import com.payroll.entities.Leaves;
import com.payroll.excepitons.EmployeeException;
import com.payroll.excepitons.LeavesException;
import com.payroll.repository.EmployeeRepository;
import com.payroll.repository.LeavesRepository;
import com.payroll.service.LeavesService;

@Service
public class LeavesServiceImpl implements LeavesService {

	@Autowired
	LeavesRepository leavesRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public boolean addLeaves(Leaves leave,String employeeId) {
		Employee employee=employeeRepository.findById(employeeId).orElseThrow(()-> new EmployeeException("Error occured while fetching employee details", "  While placing a leave"));
		leave.setEmployee(employee);
		Leaves save = leavesRepository.save(leave);
		if(save.toString().equals(leave.toString()))
		{
			return true;
		}
		else
		{
			throw new LeavesException("Error occured while placing a leave","  Try again !!!");
		}
	}

	@Override
	public boolean deleteLeave(int leaveId) {
		Leaves leaves= leavesRepository.findById(leaveId).orElseThrow(()->new LeavesException("Invaild Leaves details"," Occured while fetching leaves !!!"));
		leaves.setEmployee(null);
		leavesRepository.delete(leaves);
		return true;
	}

	@Override
	public boolean updateLeave(int leaveId) {
		Leaves leaves= leavesRepository.findById(leaveId).orElseThrow(()->new LeavesException("Invaild Leaves details"," Occured while fetching leaves !!!"));
		leaves.setLeaveStatus("Granted");
		leavesRepository.save(leaves);
		return true;
	}

	@Override
	public List<Leaves> getAllLeaves() {
		return leavesRepository.findAll();
	}

	@Override
	public Leaves getLeave(int leaveId) {
		Leaves leaves= leavesRepository.findById(leaveId).orElseThrow(()->new LeavesException("Invaild Leaves details"," Occured while fetching leaves !!!"));
		return leaves;
	}
}
