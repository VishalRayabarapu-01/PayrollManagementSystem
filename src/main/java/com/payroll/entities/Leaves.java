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
public class Leaves {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int leaveId;
	
	private String leaveType;
	
	private Date leaveStartDate;
	
	private Date leaveEndDate;
	
	private int days;
	
	private String leavepurpose;
	
	private String leaveStatus;
	
	@ManyToOne
	@JsonBackReference(value="leaves-ref")
	Employee employee;

	@Override
	public String toString() {
		return "Leaves [leaveType=" + leaveType + ", leaveStartDate=" + leaveStartDate + ", leaveEndDate="
				+ leaveEndDate + ", days=" + days + ", leavepurpose=" + leavepurpose + ", leaveStatus=" + leaveStatus
				+ "]";
	}
}
