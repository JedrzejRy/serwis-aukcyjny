package com.example.serwisaukcyjny.model.repositories;

import com.example.serwisaukcyjny.model.Purchase;
import com.example.serwisaukcyjny.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    List<Purchase> findAllByUser(User user);

}
