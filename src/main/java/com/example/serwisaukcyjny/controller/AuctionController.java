package com.example.serwisaukcyjny.controller;

import com.example.serwisaukcyjny.form.CreateAuctionForm;
import com.example.serwisaukcyjny.mapper.AuctionMapper;
import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Category;
import com.example.serwisaukcyjny.model.repositories.CategoryRepository;
import com.example.serwisaukcyjny.model.services.AuctionService;
import com.example.serwisaukcyjny.model.services.CategoryService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/home/auction")
public class AuctionController {

    private final AuctionService auctionService;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @GetMapping
    public String create(ModelMap map) {
        map.addAttribute("auction", new CreateAuctionForm());
        map.addAttribute("categories", categoryRepository.findAll());
        return "creator";
    }

    @PostMapping
    public String handleCreate(@ModelAttribute("auction") @Valid CreateAuctionForm form, Errors errors, RedirectAttributes redirectAttributes, ModelMap map) {
        log.info("Creating auction from form: {}", form);
        if (errors.hasErrors()) {
            map.addAttribute("categories", categoryRepository.findAll());
            return "creator";
        }
        auctionService.save(AuctionMapper.toEntity(form));
        redirectAttributes.addAttribute("message", "Aukcja o tytule " + form.getTitle() + " została pomyślnie dodana!");

        return "redirect:/home/auction/list";
    }

    @GetMapping("/list")
    public String list(ModelMap map, @ModelAttribute("message") String message) {
        map.addAttribute("auctions", auctionService.findAll());
        map.addAttribute("categories", categoryRepository.findAll());
        if (!message.isBlank()) {
            map.addAttribute("message", message);
        }
        return "auction-list";
    }


    @GetMapping("/list/{id}")
    public String categoryList(@PathVariable int id, ModelMap map, @ModelAttribute("message") String message) {
        List<Category> categoryList = (List<Category>) categoryRepository.findAll();
        map.addAttribute("auctions", auctionService.findAllByCategory(categoryList.get(id - 1)));
        map.addAttribute("categories", categoryRepository.findAll());
        if (!message.isBlank()) {
            map.addAttribute("message", message);
        }
        return "auction-list";
    }

}
