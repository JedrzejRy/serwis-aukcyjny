package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.Category;
import com.example.serwisaukcyjny.model.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryRepositoryService implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        categoryRepository.save(new Category("Moda"));
        categoryRepository.save(new Category("Elektronika"));
        categoryRepository.save(new Category("Dom i Ogród"));
        categoryRepository.save(new Category("Supermarket"));
        categoryRepository.save(new Category("Dziecko"));
        categoryRepository.save(new Category("Uroda"));
        categoryRepository.save(new Category("Zdrowie"));
        categoryRepository.save(new Category("Kultura i rozrywka"));
        categoryRepository.save(new Category("Sport i turystyka"));
        categoryRepository.save(new Category("Motoryzacja"));
        categoryRepository.save(new Category("Nieruchomości"));
        categoryRepository.save(new Category("Kolekcje i sztuka"));
        categoryRepository.save(new Category("Firma i usługi"));


    }
}
