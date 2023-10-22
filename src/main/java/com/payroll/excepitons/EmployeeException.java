package com.payroll.excepitons;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String msg;
	private String field;
	public EmployeeException(String msg,String field){
		this.msg=msg;
		this.field=field;
	}
}
