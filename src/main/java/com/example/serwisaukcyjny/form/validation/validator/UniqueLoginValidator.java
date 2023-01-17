package com.example.serwisaukcyjny.form.validation.validator;


import com.example.serwisaukcyjny.form.CreateUserForm;
import com.example.serwisaukcyjny.form.validation.constrains.UniqueLogin;
import com.example.serwisaukcyjny.model.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, CreateUserForm> {

    private final UserService userService;

    @Override
    public void initialize(UniqueLogin constraintAnnotation) {
    }

    @Override
    public boolean isValid(CreateUserForm form, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.existsByLogin(form.getLogin());
    }
}
