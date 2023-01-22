package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.Category;
import com.example.serwisaukcyjny.model.Role;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.repositories.CategoryRepository;
import com.example.serwisaukcyjny.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryRest implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Category> list = (List<Category>) categoryRepository.findAll();
        if (list.isEmpty()) {
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
        if (!userRepository.existsByLogin("Admin@admin.pl")){
            userRepository.save(new User("Admin@admin.pl", "Admin", "Admin123", LocalDateTime.now(), Role.ADMIN,null));
        }

    }


}
