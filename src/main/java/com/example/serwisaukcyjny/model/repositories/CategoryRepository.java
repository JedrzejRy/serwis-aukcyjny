package com.example.serwisaukcyjny.model.repositories;

import com.example.serwisaukcyjny.model.Category;
import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository <Category, Long> {

    Category findByCategoryName(String name);

}
