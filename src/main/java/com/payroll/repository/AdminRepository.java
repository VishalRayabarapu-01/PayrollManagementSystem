package com.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin,String>{

}
