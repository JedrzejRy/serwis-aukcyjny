package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Category;
import com.example.serwisaukcyjny.model.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryRepository repository;


    public List<Category> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(toList());
    }

    public List<Category> findByCategory(Long id){
        return repository.findAllById(id);

    }
}


