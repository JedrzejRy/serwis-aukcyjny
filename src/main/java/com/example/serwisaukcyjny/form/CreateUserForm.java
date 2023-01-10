package com.example.serwisaukcyjny.form;

import com.example.serwisaukcyjny.model.Localization;
import com.example.serwisaukcyjny.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CreateUserForm {


    @Email(message = "niepoprawny format email")
    private String login;
    @NotBlank
    private String userName;
   // @Size(min=6, max=30)
    private String password;
    private LocalDateTime startDate;
    private Role role;
    private Localization localization;


}
