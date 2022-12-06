package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.Purchase;
import com.example.serwisaukcyjny.model.repositories.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

private final PurchaseRepository repository;

public Purchase save(Purchase purchase) {return repository.save(purchase);}

}
