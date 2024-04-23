package com.osw.validations;

import java.util.regex.*; 
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class PasswordConstraintValidator implements
    ConstraintValidator<Password, String> {
        public boolean isValid(String s, ConstraintValidatorContext cvc) {  
        	String regex = "^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
           Pattern p = Pattern.compile(regex);
     if (s == null) { 
         return false; 
     } 
     Matcher m = p.matcher(s);
     return m.matches();
        }  
}
