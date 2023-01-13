package com.example.serwisaukcyjny.controller;

import com.example.serwisaukcyjny.Util.FileUploadUtil;
import com.example.serwisaukcyjny.form.CreateAuctionForm;
import com.example.serwisaukcyjny.mapper.AuctionMapper;
import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Category;
import com.example.serwisaukcyjny.model.repositories.CategoryRepository;
import com.example.serwisaukcyjny.model.services.AuctionService;
import com.example.serwisaukcyjny.model.services.CategoryService;
import com.example.serwisaukcyjny.model.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/home/auction")
public class AuctionController {

    private final AuctionService auctionService;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping
    public String create(ModelMap map) {
        map.addAttribute("auction", new CreateAuctionForm());
        map.addAttribute("categories", categoryRepository.findAll());
        return "creator";
    }

    @PostMapping
    public String handleCreate(@ModelAttribute("auction") @Valid CreateAuctionForm form, Errors errors, RedirectAttributes redirectAttributes, ModelMap map, Principal principal, @RequestParam("image")MultipartFile multipartFile) throws IOException {

        if (errors.hasErrors()) {
            map.addAttribute("categories", categoryRepository.findAll());
            return "creator";
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        form.setPhotos(fileName);
        String uploadDir = "/src/main/resources/static/photos/";
        Path currentPath = Paths.get("."); //on Windows Paths.get(".")
        Path absolutePath = currentPath.toAbsolutePath();
        Path filePath = Paths.get(absolutePath + uploadDir + fileName);
        Files.write(filePath, multipartFile.getBytes());
        auctionService.save(AuctionMapper.toEntity(form, userService.findByUserName(principal.getName()).get()));
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

    @GetMapping("/{id}")
    public String AuctionPage(@PathVariable Long id, ModelMap map,@ModelAttribute("message") String message) {
        map.addAttribute("auction", auctionService.findByID(id));
        map.addAttribute("categories", categoryRepository.findAll());
        if (!message.isBlank()) {
            map.addAttribute("message", message);
        }
        return "auction-page";
    }

}
