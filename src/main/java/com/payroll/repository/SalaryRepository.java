package com.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.entities.Salary;

public interface SalaryRepository extends JpaRepository<Salary,Integer> {

}
