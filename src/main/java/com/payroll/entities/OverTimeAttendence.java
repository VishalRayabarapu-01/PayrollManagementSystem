package com.payroll.entities;

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
public class OverTimeAttendence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int overtimeId;
	
	private String date;
	
	private String inTime;
	
	private String OutTime;
	
	private int noOfHrs;
	
	@ManyToOne
	@JsonBackReference(value="OverTimeAttendence-ref")
	Employee overTimeAttendence;

	@Override
	public String toString() {
		return "OverTimeAttendence [date=" + date + ", inTime=" + inTime + ", OutTime=" + OutTime + "]";
	}
	
}
