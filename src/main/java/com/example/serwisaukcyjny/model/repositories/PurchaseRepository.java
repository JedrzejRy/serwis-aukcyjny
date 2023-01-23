package com.example.serwisaukcyjny.model.repositories;

import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Purchase;
import com.example.serwisaukcyjny.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    List<Purchase> findAllByUser(User user);

    Optional<Purchase> findByAuction(Auction auction);

    boolean existsByAuction(Auction auction);

}
