package com.example.serwisaukcyjny.form;


import com.example.serwisaukcyjny.model.Localization;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateLocalizationForm {

    @NotBlank(message = "wprowadz nazwę ulicy")
    private String address;
    @NotBlank(message = "Wprowadz nazwę miasta")
    private String city;
    private Localization.Voivodeship voivodeship;

}
