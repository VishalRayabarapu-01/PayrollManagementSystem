package com.payroll.entities;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Employee {

	@Id
	private String employeeId;
	private String firstName;
	private String lastName;
	private String gender;
	private String mobile;
	private Date dateOfBirth;
	private String contactAddress;
	private String password;
	private String employeePic;
	
	@ManyToOne
	@JsonBackReference(value = "dept-ref")
	Department department;
	
	@ManyToOne
	@JsonBackReference(value = "designation-ref")
	Designation designation;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	@JsonManagedReference(value="leaves-ref")
	private List<Leaves> leaves=new LinkedList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeAttendence")
	@JsonManagedReference(value="attendence-ref")
	private List<Attendence> attendence=new LinkedList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "overTimeAttendence")
	@JsonManagedReference(value="OverTimeAttendence-ref")
	private List<OverTimeAttendence> overTimeAttendence=new LinkedList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	@JsonManagedReference(value="payroll-ref")
	private List<PayRoll> payrolls=new LinkedList<>();

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", mobile=" + mobile + ", dateOfBirth=" + dateOfBirth + ", contactAddress="
				+ contactAddress + ", password=" + password + ", employeePic=" + employeePic + "]";
	}

	
}
