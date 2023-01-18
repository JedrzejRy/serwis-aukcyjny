package com.example.serwisaukcyjny.form.validation.constrains;

import com.example.serwisaukcyjny.form.validation.validator.AuctionDurationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AuctionDurationValidator.class})
public @interface AuctionDuration {
    String message() default "{com.sda.eventapp.web.mvc.form.validation.constraints.EventPastTime.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
