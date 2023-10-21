package com.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.entities.OverTimeAttendence;

public interface OverTimeAttendenceRepository extends JpaRepository<OverTimeAttendence,Integer>{

}
