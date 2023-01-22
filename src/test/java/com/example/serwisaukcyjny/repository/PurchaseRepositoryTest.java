package com.example.serwisaukcyjny.repository;

import com.example.serwisaukcyjny.SerwisAukcyjnyApplicationTests;
import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Purchase;
import com.example.serwisaukcyjny.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseRepositoryTest extends SerwisAukcyjnyApplicationTests {
    @Test
    void shouldFindALLByUser(){
        //given

        //when
        User user = userRepository.findByUserName("Ania").get();
        List<Purchase> purchases = purchaseRepository.findAllByUser(user);
        //then
        assertEquals(purchases.get(0).getUser().getLogin(), user.getLogin());

    }

    @Test
    void ShouldFindByAuction() {
        //given

        //when
        Auction auction = auctionRepository.findAllByTitle("Komputer").get(0);
        Purchase purchase = purchaseRepository.findByAuction(auction).get();

        //then
        assertEquals(purchase.getAuction().getAuctionId(), auction.getAuctionId());
    }

    @Test
    void ShouldExistByAuction(){
        //given

        //when
        Auction auction = auctionRepository.findAllByTitle("Komputer").get(0);
        boolean logicValue = purchaseRepository.existsByAuction(auction);

        //then
        assertTrue(logicValue);
    }

}
