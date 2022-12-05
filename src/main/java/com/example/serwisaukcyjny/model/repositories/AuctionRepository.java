package com.example.serwisaukcyjny.model.repositories;

import com.example.serwisaukcyjny.model.Auction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuctionRepository extends CrudRepository<Auction,Long> {
    List<Auction> findAllByTitle(String title);
    List<Auction> findAllByDateOfIssue(String date);
}
