package com.payroll.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.payroll.entities.Salary;
import com.payroll.repository.SalaryRepository;
import com.payroll.service.SalaryService;

@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	SalaryRepository repository;
	
	@Override
	public boolean addSalary(Salary salary) {
		Salary save = repository.save(salary);
		return save.toString().equals(salary.toString());
	}

	@Override
	public boolean deleteSalary(int salaryId) {
		repository.deleteById(salaryId);
		return false;
	}

	@Override
	public boolean updateSalary(int salaryId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Salary> getAllSalary() {
		return repository.findAll();
	}

	@Override
	public Salary getSalary(int salaryId) {
		return repository.findById(salaryId).orElse(null);
	}

	public boolean validateAttendence(int id) {
		if (repository.findById(id).isPresent()) {
			return true;
		}
		return false;
	}

}
