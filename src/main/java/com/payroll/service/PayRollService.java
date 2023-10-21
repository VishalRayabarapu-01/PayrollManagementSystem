package com.payroll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.payroll.entities.PayRoll;

@Service
public interface PayRollService {

	boolean addPayroll(PayRoll payroll);
	
	boolean deletePayroll(int payrollId);
	
	boolean updatePayroll(int payrollId);
	
	List<PayRoll> getAllPayrolls();
	
	PayRoll getPayRoll(int payrollId);
}
