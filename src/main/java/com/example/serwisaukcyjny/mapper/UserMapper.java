package com.example.serwisaukcyjny.mapper;

import com.example.serwisaukcyjny.form.CreateUserForm;
import com.example.serwisaukcyjny.model.UserMenu.User;

public class UserMapper {
    public static User toEntity(CreateUserForm form){
        return new User(form.getFirstName(), form.getLastName(), form.getLogin(), form.getFirstName(), form.getType());
    }
}
