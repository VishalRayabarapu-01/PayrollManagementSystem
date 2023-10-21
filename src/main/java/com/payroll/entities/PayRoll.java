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
public class PayRoll {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int payrollId;
	
	private Date payDate;
	
	private int workingDays,presentDays;
	
	private Double basicSalary,hra,da,pf,incomeTaxAmount;
	
	private Double lossOfPay;
	
	private Double overTimeHoursWorked,overTimeAmount,grossSalary;
	
	private Double totalDeductions,netSalary;
	
	private String remarks;
	
	@ManyToOne
	@JsonBackReference(value="payroll-ref")
	Employee employee;
	
}
