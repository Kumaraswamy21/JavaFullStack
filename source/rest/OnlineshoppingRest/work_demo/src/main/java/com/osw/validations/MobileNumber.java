package com.osw.validations;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = MobileConstraintValidator.class)  
@Target( { ElementType.METHOD, ElementType.FIELD } )  
@Retention(RetentionPolicy.RUNTIME)

public @interface MobileNumber {
      
        //error message  
            public String message() default "must be 10 digits";  
        //represents group of constraints     
            public Class<?>[] groups() default {};  
        //represents additional information about annotation  
            public Class<? extends Payload>[] payload() default {};  
    }  