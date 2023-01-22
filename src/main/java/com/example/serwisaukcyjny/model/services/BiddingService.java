package com.example.serwisaukcyjny.model.services;


import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Bidding;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.repositories.BiddigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BiddingService {

    private final BiddigRepository repository;

    public Bidding save(Bidding bidding) {
        return repository.save(bidding);
    }

    public List<Bidding> findAllByUser(User user){
        return repository.findAllByUser(user);
    }

    public List<Bidding> findAll() {
        return repository.findAll();
    }

    public Optional<Bidding> findHighestAuctionBidder(Auction auction){
        if (!repository.findAllByAuctionOrderByPrice(auction).isEmpty()){
            return Optional.of(repository.findAllByAuctionOrderByPrice(auction).get(0));
        }
        else{
          return Optional.empty();
        }
    }

    public List<Bidding> findAllByAuction(Auction auction){
        return repository.findAllByAuction(auction);
    }


    public void deleteAllByAuction(Auction auction){
        repository.deleteAllByAuction(auction);
    }


}
