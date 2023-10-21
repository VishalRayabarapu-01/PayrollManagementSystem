package com.payroll.entities;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Department {

	@Id
	private int departmentId;
	private String departmentName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	@JsonManagedReference(value = "dept-ref")
	private List<Employee> employees = new LinkedList<>();

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + "]";
	}
}
