package com.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.entities.Leaves;

public interface LeavesRepository extends JpaRepository<Leaves,Integer> {

}
