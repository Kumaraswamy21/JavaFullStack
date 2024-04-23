package com.osw.exceptions;

public class CustomerNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -1053886567699465007L;
	   
    private String errorMessage;
    private String errorCode;
    public CustomerNotFoundException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
        this.errorCode="704";
    }

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
}
