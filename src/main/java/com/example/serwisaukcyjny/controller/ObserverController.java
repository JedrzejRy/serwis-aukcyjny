package com.example.serwisaukcyjny.controller;


import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Observer;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.services.AuctionService;
import com.example.serwisaukcyjny.model.services.ObserverService;
import com.example.serwisaukcyjny.model.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/home/auction/follow")
public class ObserverController {

    private final UserService userService;
    private final AuctionService auctionService;
    private final ObserverService observerService;

    @PostMapping("/{id}")
    public String handleCreate(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes) {

        User loggedUser = userService.findByLogin(principal.getName()).get();
        Auction currentAuction = auctionService.findByID(id);
        observerService.saveOrCreateNewObserver(loggedUser, currentAuction);

        redirectAttributes.addAttribute("message", "Aukcja dodana do obserwowanych");
        return "redirect:/home/auction/" + id;
    }

    @PostMapping("/unfollow/{id}")
    public String unfollowAuction(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes) {
        User loggedUser = userService.findByLogin(principal.getName()).get();
        Auction currentAuction = auctionService.findByID(id);
        Observer userObserver = observerService.findByUser(loggedUser).get();
        userObserver.getAuctions().remove(currentAuction);
        observerService.save(userObserver);

        redirectAttributes.addAttribute("message", "Aukcja usunięta z obserwowanych!");
        return "redirect:/home/auction/" + id;
    }
}
