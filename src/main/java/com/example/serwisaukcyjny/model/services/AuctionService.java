package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.repositories.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;

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

}
