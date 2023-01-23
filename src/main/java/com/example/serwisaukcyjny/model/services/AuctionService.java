package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.*;
import com.example.serwisaukcyjny.model.Observer;
import com.example.serwisaukcyjny.model.repositories.AuctionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableScheduling
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final PurchaseService purchaseService;
    private final ObserverService observerService;
    private final BiddingService biddingService;
    private final UserService userService;


    public List<Auction> findAll() {
        return StreamSupport.stream(auctionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Auction save(Auction auction) {
        return auctionRepository.save(auction);
    }

    public boolean delete(Long id, User loggedUser) {
        Auction auction = auctionRepository.findById(id).get();
        if (auction.getUser() == loggedUser && !isBidded(id) && !isAlreadyBought(id)) {
            Iterable<Observer> observers = observerService.findAll();
            observers.forEach(observer -> observer.getAuctions().remove(auctionRepository.findById(id).get()));
            auctionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean isBidded(long id) {
        List<Bidding> biddings = biddingService.findAll();
        Auction auction = auctionRepository.findById(id).get();
        return biddings.stream().map(Bidding::getAuction).toList().contains(auction);
    }


    public Auction findByID(Long id) {
        return auctionRepository.findById(id).orElseThrow(() -> new RuntimeException("Auction with id " + id + " not found!"));
    }

    public List<Auction> findAllOpenAuctionsByCategory(Category category) {
        return findAllOpenAuctions().stream().filter(auction -> auction.getCategory() == category).collect(Collectors.toList());

    }

    public List<Auction> findAllOpenAuctions() {
        List<Auction> auctions = findAll();
        auctions.removeAll(findAllPurchasedAuctions());
        return auctions;
    }

    public List<Auction> findAllOpenAuctionsByUser(User loggedUser) {
        List<Auction> auctions = findAllByUser(loggedUser);
        auctions.removeAll(findAllPurchasedAuctions());
        return auctions;
    }

    public List<Auction> findAllSoldAuctionsByUser(User loggedUser) {
        return findAllPurchasedAuctions().stream().filter(auction -> auction.getUser() == loggedUser).collect(Collectors.toList());
    }

    public List<Auction> findAllByUser(User loggedUser) {
        return auctionRepository.findAllByUser(loggedUser);
    }

    public List<Auction> findAllPurchasedAuctionsByUser(User loggedUser) {
        List<Purchase> purchases = purchaseService.findAllByUser(loggedUser);
        ArrayList<Auction> auctions = new ArrayList<>();
        for (Purchase purchase : purchases) {
            auctions.add(purchase.getAuction());
        }
        return auctions;
    }

    public List<Auction> findAllPurchasedAuctions() {
        return purchaseService.findAll().stream().map(Purchase::getAuction).collect(Collectors.toList());
    }

    public Set<Auction> findFollowedAuctions(User loggedUser) {
        if (observerService.findByUser(loggedUser).isPresent()) {
            return observerService.findByUser(loggedUser).get().getAuctions();
        }
        return new HashSet<>();
    }

    public boolean isFollowedByUser(User loggedUser, Long id) {
        if (observerService.findByUser(loggedUser).isPresent()) {
            return observerService.findByUser(loggedUser).get().getAuctions().contains(auctionRepository.findById(id).get());
        } else {
            return false;
        }
    }

    public boolean isAlreadyBought(Long id) {
        return findAllPurchasedAuctions().contains(findByID(id));
    }

    public List<Auction> findAllBiddingAuctionsByUser(User loggedUser) {
        List<Bidding> bindings = biddingService.findAllByUser(loggedUser);
        List<Auction> auctions = new ArrayList<>();
        for (Bidding bidding : bindings) {
            auctions.add(bidding.getAuction());
        }
        return auctions;
    }

    public List<Auction> findTenNewAuctions() {
        return findAllOpenAuctions().stream().sorted(Comparator.comparing(Auction::getDateOfIssue).reversed()).limit(10L).collect(Collectors.toList());
    }

    public List<Auction> findTenEndingAuctions() {
        return findAllOpenAuctions().stream().sorted(Comparator.comparing(Auction::getEndDate)).limit(10L).collect(Collectors.toList());
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void endAuctionsAfterTime() {
        List<Auction> auctions = findAllOpenAuctions();
        if (auctions != null) {
            for (Auction auction : auctions) {
                List<Bidding> biddings = biddingService.findAllByAuction(auction);
                if (auction.getEndDate().isBefore(LocalDateTime.now()) && !biddings.isEmpty() && !purchaseService.existByAuction(auction)) {
                    Bidding bidding = biddingService.findAllByAuction(auction).stream().max(Comparator.comparing(Bidding::getPrice)).get();
                    purchaseService.save(bidding.toPurchase());
                } else if (auction.getEndDate().isBefore(LocalDateTime.now()) && biddings.isEmpty() && !purchaseService.existByAuction(auction)) {
                    purchaseService.save(new Purchase(auction, userService.findByLogin("Admin@admin.pl").get(), auction.getBuyNowPrice()));
                }
            }
        }
    }

}
