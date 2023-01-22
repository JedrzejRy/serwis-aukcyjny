package com.example.serwisaukcyjny.model.services;


import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Observer;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.repositories.ObserverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Iterable<Observer> findAll() {
        return repository.findAll();
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
    public void deleteByAuction(Auction auction) {
        Iterable<Observer> observers = repository.findAll();
        observers.forEach(observer -> observer.getAuctions().remove(auction));
    }

}
