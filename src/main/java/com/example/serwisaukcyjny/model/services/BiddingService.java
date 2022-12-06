package com.example.serwisaukcyjny.model.services;


import com.example.serwisaukcyjny.model.Bidding;
import com.example.serwisaukcyjny.model.repositories.BiddigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BiddingService {

    private final BiddigRepository repository;

    public Bidding save(Bidding bidding) {return repository.save(bidding);}

}
