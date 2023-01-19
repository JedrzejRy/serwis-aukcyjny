package com.example.serwisaukcyjny.model.repositories;

import com.example.serwisaukcyjny.model.Observer;
import com.example.serwisaukcyjny.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface ObserverRepository extends CrudRepository<Observer, Long> {

    Optional<Observer> findByUser(User user);

    boolean existsByUser(User user);

    Set<Observer> findAllByUser(User user);
}
