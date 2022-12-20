package com.example.serwisaukcyjny.mapper;

import com.example.serwisaukcyjny.form.CreateUserForm;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.Localization;
import com.example.serwisaukcyjny.model.services.LocalizationService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


public class UserMapper {
    public static User toEntity(CreateUserForm form){
        return new User(form.getLogin(), form.getUserName(), form.getPassword(), LocalDateTime.now(), form.getRole(), null);
    }
}
