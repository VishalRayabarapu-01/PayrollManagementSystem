package com.payroll.excepitons;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LeavesException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String msg;
	private String field;
	private String totalDesc;
	public LeavesException(String msg, String field) {
		this.msg = msg;
		this.field = field;
		totalDesc=msg+" at "+field;
	}
}
