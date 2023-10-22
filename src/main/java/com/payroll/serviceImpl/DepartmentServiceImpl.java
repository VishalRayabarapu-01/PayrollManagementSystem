package com.payroll.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.entities.Department;
import com.payroll.excepitons.DepartmentException;
import com.payroll.repository.DepartmentRepository;
import com.payroll.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public boolean addDepartment(Department department) {
		boolean flag=validateDepartment(department.getDepartmentId());
		if(flag) {
			throw new DepartmentException("Department Already exists.","Adding Department");
		}
		if(!flag)
		{
			Department save = departmentRepository.save(department);
			if(save.toString().equals(department.toString()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean deleteDepartment(int departmentId) {
		
		Optional<Department> optional = departmentRepository.findById(departmentId);
		
		if(optional.isPresent())
		{
			departmentRepository.delete(optional.get());
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateDepartment(Department department) {
		boolean flag=validateDepartment(department.getDepartmentId());
		if(!flag) {
			throw new DepartmentException("For adding new Department go to Department => Add", "Updating department");
		}
		Department save = departmentRepository.save(department);
		if(save!=null)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public List<Department> getAllDepartments() {
		
		 return departmentRepository.findAll();
	}

	@Override
	public Department getDepartment(int departmentId) {
		
		Optional<Department> optional = departmentRepository.findById(departmentId);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new DepartmentException("Department id doesn't exist","fetching department details");
		}
	}
	
	public boolean validateDepartment(int id)
	{
		Optional<Department> department= departmentRepository.findById(id);
		
		if(department.isPresent())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
}