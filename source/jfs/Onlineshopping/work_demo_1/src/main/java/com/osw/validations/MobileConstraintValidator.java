package com.osw.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileConstraintValidator implements
ConstraintValidator<MobileNumber, String> {
    public boolean isValid(String s, ConstraintValidatorContext cvc) {  
    	String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        if (s == null) {
            return false;
        }
        boolean result=false;
        Matcher m=p.matcher("s");
      
        if(s.length()==10)
        	 m= p.matcher(s);
        
        result=m.matches();
        return result ;
    }  

}
