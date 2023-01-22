package com.example.serwisaukcyjny.repository;

import com.example.serwisaukcyjny.SerwisAukcyjnyApplicationTests;
import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Observer;
import com.example.serwisaukcyjny.model.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ObserverRepositoryTest extends SerwisAukcyjnyApplicationTests {

    @Test
    void shouldFindByUser() {
        //given
        User marta = userRepository.findByUserName("Marta").get();
        User ania = userRepository.findByUserName("Ania").get();
        User tomasz = userRepository.findByUserName("Tomasz").get();
        List<Auction> auctions = auctionRepository.findAllByTitle("Komputer");
        Set<Auction> auctionSet = new HashSet<>(auctions);
        observerRepository.save(new Observer(auctionSet, marta));
        //when
        Observer observer = observerRepository.findByUser(marta).get();

        //then
        assertEquals(observer.getUser().getId(), marta.getId());
        assertNotNull(observer);


    }

    @Test
    void shouldExistByUser() {
        //given
        User tomasz = userRepository.findByUserName("Tomasz").get();
        List<Auction> auctions = auctionRepository.findAllByTitle("Komputer");
        Set<Auction> auctionSet = new HashSet<>(auctions);
        observerRepository.save(new Observer(auctionSet, tomasz));
        //when
        boolean isPresent = observerRepository.existsByUser(tomasz);

        //then
        assertTrue(isPresent);

    }

    @Test
    void shouldFindAllByUser() {
        //given
        User tomasz = userRepository.findByUserName("Tomasz").get();
        List<Auction> auctions = auctionRepository.findAllByTitle("Komputer");
        List<Auction> auctions2 = auctionRepository.findAllByTitle("Meble");
        Set<Auction> auctionSet = new HashSet<>(auctions);
        Set<Auction> auctionSet2 = new HashSet<>(auctions2);
        observerRepository.save(new Observer(auctionSet, tomasz));
        observerRepository.save(new Observer(auctionSet2, tomasz));
        //when
        Set<Observer> observers = observerRepository.findAllByUser(tomasz);

        //then
        assertEquals(observers.size(), 2);

    }

    @Test
    void shouldFindAll() {
        //given
        User ania = userRepository.findByUserName("Ania").get();
        User marta = userRepository.findByUserName("Marta").get();
        User tomasz = userRepository.findByUserName("Tomasz").get();

        List<Auction> auctions = auctionRepository.findAllByTitle("Komputer");
        List<Auction> auctions2 = auctionRepository.findAllByTitle("Meble");

        Set<Auction> auctionSet = new HashSet<>(auctions);
        Set<Auction> auctionSet2 = new HashSet<>(auctions2);

        observerRepository.save(new Observer(auctionSet, tomasz));
        observerRepository.save(new Observer(auctionSet2, tomasz));
        observerRepository.save(new Observer(auctionSet2, marta));
        observerRepository.save(new Observer(auctionSet, marta));
        observerRepository.save(new Observer(auctionSet2, ania));
        observerRepository.save(new Observer(auctionSet, ania));
        //when

        List<Observer> observers = observerRepository.findAll();
        //then
        assertEquals(observers.size(), 6);


    }
}
