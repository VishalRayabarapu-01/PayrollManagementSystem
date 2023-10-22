package com.payroll.excepitons;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String msg;
	private String field;
	private String totalDesc;
	public DepartmentException(String msg, String field) {
		this.msg = msg;
		this.field = field;
		totalDesc=msg+" at "+field;
	}
}
