package com.example.serwisaukcyjny.controller;

import com.example.serwisaukcyjny.authentication.IAuthenticationFacade;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.services.AuctionService;
import com.example.serwisaukcyjny.model.services.CategoryService;
import com.example.serwisaukcyjny.model.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final CategoryService categoryService;
    private final AuctionService auctionService;
    private final UserService userService;
    private final IAuthenticationFacade authenticationFacade;


    @GetMapping
    public String categoriesList(ModelMap map, Principal principal) {
        map.addAttribute("categories", categoryService.findAll());
        Authentication authentication = authenticationFacade.getAuthentication();
        map.addAttribute("purchasedAuctions", auctionService.findAllPurchasedAuctions().stream().limit(10).collect(Collectors.toList()));
        map.addAttribute("newAuctions", auctionService.findTenNewAuctions());
        map.addAttribute("endingAuctions", auctionService.findTenEndingAuctions());

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User loggedUser = userService.findByLogin(principal.getName()).get();
            map.addAttribute("loggedUser", loggedUser);
            map.addAttribute("userAuctions", auctionService.findAllOpenAuctionsByUser(loggedUser).stream().limit(10).collect(Collectors.toList()));
            map.addAttribute("followedAuctions", auctionService.findFollowedAuctions(loggedUser).stream().limit(10).collect(Collectors.toList()));
            map.addAttribute("biddingAuctions", auctionService.findAllBiddingAuctionsByUser(loggedUser).stream().limit(10).collect(Collectors.toList()));
        }

        return "home";
    }

}
