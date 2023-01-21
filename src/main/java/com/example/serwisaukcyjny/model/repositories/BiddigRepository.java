package com.example.serwisaukcyjny.model.repositories;

import com.example.serwisaukcyjny.model.Bidding;
import com.example.serwisaukcyjny.model.Observer;
import com.example.serwisaukcyjny.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BiddigRepository extends CrudRepository<Bidding, Long> {

  List<Bidding> findAllByUser(User user);
}
