package com.osw.validations;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PasswordConstraintValidator.class)  
@Target( { ElementType.METHOD, ElementType.FIELD } )  
@Retention(RetentionPolicy.RUNTIME)

public @interface Password {
      
        //error message  
            public String message() default "must contain an uppercaseletter,lowercase letter,number,a special character and length must be between 8 and 20";  
        //represents group of constraints     
            public Class<?>[] groups() default {};  
        //represents additional information about annotation  
            public Class<? extends Payload>[] payload() default {};  
    }  