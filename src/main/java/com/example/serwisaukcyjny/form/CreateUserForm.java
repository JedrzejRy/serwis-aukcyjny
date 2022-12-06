package com.example.serwisaukcyjny.form;

import com.example.serwisaukcyjny.model.UserMenu.Type;
import com.example.serwisaukcyjny.model.UserMenu.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserForm {
    private String firstName;
    private String lastName;
    @NotBlank(message = "Pole nie może być puste")
    @Email (message = "Login powinien być poprawnym formatem adresu email")
    private String login;
    @Size(min = 8, message = "Minimalna liczba znaków: 8")
    @NotBlank(message = "Pole nie może być puste")
    private String password;
    private Type type;
}
