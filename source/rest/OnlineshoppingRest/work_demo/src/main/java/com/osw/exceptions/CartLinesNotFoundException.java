package com.osw.exceptions;

public class CartLinesNotFoundException extends RuntimeException{
    private static final long serialVersionUID=-1053886567699465007L;
   
    private String errorMessage;
    private String errorCode;
   
    public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	

	public CartLinesNotFoundException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = "702";
	}

	public CartLinesNotFoundException() {
        super();
    }

 

}