package com.osw.exceptions;

public class CategoryNotFoundException extends RuntimeException{
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
	public CategoryNotFoundException(String errorMessage) {
        super();
       this.errorCode="703";
        this.errorMessage = errorMessage;
    }
    public CategoryNotFoundException() {
        super();
    }

}
