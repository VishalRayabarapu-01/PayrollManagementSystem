package com.payroll.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.entities.Designation;
import com.payroll.entities.Salary;
import com.payroll.excepitons.SalaryException;
import com.payroll.repository.DesignationRepository;
import com.payroll.repository.SalaryRepository;
import com.payroll.service.SalaryService;

@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	SalaryRepository repository;
	
	@Autowired
	DesignationRepository designationRepository;
	
	@Override
	public boolean addSalary(Salary salary,Designation designation) {
		System.out.println(designation.getSalary());
		salary.setDesignation(designation);
		repository.save(salary);
		Designation save = designationRepository.save(designation);
		return save.toString().equals(designation.toString());
	}

	@Override
	public boolean deleteSalary(int salaryId) {
		Salary sal=repository.findById(salaryId).orElseThrow(()-> new SalaryException("Invalid Salary id", "Occured while deleting salary !!!"));
		int designationId = sal.getDesignation().getDesignationId();
		Designation designation = designationRepository.findById(designationId).get();
		designation.setSalary(null);
		designationRepository.save(designation);
		sal.setDesignation(null);
		repository.delete(sal);
		return true;
	}

	@Override
	public boolean updateSalary(Salary sal) {
		boolean flag=validateSalary(sal.getSalaryId());
		if(!flag) {
			throw new SalaryException("For adding new Salary go to Salary => Add", "Updating Salary");
		}
		Salary save = repository.save(sal);
		if(save!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<Salary> getAllSalary() {
		return repository.findAll();
	}

	@Override
	public Salary getSalary(int salaryId) {
		return repository.findById(salaryId)
	.orElseThrow(()->new SalaryException("Invalid Salary Details occured", "Fetching salary details"));
	}

	public boolean validateSalary(int id) {
		if (repository.findById(id).isPresent()) {
			return true;
		}
		return false;
	}

}
