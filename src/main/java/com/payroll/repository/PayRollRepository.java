package com.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.entities.PayRoll;

public interface PayRollRepository extends JpaRepository<PayRoll,Integer>{

}
