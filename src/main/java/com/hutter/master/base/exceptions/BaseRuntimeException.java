package com.hutter.master.base.exceptions;

public class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private ErrorCode errorCode;
	
	public BaseRuntimeException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public BaseRuntimeException(ErrorCode errorCode, Throwable arg0) {
		super(arg0);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode.getName();
	}

}
