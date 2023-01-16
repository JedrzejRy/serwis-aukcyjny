package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Purchase;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.repositories.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository repository;


    public Purchase save(Purchase purchase) {
        return repository.save(purchase);
    }

    public List<Purchase> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    public List<Purchase> findAll() {
        return (List<Purchase>) repository.findAll();
    }
}
