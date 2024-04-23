package com.osw.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException UserNotFound) {
		return "Exception occured:"+UserNotFound.getErrorCode()+UserNotFound.getErrorMessage() ;
		}
	@ExceptionHandler(CategoryNotFoundException.class)
    public String handleCategoryNotFoundException(CategoryNotFoundException CategoryNotFound) {
		return "Exception occured:"+CategoryNotFound.getErrorCode()+CategoryNotFound.getErrorMessage() ;
		}
	@ExceptionHandler(ProductNotFoundexception.class)
    public String handleProductNotFoundexception(ProductNotFoundexception ProductNotFound) {
		return "Exception occured:"+ProductNotFound.getErrorCode()+ProductNotFound.getErrorMessage() ;
		}
	@ExceptionHandler(CustomerNotFoundException.class)
    public String handleCustomerNotFoundException(CustomerNotFoundException elementException,Model m) {
		return "Exception occured:"+elementException.getErrorCode()+elementException.getErrorMessage() ;
    }
	@ExceptionHandler(CartLinesNotFoundException.class)
    public String handleCartLinesNotFoundException(CartLinesNotFoundException elementException,Model m) {
		return "Exception occured:"+elementException.getErrorCode()+elementException.getErrorMessage() ;
    }
	@ExceptionHandler(AddressNotFoundexception.class)
    public String handleAddressNotFoundexception(AddressNotFoundexception elementException,Model m) {
		return "Exception occured:"+elementException.getErrorCode()+elementException.getErrorMessage() ;
    }
	
}
