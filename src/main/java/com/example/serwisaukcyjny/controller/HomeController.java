package com.example.serwisaukcyjny.controller;

import com.example.serwisaukcyjny.form.CreateAuctionForm;
import com.example.serwisaukcyjny.model.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    public String categoriesList(ModelMap map) {
        map.addAttribute("categories",categoryRepository.findAll());
        return "home";
    }

}
