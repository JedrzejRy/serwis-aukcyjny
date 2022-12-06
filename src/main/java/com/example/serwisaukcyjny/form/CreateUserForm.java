package com.example.serwisaukcyjny.form;

import com.example.serwisaukcyjny.model.Localization;
import com.example.serwisaukcyjny.model.UserMenu.Type;
import com.example.serwisaukcyjny.model.UserMenu.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CreateUserForm {


    private String login;
    private String userName;
    private String password;
    private LocalDateTime startDate;
    private Type type;
    private Localization localization;


}
