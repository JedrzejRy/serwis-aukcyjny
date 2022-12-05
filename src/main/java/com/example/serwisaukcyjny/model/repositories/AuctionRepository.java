package com.example.serwisaukcyjny.model.repositories;

import com.example.serwisaukcyjny.model.Auction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends CrudRepository<Auction,Long> {
    List<Auction> findAllByTitle(String title);
    List<Auction> findAllByDateOfIssue(String date);
}
