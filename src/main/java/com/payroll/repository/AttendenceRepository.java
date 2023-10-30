package com.payroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.entities.Attendence;

public interface AttendenceRepository extends JpaRepository<Attendence, Integer> {

//	@Query("select c from Attendence c where c.employeeId= :employeeId AND year(c.date)= :year AND month(c.date)= :month")
//	List<Attendence> getAttendenceByEmployeeId(@Param("employeeId") String employeeId, @Param("year") String year,
//			@Param("month") String month);

	List<Attendence> getByattendanceStatus(String status);
	
	List<Attendence> getByemployeeId(String employeeId);
}
