package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.*;
import com.example.serwisaukcyjny.model.Observer;
import com.example.serwisaukcyjny.model.repositories.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final PurchaseService purchaseService;
    private final ObserverService observerService;

    private final BiddingService biddingService;

    public List<Auction> findAll() {
        return StreamSupport.stream(auctionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Auction save(Auction auction) {
        return auctionRepository.save(auction);
    }

    public boolean delete(Long id, User loggedUser) {
        Auction auction = auctionRepository.findById(id).get();
        if (auction.getUser() == loggedUser && !isBidded(id)){
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

    public List<Auction> findAllByCategory(Category category) {
        return auctionRepository.findAllByCategory(category);
    }

    public List<Auction> findAllOpenAuctionsByCategory(Category category) {
        return findAllOpenAuctions().stream().filter(auction -> auction.getCategory() == category).collect(Collectors.toList());

    }

    public List<Auction> findAllOpenAuctions() {
        List<Purchase> purchases = purchaseService.findAll();
        List<Auction> auctions = findAll();
        for (Purchase purchase : purchases) {
            auctions.remove(purchase.getAuction());
        }
        return auctions;
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
        List<Purchase> purchases = purchaseService.findAll();
        ArrayList<Auction> auctions = new ArrayList<>();
        for (Purchase purchase : purchases) {
            auctions.add(purchase.getAuction());
        }
        return auctions;
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
        List<Auction> boughtAuctions = findAllPurchasedAuctions();
        return boughtAuctions.contains(findByID(id));
    }

    public List<Auction> findAllBiddingAuctionsByUser(User loggedUser) {
        List<Bidding> bindings = biddingService.findAllByUser(loggedUser);
        List<Auction> auctions = new ArrayList<>();
        for (Bidding bidding : bindings){
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

}
