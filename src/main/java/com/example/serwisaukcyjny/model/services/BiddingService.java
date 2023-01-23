package com.example.serwisaukcyjny.model.services;


import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Bidding;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.repositories.BiddigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BiddingService {

    private final BiddigRepository repository;

    public Bidding save(Bidding bidding, User loggedUser, Auction auction) {

        if (repository.existsByAuctionAndUser(auction, loggedUser)) {
            Bidding userBidding = repository.findByAuctionAndUser(auction, loggedUser);
            userBidding.setPrice(bidding.getPrice());
            return repository.save(userBidding);
        } else {
            return repository.save(bidding);
        }
    }

    public List<Bidding> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    public List<Bidding> findAll() {
        return repository.findAll();
    }


    public List<Bidding> findAllByAuction(Auction auction) {
        return repository.findAllByAuction(auction);
    }


    public void deleteAllByAuction(Auction auction) {
        repository.deleteAllByAuction(auction);
    }


}
