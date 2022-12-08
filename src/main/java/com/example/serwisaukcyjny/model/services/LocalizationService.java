package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.Localization;
import com.example.serwisaukcyjny.model.UserMenu.User;
import com.example.serwisaukcyjny.model.repositories.LocalizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class LocalizationService {

    private final LocalizationRepository repository;

    public List<Localization> findAll(){
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(toList());
    }

    public List<Localization> findByCity(String city){
        return StreamSupport.stream(repository.findByCity(city).spliterator(), false)
                .collect(toList());
    }

    public List<Localization> findByVoivodeship(String voivodeship){
        return StreamSupport.stream(repository.findByVoivodeship(voivodeship).spliterator(), false)
                .collect(toList());
    }

    public Localization save(Localization localization) {return repository.save(localization);}



}
