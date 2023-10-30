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
public class PayRoll {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int payrollId;

	private String payDate;

	private int workingDays, presentDays;

	private Double basicSalary, hra, da, pf, incomeTaxAmount;

	private Double lossOfPay, healthinsurance;

	private Double overTimeHoursWorked, overTimeAmount, grossSalary;

	private Double totalDeductions, netSalary;

	private String remarks;

	private int year, month;

	@ManyToOne
	@JsonBackReference(value = "payroll-ref")
	Employee employee;

	public PayRoll(String payDate, int workingDays, int presentDays, Double basicSalary, Double hra, Double da,
			Double pf, Double incomeTaxAmount, Double lossOfPay, Double healthinsurance, Double overTimeHoursWorked,
			Double overTimeAmount, Double grossSalary, Double totalDeductions, Double netSalary, String remarks,
			int year, int month, Employee employee) {
		super();
		this.payDate = payDate;
		this.workingDays = workingDays;
		this.presentDays = presentDays;
		this.basicSalary = basicSalary;
		this.hra = hra;
		this.da = da;
		this.pf = pf;
		this.incomeTaxAmount = incomeTaxAmount;
		this.lossOfPay = lossOfPay;
		this.healthinsurance = healthinsurance;
		this.overTimeHoursWorked = overTimeHoursWorked;
		this.overTimeAmount = overTimeAmount;
		this.grossSalary = grossSalary;
		this.totalDeductions = totalDeductions;
		this.netSalary = netSalary;
		this.remarks = remarks;
		this.year = year;
		this.month = month;
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "PayRoll [payDate=" + payDate + ", workingDays=" + workingDays + ", presentDays=" + presentDays
				+ ", basicSalary=" + basicSalary + ", hra=" + hra + ", da=" + da + ", pf=" + pf + ", incomeTaxAmount="
				+ incomeTaxAmount + ", lossOfPay=" + lossOfPay + ", healthinsurance=" + healthinsurance
				+ ", overTimeHoursWorked=" + overTimeHoursWorked + ", overTimeAmount=" + overTimeAmount
				+ ", grossSalary=" + grossSalary + ", totalDeductions=" + totalDeductions + ", netSalary=" + netSalary
				+ ", remarks=" + remarks + ", year=" + year + ", month=" + month + "]";
	}

}
