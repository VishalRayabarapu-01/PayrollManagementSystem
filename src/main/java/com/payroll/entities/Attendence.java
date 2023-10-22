package com.payroll.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Attendence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int attendanceId;
	
	private Date date;
	
	private String attendanceStatus;
	
	@ManyToOne
	@JsonBackReference(value="attendence-ref")
	Employee employeeAttendence;

	@Override
	public String toString() {
		return "Attendence [date=" + date + ", attendanceStatus=" + attendanceStatus + "]";
	}
	
	
}
