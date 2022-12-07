package com.example.serwisaukcyjny.model.repositories;

import com.example.serwisaukcyjny.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository <Category, Long> {

List<Category> findAllById(Long id);

}
