package com.example.serwisaukcyjny.mapper;

import com.example.serwisaukcyjny.form.CreateUserForm;
import com.example.serwisaukcyjny.model.Role;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.Localization;

import java.security.Principal;
import java.time.LocalDateTime;


public class UserMapper {
    public static User toEntity(CreateUserForm form, Localization localization){
        return new User(form.getLogin(), form.getUserName(), form.getPassword(), LocalDateTime.now(), Role.USER, localization);
    }
}
