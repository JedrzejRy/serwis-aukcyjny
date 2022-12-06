package com.example.serwisaukcyjny.controller;

import com.example.serwisaukcyjny.form.CreateUserForm;
import com.example.serwisaukcyjny.mapper.UserMapper;
import com.example.serwisaukcyjny.model.UserMenu.Type;
import com.example.serwisaukcyjny.model.UserMenu.User;
import com.example.serwisaukcyjny.model.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/home/register")
public class RegisterController {

    private static final String MESSAGE_KEY = "message";
    private final UserService userService;

    @GetMapping
    public String register(ModelMap map) {
        map.addAttribute("user", new CreateUserForm());
        map.addAttribute("types", Type.values());
        return "registered";
    }

    @PostMapping
    public String handleCreate(@ModelAttribute("user") @Valid CreateUserForm form, Errors errors, RedirectAttributes redirectAttributes, ModelMap map) {
        log.info("Creating user from form{}", form);
        if (errors.hasErrors()) {
            map.addAttribute("types", Type.values());
            return "registered";
        }
        userService.save(UserMapper.toEntity(form));
        redirectAttributes.addAttribute(MESSAGE_KEY, "Konto zostało pomyślnie utworzone!");
        return "redirect:/home/register/userList";
    }

    @GetMapping("/userList")
    public String userList(ModelMap map, @ModelAttribute("message") String message) {
        map.addAttribute("users", userService.findAll());
        if (!message.isBlank()) {
            map.addAttribute("message", message);
        }
        return "user-list";
    }
}
