package com.payroll.entities;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Designation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int designationId;
	
	private String designationName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "designation")
	@JsonManagedReference(value = "designation-ref")
	private List<Employee> employees=new LinkedList<>();

	@Override
	public String toString() {
		return "Designation [designationName=" + designationName + "]";
	}	
}
