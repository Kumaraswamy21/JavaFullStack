package com.osw.exceptions;

public class UserNotFoundException extends Exception {
    
    private static final long serialVersionUID = -1053886567699465007L;
   
    private String errorMessage;
   
    public UserNotFoundException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}