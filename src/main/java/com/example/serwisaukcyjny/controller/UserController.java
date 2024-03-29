package com.example.serwisaukcyjny.controller;

import com.example.serwisaukcyjny.form.CreateLocalizationForm;
import com.example.serwisaukcyjny.form.CreateUserForm;
import com.example.serwisaukcyjny.mapper.LocalizationMapper;
import com.example.serwisaukcyjny.mapper.UserMapper;
import com.example.serwisaukcyjny.model.Role;
import com.example.serwisaukcyjny.model.Localization;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.services.CategoryService;
import com.example.serwisaukcyjny.model.services.LocalizationService;
import com.example.serwisaukcyjny.model.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/home/register")
public class UserController {

    private static final String MESSAGE_KEY = "message";
    private final UserService userService;
    private final LocalizationService localizationService;
    private final CategoryService categoryService;



    @GetMapping
    public String register(ModelMap map) {
        map.addAttribute("user", new CreateUserForm());
        map.addAttribute("roles", Role.values());
        map.addAttribute("localization", new CreateLocalizationForm());
        map.addAttribute("voivodeships", Localization.Voivodeship.values());
        map.addAttribute("categories", categoryService.findAll());

        return "registered";
    }

    @PostMapping
    public String handleCreate(@ModelAttribute("user") @Valid CreateUserForm form, BindingResult userFormBindingResult, @Valid @ModelAttribute("localization")  CreateLocalizationForm formLoc, BindingResult formLocBindingResult, RedirectAttributes redirectAttributes, ModelMap map) {

        if (userFormBindingResult.hasErrors() || formLocBindingResult.hasErrors()) {
            map.addAttribute("types", Role.values());
            map.addAttribute("voivodeships", Localization.Voivodeship.values());
            return "registered";
        }
        Localization localization = LocalizationMapper.toEntity(formLoc);
        localizationService.save(localization);
        userService.save(UserMapper.toEntity(form, localization));


        redirectAttributes.addAttribute(MESSAGE_KEY, "Konto zostało pomyślnie utworzone!");
        return "redirect:/home/register/userList";
    }



    @GetMapping("/userList")
    public String userList(ModelMap map, @ModelAttribute("message") String message) {
        map.addAttribute("users", userService.findAll());
        map.addAttribute("categories", categoryService.findAll());
        if (!message.isBlank()) {
            map.addAttribute("message", message);
        }
        return "user-list";
    }


}
