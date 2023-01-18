package com.example.serwisaukcyjny.form.validation.validator;

import com.example.serwisaukcyjny.form.CreateAuctionForm;
import com.example.serwisaukcyjny.form.validation.constrains.AuctionPastDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class AuctionPastDateValidator implements ConstraintValidator<AuctionPastDate, CreateAuctionForm> {

    @Override
    public void initialize(AuctionPastDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(CreateAuctionForm form, ConstraintValidatorContext constraintValidatorContext) {
        return form.getEndDate().isAfter(LocalDateTime.now().plusDays(1L));
    }
}
