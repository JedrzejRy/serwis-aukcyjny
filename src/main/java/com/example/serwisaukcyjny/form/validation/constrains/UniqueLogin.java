package com.example.serwisaukcyjny.form.validation.constrains;

import com.example.serwisaukcyjny.form.validation.validator.UniqueLoginValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueLoginValidator.class})
public @interface UniqueLogin {
    String message() default "{com.example.serwisaukcyjny.web.mvc.form.validation.constraints.UniqueEmail.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
