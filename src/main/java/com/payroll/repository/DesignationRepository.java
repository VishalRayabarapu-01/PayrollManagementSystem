package com.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.entities.Designation;

public interface DesignationRepository extends JpaRepository<Designation,Integer>{

	Designation getDesignationBydesignationName(String name);
}
