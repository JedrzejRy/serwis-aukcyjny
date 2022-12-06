package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.Rating;
import com.example.serwisaukcyjny.model.repositories.Ratingrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Ratingservice {

    private final Ratingrepository repository;

    private Rating save(Rating rating) {return repository.save(rating); }

}
