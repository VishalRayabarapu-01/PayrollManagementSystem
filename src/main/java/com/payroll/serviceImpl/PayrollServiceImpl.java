package com.payroll.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.entities.PayRoll;
import com.payroll.excepitons.PayrollException;
import com.payroll.repository.PayRollRepository;
import com.payroll.service.PayRollService;

@Service
public class PayrollServiceImpl implements PayRollService {

	@Autowired
	PayRollRepository repository;

	@Override
	public boolean addPayroll(PayRoll payroll) {		
		PayRoll save = repository.save(payroll);
		if (save.toString().equals(payroll.toString())) {
			return true;
		} else {
			throw new PayrollException("Error occured while adding a Payroll", "  Try again !!!");
		}
	}

	@Override
	public boolean deletePayroll(int payrollId) {
		PayRoll payroll = repository.findById(payrollId).orElseThrow(
				() -> new PayrollException("Invaild Payroll details", " Occured while fetching details !!!"));
		payroll.setEmployee(null);
		repository.delete(payroll);
		return true;
	}

	@Override
	public boolean updatePayroll(int payrollId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PayRoll> getAllPayrolls() {
		return repository.findAll();
	}

	@Override
	public PayRoll getPayRoll(int payrollId) {
		return repository.findById(payrollId).orElseThrow(
				() -> new PayrollException("Invaild Payroll details", " Occured while fetching details !!!"));
	}

}
