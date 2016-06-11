package com.hutter.master.base.exceptions;

public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ErrorCode errorCode;

	public BaseException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public BaseException(ErrorCode errorCode, Throwable arg0) {
		super(arg0);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode.getName();
	}

}
