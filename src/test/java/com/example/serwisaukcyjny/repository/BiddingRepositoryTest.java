package com.example.serwisaukcyjny.repository;

import com.example.serwisaukcyjny.SerwisAukcyjnyApplicationTests;
import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Bidding;
import com.example.serwisaukcyjny.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BiddingRepositoryTest extends SerwisAukcyjnyApplicationTests {
    @Test
    void shouldFindAllByUser() {
        //given
        User user = userRepository.findByUserName("Marta").get();
        List<Auction> auctions = auctionRepository.findAllByTitle("Komputer");
        biddigRepository.save(new Bidding(BigDecimal.valueOf(600L),auctions.get(0),user));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(600L),auctions.get(1),user));
        //when
        List<Bidding> biddings = biddigRepository.findAllByUser(user);
        //then
        assertEquals(biddings.size(), 2);
    }

    @Test
    void shouldFindAll() {
        //given
        User marta = userRepository.findByUserName("Marta").get();
        User ania = userRepository.findByUserName("Ania").get();
        User tomasz = userRepository.findByUserName("Tomasz").get();
        List<Auction> auctions = auctionRepository.findAllByTitle("Komputer");
        biddigRepository.save(new Bidding(BigDecimal.valueOf(600L),auctions.get(0),marta));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(600L),auctions.get(1),marta));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(610L),auctions.get(0),ania));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(610L),auctions.get(1),ania));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(620L),auctions.get(0),tomasz));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(620L),auctions.get(1),tomasz));
        //when

        List<Bidding> biddings = biddigRepository.findAll();

        //then
        assertEquals(biddings.size(), 6);
    }

    @Test
    void shouldFindAllByAuctionAndOrderByPrice() {
        //given
        User marta = userRepository.findByUserName("Marta").get();
        User ania = userRepository.findByUserName("Ania").get();
        User tomasz = userRepository.findByUserName("Tomasz").get();
        List<Auction> auctions = auctionRepository.findAllByTitle("Komputer");
        biddigRepository.save(new Bidding(BigDecimal.valueOf(600L),auctions.get(0),marta));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(700L),auctions.get(0),ania));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(800L),auctions.get(0),tomasz));

        //when
        List<Bidding> biddings = biddigRepository.findAllByAuctionOrderByPrice(auctions.get(0));
        Bidding highestBidding = biddings.get(2);
        //then
        assertEquals(highestBidding.getPrice().intValue(), 800);
    }

    @Test
    @Transactional
    void shouldDeleteAllByAuction() {
        //given
        User marta = userRepository.findByUserName("Marta").get();
        User ania = userRepository.findByUserName("Ania").get();
        User tomasz = userRepository.findByUserName("Tomasz").get();
        List<Auction> auctions = auctionRepository.findAllByTitle("Komputer");
        biddigRepository.save(new Bidding(BigDecimal.valueOf(600L),auctions.get(0),marta));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(600L),auctions.get(1),marta));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(610L),auctions.get(0),ania));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(610L),auctions.get(1),ania));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(620L),auctions.get(0),tomasz));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(620L),auctions.get(1),tomasz));
        //when
        biddigRepository.deleteAllByAuction(auctions.get(0));
        List<Bidding> deletedBiddings = biddigRepository.findAllByAuction(auctions.get(0));
        List<Bidding> safeBiddings = biddigRepository.findAllByAuction(auctions.get(1));
        //then
        assertEquals(deletedBiddings.size(), 0);
        assertEquals(safeBiddings.size(), 3);
    }

    @Test
    void shouldFindAllByAuction() {
        //given
        User marta = userRepository.findByUserName("Marta").get();
        User ania = userRepository.findByUserName("Ania").get();
        User tomasz = userRepository.findByUserName("Tomasz").get();
        List<Auction> auctions = auctionRepository.findAllByTitle("Komputer");
        biddigRepository.save(new Bidding(BigDecimal.valueOf(600L),auctions.get(0),marta));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(700L),auctions.get(0),ania));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(800L),auctions.get(0),tomasz));

        //when
        List<Bidding> biddings = biddigRepository.findAllByAuction(auctions.get(0));

        //then
        assertEquals(biddings.size(), 3);
    }

    @Test
    void shouldFindByAuctionAndUser() {
        //given
        User marta = userRepository.findByUserName("Marta").get();
        User ania = userRepository.findByUserName("Ania").get();
        User tomasz = userRepository.findByUserName("Tomasz").get();
        List<Auction> auctions = auctionRepository.findAllByTitle("Komputer");
        biddigRepository.save(new Bidding(BigDecimal.valueOf(600L),auctions.get(0),marta));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(700L),auctions.get(0),ania));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(800L),auctions.get(0),tomasz));

        //when
        Bidding bidding = biddigRepository.findByAuctionAndUser(auctions.get(0), ania);
        //then
        assertNotNull(bidding);
        assertEquals(bidding.getUser().getId(), ania.getId());
    }

    @Test
    void shouldExistByAuctionAndUser() {
        //given
        User marta = userRepository.findByUserName("Marta").get();
        User ania = userRepository.findByUserName("Ania").get();
        User tomasz = userRepository.findByUserName("Tomasz").get();
        List<Auction> auctions = auctionRepository.findAllByTitle("Komputer");
        biddigRepository.save(new Bidding(BigDecimal.valueOf(600L),auctions.get(0),marta));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(700L),auctions.get(0),ania));
        biddigRepository.save(new Bidding(BigDecimal.valueOf(800L),auctions.get(0),tomasz));

        //when
        boolean isBidding = biddigRepository.existsByAuctionAndUser(auctions.get(0), ania);
        //then
        assertTrue(isBidding);

    }

}
