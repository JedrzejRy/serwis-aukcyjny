package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Observer;
import com.example.serwisaukcyjny.model.Purchase;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.repositories.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository repository;

    private final ObserverService observerService;

    private final BiddingService biddingService;

    @Transactional
    public Purchase save(Purchase purchase) {
        Iterable<Observer> observers = observerService.findAll();
        observers.forEach(observer -> observer.getAuctions().remove(purchase.getAuction()));
        biddingService.deleteAllByAuction(purchase.getAuction());
        return repository.save(purchase);
    }

    public List<Purchase> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    public List<Purchase> findAll() {
        return (List<Purchase>) repository.findAll();
    }

    public boolean existByAuction(Auction auction) {
        return repository.existsByAuction(auction);
    }
}
