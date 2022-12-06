package com.example.serwisaukcyjny.mapper;

import com.example.serwisaukcyjny.form.CreateUserForm;
import com.example.serwisaukcyjny.model.UserMenu.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserMapper {
    public static User toEntity(CreateUserForm form){
        return new User(form.getLogin(), form.getUserName(), form.getPassword(), LocalDateTime.now(), form.getType(), null);
    }
}
