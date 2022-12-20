package com.example.serwisaukcyjny.mapper;

import com.example.serwisaukcyjny.form.CreateLocalizationForm;
import com.example.serwisaukcyjny.model.Localization;


public class LocalizationMapper {

    public static Localization toEntity(CreateLocalizationForm form) {

        return new Localization(form.getAddress(), form.getCity(), form.getVoivodeship());


    }
}
