package com.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

}
