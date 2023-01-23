package com.example.serwisaukcyjny.controller;

import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Purchase;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.services.AuctionService;
import com.example.serwisaukcyjny.model.services.PurchaseService;
import com.example.serwisaukcyjny.model.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/home/auction/buy")
public class PurchaseController {
   private final UserService userService;
   private final AuctionService auctionService;
   private final PurchaseService purchaseService;


    @PostMapping("/{id}")
    public String handleCreate(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes) {

        User loggedUser = userService.findByLogin(principal.getName()).get();
        Auction currentAuction = auctionService.findByID(id);
        currentAuction.setEndDate(LocalDateTime.now());
        purchaseService.save(new Purchase(currentAuction, loggedUser, currentAuction.getBuyNowPrice()));

        redirectAttributes.addAttribute("message", "Aukcja pomyśle kupiona!");
        return "redirect:/home/auction/" + id;
    }

}
