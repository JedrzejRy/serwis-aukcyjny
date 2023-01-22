package com.example.serwisaukcyjny.model.repositories;

import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Bidding;
import com.example.serwisaukcyjny.model.Observer;
import com.example.serwisaukcyjny.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BiddigRepository extends CrudRepository<Bidding, Long> {

  List<Bidding> findAllByUser(User user);

  List<Bidding> findAll();

  List<Bidding> findAllByAuctionOrderByPrice(Auction auction);

  void deleteAllByAuction(Auction auction);

  List<Bidding> findAllByAuction(Auction auction);

  Bidding findByAuctionAndUser(Auction auction, User  user);
  boolean existsByAuctionAndUser(Auction auction, User  user);
}
