package com.example.serwisaukcyjny.model.repositories;

import com.example.serwisaukcyjny.model.Localization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocalizationRepository extends CrudRepository<Localization, Long> {


    List<Localization> findByCity(String city);
    List<Localization> findByVoivodeship(String voivodeship);

}
