package com.example.serwisaukcyjny.model.services;


import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Observer;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.repositories.ObserverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ObserverService {

    private final ObserverRepository repository;

    public Observer save(Observer observer) {
        return repository.save(observer);
    }

    public Optional<Observer> findByUser(User user) {
        return repository.findByUser(user);
    }

    public boolean existByUser(User user) {
        return repository.existsByUser(user);
    }

    public Set<Observer> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    public void saveOrCreateNewObserver(User loggedUser, Auction auction) {
        if (existByUser(loggedUser)) {
            Observer loggedUserObserver = findByUser(loggedUser).get();
            loggedUserObserver.getAuctions().add(auction);
            save(loggedUserObserver);
        } else {
            Set<Auction> auctions = new HashSet<>();
            auctions.add(auction);
            save(new Observer(auctions, loggedUser));
        }
    }
}
