
  package com.osw.exceptions;
  
  
  
  
  import org.springframework.ui.Model; import
  org.springframework.web.bind.annotation.ControllerAdvice; import
  org.springframework.web.bind.annotation.ExceptionHandler;
  
  @ControllerAdvice public class GlobalExceptionHandler {
  
  @ExceptionHandler(ProductNotFoundexception.class) 
  public String handleProductNotFoundexception(ProductNotFoundexception elementException,Model m){ 
	  m.addAttribute("exceptionMessage", "Product NotFound : "+elementException.getErrorCode()+" "+elementException. getErrorMessage());
	  return "exceptionView"; 
	  }
  
  @ExceptionHandler(UserNotFoundException.class)
  public String handleUserNotFoundException(UserNotFoundException UserNotFound,Model m) {
  m.addAttribute("ExceptionMessage", "User Not Found : "+UserNotFound.getErrorMessage()); 
  return "admin/adminloginpage"; 
  }
  
  @ExceptionHandler(AddressNotFoundexception.class)
  public String handleAddressNotFoundexception(AddressNotFoundexception elementException,Model m) {
  m.addAttribute("exceptionMessage","Address NotFound:"+elementException.getErrorMessage()); 
  return "customer/exceptionAddress"; 
  }
  
  @ExceptionHandler(CartLinesNotFoundException.class)
  public String handleCartLinesNotFoundException(CartLinesNotFoundException elementException,Model m) {
  m.addAttribute("exceptionMessage","CartLines NotFound:"+elementException.getErrorMessage());
  return "customer/cartlinesExceptionView"; 
  }
  
  @ExceptionHandler(CustomerNotFoundException.class) 
  public String handleCustomerNotFoundException(CustomerNotFoundException CustomerNotFound,Model m) { 
	  m.addAttribute("ExceptionMessage","User Not Found : "+CustomerNotFound.getErrorMessage()); 
  return "customer/customerloginpage"; 
  } 
  }
 