package com.payroll.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.payroll.entities.Department;
import com.payroll.entities.Designation;
import com.payroll.entities.Employee;
import com.payroll.entities.User;
import com.payroll.excepitons.DepartmentException;
import com.payroll.excepitons.DesignationException;
import com.payroll.excepitons.EmployeeException;
import com.payroll.repository.DepartmentRepository;
import com.payroll.repository.DesignationRepository;
import com.payroll.repository.EmployeeRepository;
import com.payroll.repository.LoginUserRepository;
import com.payroll.service.DesignationService;
import com.payroll.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	DesignationRepository designationRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DesignationService designationService;

	@Autowired
	LoginUserRepository loginUserRepository;

	// adding employee wants department id , design id .
	@Override
	public boolean addEmployee(Employee employee, int departmentId, String designationName) {

		boolean flag = validateEmployee(employee.getEmployeeId());
		if (!flag) {
			Optional<Department> optional = departmentRepository.findById(departmentId);
			Department department = null;
			if (optional.isPresent()) {
				department = optional.get();
			} else {
				throw new DepartmentException(
						"Department doesn't exits with ID :" + employee.getDepartment().getDepartmentId(),
						"Adding employee");
			}
			// adding designation.
			Designation designation = designationService.getDesignation(designationName);
			if (designation == null) {
				throw new DesignationException("Designation not found with Name:" + designationName, "Adding employee");
			}
			employee.setDepartment(department);
			employee.setDesignation(designation);
			department.getEmployees().add(employee);
			Department save = departmentRepository.save(department);
			// here we should not save multiple object like above add only once.
			if ((save.toString().equals(department.toString()))) {
				loginUserRepository.save(new User(employee.getEmployeeId(), employee.getPassword(), "ROLE_EMPLOYEE"));
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public boolean addEmployeeWithoutAnyCheck(Employee employee) {
		Employee save = employeeRepository.save(employee);
		if ((save.toString().equals(employee.toString()))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteEmployee(String employeeId) {
		Optional<Employee> findById = employeeRepository.findById(employeeId);
		if (findById.isPresent()) {
			Employee employee = findById.get();
			employee.setDepartment(null);
			employee.setDesignation(null);
			employeeRepository.delete(employee);
			try {
				File f1 = new ClassPathResource("/static/employee-images/" + employee.getEmployeePic()).getFile();
				if (!f1.delete()) {
					throw new EmployeeException("Error occured while deleting employee with id : " + employeeId, "");
				}
			} catch (IOException e) {
				throw new EmployeeException("Error occured while deleting employee with id : " + employeeId,
						"   TRY AGAIN !!!!!!!!!!!!");
			}
			return true;
		}
		throw new EmployeeException("Invalid Employee id : " + employeeId, " Occured while deleting employee");
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		Employee save = employeeRepository.save(employee);

		if (save != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployee(String employeeId) {

		Optional<Employee> optional = employeeRepository.findById(employeeId);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new EmployeeException("Invalid employee id : "+employeeId," while fetching details.");
	}

	public boolean validateEmployee(String id) {
		if (employeeRepository.findById(id).isPresent()) {
			return true;
		}

		return false;
	}

}
