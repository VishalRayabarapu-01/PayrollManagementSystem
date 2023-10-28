package com.payroll.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
	private Double hra, da, pf,incomeTaxPercentage;
	private Double healthinsurance, overTimePerHourPay;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "salary")
	@JsonBackReference(value = "salary-ref")
	Designation designation;
	
	@Override
	public String toString() {
		return "Salary [basicSalary=" + basicSalary + ", hra=" + hra + ", da=" + da + ", pf=" + pf
				+ ", incomeTaxPercentage=" + incomeTaxPercentage + ", healthinsurance=" + healthinsurance
				+ ", overTimePerHourPay=" + overTimePerHourPay + "]";
	}
	
}
