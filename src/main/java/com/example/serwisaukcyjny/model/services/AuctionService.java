package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Category;
import com.example.serwisaukcyjny.model.Purchase;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.repositories.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final PurchaseService purchaseService;

    public List<Auction> findAll() {
        return StreamSupport.stream(auctionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Auction save(Auction auction) {
        return auctionRepository.save(auction);
    }

    public void delete(Long id) {
        auctionRepository.deleteById(id);
    }

    public Auction findByID(Long id) {
        return auctionRepository.findById(id).orElseThrow(() -> new RuntimeException("Auction with id " + id + " not found!"));
    }

    public List<Auction> findAllByCategory (Category category) {
        return auctionRepository.findAllByCategory(category);
    }

    public List<Auction> findAllOpenAuctionsByCategory(Category category) {
        return findAllOpenAuctions().stream().filter(auction -> auction.getCategory() == category).collect(Collectors.toList());

    }

    public List<Auction> findAllOpenAuctions(){
        List<Purchase> purchases = (List<Purchase>) purchaseService.findAll();
        List<Auction> auctions = findAll();
        for(Purchase purchase: purchases){
            auctions.remove(purchase.getAuction());
        }
        return auctions;
    }

    public List<Auction> findAllByUser(User loggedUser){
        return auctionRepository.findAllByUser(loggedUser);
    }

    public List<Auction> findAllPurchasedAuctionsByUser(User loggedUser){
        List<Purchase> purchases = purchaseService.findAllByUser(loggedUser);
        ArrayList<Auction> auctions = new ArrayList<>();
        for(Purchase purchase: purchases){
            auctions.add(purchase.getAuction());
        }
        return auctions;
    }

    public List<Auction> findAllPurchasedAuctions(){
        List<Purchase> purchases = purchaseService.findAll();
        ArrayList<Auction> auctions = new ArrayList<>();
        for(Purchase purchase: purchases){
            auctions.add(purchase.getAuction());
        }
        return auctions;
    }

}
