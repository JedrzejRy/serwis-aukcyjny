package com.example.serwisaukcyjny.model.repositories;

import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Category;
import com.example.serwisaukcyjny.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuctionRepository extends CrudRepository<Auction,Long> {
    List<Auction> findAllByTitle(String title);

    List<Auction> findAllByCategory(Category category);

    List<Auction> findAllByUser(User user);
}
