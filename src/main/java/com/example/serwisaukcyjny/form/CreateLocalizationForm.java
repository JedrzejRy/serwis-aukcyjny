package com.example.serwisaukcyjny.form;


import com.example.serwisaukcyjny.model.Localization;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateLocalizationForm {

    private String address;
    private String city;
    private Localization.Voivodeship voivodeship;

}
