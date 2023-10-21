package com.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.entities.Attendence;

public interface AttendenceRepository extends JpaRepository<Attendence,Integer> {

}
