package com.osw.exceptions;

public class ProductNotFoundexception extends RuntimeException{
	private static final long serialVersionUID = -1053886567699465007L;
	private String errorCode;
	private String errorMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ProductNotFoundexception(String errorMessage) {
		super();
		this.errorCode = "705";
		this.errorMessage = errorMessage;
	}

	public ProductNotFoundexception() {
		super();
		// TODO Auto-generated constructor stub
	}
}
