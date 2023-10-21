package com.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.entities.User;

public interface LoginUserRepository extends JpaRepository<User,String> {

}
