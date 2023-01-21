package com.example.serwisaukcyjny.controller;

import com.example.serwisaukcyjny.authentication.IAuthenticationFacade;
import com.example.serwisaukcyjny.form.CreateAuctionForm;
import com.example.serwisaukcyjny.form.CreateBiddingForm;
import com.example.serwisaukcyjny.mapper.AuctionMapper;
import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Category;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.repositories.CategoryRepository;
import com.example.serwisaukcyjny.model.services.AuctionService;
import com.example.serwisaukcyjny.model.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/home/auction")
public class AuctionController {

    private final AuctionService auctionService;
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final IAuthenticationFacade authenticationFacade;

    @GetMapping
    public String create(ModelMap map) {
        map.addAttribute("auction", new CreateAuctionForm());
        map.addAttribute("categories", categoryRepository.findAll());
        return "creator";
    }

    @PostMapping
    public String handleCreate(@ModelAttribute("auction") @Valid CreateAuctionForm form, Errors errors, RedirectAttributes redirectAttributes, ModelMap map, Principal principal, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (errors.hasErrors()) {
            map.addAttribute("categories", categoryRepository.findAll());
            return "creator";
        }

        if (multipartFile.isEmpty() || !form.isImage(multipartFile)) {
            redirectAttributes.addFlashAttribute("wrongFileExtension",
                    form.wrongFileExtensionMessage());
            return "redirect:/home/auction";
        }

        form.setPhotos(multipartFile);
        User loggedUser = userService.findByLogin(principal.getName()).get();

        auctionService.save(AuctionMapper.toEntity(form, loggedUser, loggedUser.getLocalization()));
        redirectAttributes.addAttribute("message", "Aukcja o tytule " + form.getTitle() + " została pomyślnie dodana!");


        return "redirect:/home/auction/list";
    }


    @GetMapping("/list")
    public String list(ModelMap map, @ModelAttribute("message") String message) {
        map.addAttribute("auctions", auctionService.findAllOpenAuctions());
        map.addAttribute("categories", categoryRepository.findAll());
        if (!message.isBlank()) {
            map.addAttribute("message", message);
        }
        return "auction-list";
    }


    @GetMapping("/list/{id}")
    public String auctionByCategoryList(@PathVariable int id, ModelMap map, @ModelAttribute("message") String message) {
        List<Category> categoryList = (List<Category>) categoryRepository.findAll();
        map.addAttribute("auctions", auctionService.findAllOpenAuctionsByCategory(categoryList.get(id - 1)));
        map.addAttribute("categories", categoryRepository.findAll());
        if (!message.isBlank()) {
            map.addAttribute("message", message);
        }
        return "auction-list";
    }

    @GetMapping("/{id}")
    public String AuctionPage(@PathVariable Long id, ModelMap map, @ModelAttribute("message") String message, Principal principal) {

        Authentication authentication = authenticationFacade.getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User loggedUser = userService.findByLogin(principal.getName()).get();
            map.addAttribute("loggedUser", loggedUser);
            map.addAttribute("isFollowed", auctionService.isFollowedByUser(loggedUser,id));
        }
        map.addAttribute("bidding", new CreateBiddingForm());
        map.addAttribute("auction", auctionService.findByID(id));
        map.addAttribute("categories", categoryRepository.findAll());
        map.addAttribute("isBought", auctionService.isAlreadyBought(id));
        if (!message.isBlank()) {
            map.addAttribute("message", message);
        }
        return "auction-page";
    }

    @GetMapping("/purchased")
    public String purchasedList(ModelMap map, @ModelAttribute("message") String message, Principal principal) {
        User loggedUser = userService.findByLogin(principal.getName()).get();
        List<Auction> auctions = auctionService.findAllPurchasedAuctionsByUser(loggedUser);
        map.addAttribute("auctions", auctions);
        map.addAttribute("categories", categoryRepository.findAll());
        if (!message.isBlank()) {
            map.addAttribute("message", message);
        }
        return "auction-list";
    }

    @GetMapping("/my-auctions")
    public String myAuctionsList(ModelMap map, @ModelAttribute("message") String message, Principal principal) {
        User loggedUser = userService.findByLogin(principal.getName()).get();
        List<Auction> auctions = auctionService.findAllByUser(loggedUser);
        map.addAttribute("auctions", auctions);
        map.addAttribute("categories", categoryRepository.findAll());
        if (!message.isBlank()) {
            map.addAttribute("message", message);
        }
        return "auction-list";
    }

    @GetMapping("/followed")
    public String followedAuctions(ModelMap map, @ModelAttribute("message") String message, Principal principal) {
        User loggedUser = userService.findByLogin(principal.getName()).get();
        Set<Auction> auctions = auctionService.findFollowedAuctions(loggedUser);
        map.addAttribute("auctions", auctions);
        map.addAttribute("categories", categoryRepository.findAll());
        if (!message.isBlank()) {
            map.addAttribute("message", message);
        }
        return "auction-list";
    }

    @GetMapping("/user-page-view")
    public String showUserPageAuctions(ModelMap map, @ModelAttribute("message") String message, Principal principal) {
        User loggedUser = userService.findByLogin(principal.getName()).get();
        map.addAttribute("loggedUser", loggedUser);
        map.addAttribute("myAuctions", auctionService.findAllByUser(loggedUser));
        map.addAttribute("boughtAuctions", auctionService.findAllPurchasedAuctionsByUser(loggedUser));
        map.addAttribute("followedAuctions", auctionService.findFollowedAuctions(loggedUser));
        map.addAttribute("categories", categoryRepository.findAll());
        if (!message.isBlank()) {
            map.addAttribute("message", message);
        }

        return "user-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuction(@PathVariable("id") Long id ,Principal principal, RedirectAttributes redirectAttributes, @ModelAttribute("message") String message){
        User loggedUser = userService.findByLogin(principal.getName()).get();
        if (auctionService.delete(id,loggedUser)){
            redirectAttributes.addAttribute("message", "Aukcja pomyśle usunięta");
        } else {
            redirectAttributes.addAttribute("message", "Nie można usunąc aukcji która jest licytowana!");
        }
        return "redirect:/home/auction/user-page-view";
    }

}
