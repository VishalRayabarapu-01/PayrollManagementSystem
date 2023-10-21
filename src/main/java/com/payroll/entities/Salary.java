package com.payroll.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Salary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int salaryId;
	private Double basicSalary;
	private Double hra, da, pf, incomeTaxPercentage;
	private Double healthinsurance, overTimePerHourPay;
	
}
