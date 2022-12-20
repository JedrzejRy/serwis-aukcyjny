package com.example.serwisaukcyjny.form;

import com.example.serwisaukcyjny.model.Localization;
import com.example.serwisaukcyjny.model.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CreateUserForm {


    private String login;
    private String userName;
    private String password;
    private LocalDateTime startDate;
    private Role role;
    private Localization localization;


}
