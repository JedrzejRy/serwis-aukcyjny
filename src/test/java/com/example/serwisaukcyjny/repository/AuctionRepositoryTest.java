package com.example.serwisaukcyjny.repository;

import com.example.serwisaukcyjny.SerwisAukcyjnyApplicationTests;
import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Category;
import com.example.serwisaukcyjny.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

public class AuctionRepositoryTest extends SerwisAukcyjnyApplicationTests {

    @Test
    void ShouldFindAllByTitle() {
        //given


        //when
        List<Auction> auctions = auctionRepository.findAllByTitle("Komputer");

        //then
        assertEquals(auctions.size(), 2);
    }

    @Test
    void shouldFindAllByCategory() {
        //given


        //when
        Category category = categoryRepository.findByCategoryName("Meble");
        List<Auction> auctions = auctionRepository.findAllByCategory(category);
        //then
        assertEquals(auctions.size(), 1);
    }

    @Test
    void shouldFindAllByUser() {
        //given

        //when
        User user  = userRepository.findByUserName("Zosia").get();
        List<Auction> auctions = auctionRepository.findAllByUser(user);

        //then
        assertEquals(auctions.size(), 2);
    }
}
