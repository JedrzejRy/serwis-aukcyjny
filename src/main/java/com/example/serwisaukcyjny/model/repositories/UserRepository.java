package com.example.serwisaukcyjny.model.repositories;

import com.example.serwisaukcyjny.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(int id);
    Optional<User> findByUserName(String username);

    boolean existsByLogin(String email);

    Optional<User> findByLogin(String login);
}
