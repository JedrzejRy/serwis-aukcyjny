package com.example.serwisaukcyjny.mapper;

import com.example.serwisaukcyjny.form.CreateLocalizationForm;
import com.example.serwisaukcyjny.form.CreateUserForm;
import com.example.serwisaukcyjny.model.Localization;
import com.example.serwisaukcyjny.model.UserMenu.User;

import java.time.LocalDateTime;

public class LocalizationMapper {

    public static Localization toEntity(CreateLocalizationForm form) {

        return new Localization(form.getAddress(), form.getCity(), form.getVoivodeship());


    }
}
