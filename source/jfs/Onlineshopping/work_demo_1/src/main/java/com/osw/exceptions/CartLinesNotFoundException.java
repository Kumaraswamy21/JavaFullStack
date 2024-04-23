package com.osw.exceptions;

public class CartLinesNotFoundException extends RuntimeException{
    private static final long serialVersionUID=-1053886567699465007L;
   
    private String errorMessage;
    
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public CartLinesNotFoundException(String errorMessage) {
        super();
       
        this.errorMessage = errorMessage;
    }
    public CartLinesNotFoundException() {
        super();
    }

 

}