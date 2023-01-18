package com.example.serwisaukcyjny.form.validation.validator;

import com.example.serwisaukcyjny.form.CreateAuctionForm;
import com.example.serwisaukcyjny.form.validation.constrains.AuctionDuration;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class AuctionDurationValidator implements ConstraintValidator<AuctionDuration, CreateAuctionForm> {
    @Override
    public void initialize(AuctionDuration constraintAnnotation) {
    }

    @Override
    public boolean isValid(CreateAuctionForm form, ConstraintValidatorContext constraintValidatorContext) {
        return form.getEndDate().isBefore(LocalDateTime.now().plusDays(7L));
    }
}
