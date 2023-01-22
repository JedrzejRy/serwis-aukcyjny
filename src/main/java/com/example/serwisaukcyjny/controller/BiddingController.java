package com.example.serwisaukcyjny.controller;

import com.example.serwisaukcyjny.form.CreateBiddingForm;
import com.example.serwisaukcyjny.mapper.BiddingMapper;
import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Bidding;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.services.AuctionService;
import com.example.serwisaukcyjny.model.services.BiddingService;
import com.example.serwisaukcyjny.model.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home/auction/bid")
public class BiddingController {
    private final UserService userService;
    private final AuctionService auctionService;
    private final BiddingService biddingService;

    @PostMapping("/{id}")
    public String handleCreate(@ModelAttribute("bidding") @Valid CreateBiddingForm form, @PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes, Errors errors) {

        User loggedUser = userService.findByLogin(principal.getName()).get();
        Auction currentAuction = auctionService.findByID(id);
        Bidding newBidding = BiddingMapper.ToEntity(loggedUser, currentAuction, BigDecimal.valueOf(form.getPrice()));

        if (newBidding.getPrice().intValue() > currentAuction.getMinimumPrice().intValue()
                && newBidding.getPrice().intValue() < currentAuction.getBuyNowPrice().intValue()) {
            currentAuction.setMinimumPrice(newBidding.getPrice());
            biddingService.save(newBidding,loggedUser,currentAuction);
            redirectAttributes.addAttribute("message", "Założono licytację o wysokości " + newBidding.getPrice() + "PLN");
        } else if (newBidding.getPrice().intValue() > currentAuction.getBuyNowPrice().intValue()) {
            redirectAttributes.addAttribute("message", "Kwota licytacji musi być niższa od ceny kup teraz");
        } else {
            redirectAttributes.addAttribute("message", "Kwota licytacji musi być większa od ceny minimalnej");
        }

        return "redirect:/home/auction/" + id;

    }

}
