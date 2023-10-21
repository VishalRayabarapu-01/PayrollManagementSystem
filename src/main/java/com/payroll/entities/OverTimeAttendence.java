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
public class OverTimeAttendence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int overtimeId;
	
	private Date date;
	
	private String inTime;
	
	private String OutTime;
	
	@ManyToOne
	@JsonBackReference(value="OverTimeAttendence-ref")
	Employee overTimeAttendence;
	
}
